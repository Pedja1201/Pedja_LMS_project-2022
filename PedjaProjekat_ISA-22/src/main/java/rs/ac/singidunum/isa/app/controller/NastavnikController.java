package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.isa.app.aspect.LoggedNastavnik;
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.service.NastavnikService;
import rs.ac.singidunum.isa.app.service.PdfService;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/nastavnici")
public class NastavnikController {
    @Autowired
    private NastavnikService nastavnikService;
    @Autowired
    private  PdfService pdfService;

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Page<NastavnikDTO>> getAllNastavnik(Pageable pageable) {
        Page<Nastavnik> nastavnik = nastavnikService.findAll(pageable);
        Page<NastavnikDTO> nastavnici = nastavnik.map(new Function<Nastavnik, NastavnikDTO>() {
            public NastavnikDTO apply(Nastavnik nastavnik) {
                NastavnikDTO nastavnikDTO = new NastavnikDTO(nastavnik.getId(), nastavnik.getKorisnickoIme(),
                        nastavnik.getLozinka(), nastavnik.getEmail(),nastavnik.getIme(),nastavnik.getBiografija(), nastavnik.getJmbg(),
                        new AdresaDTO(nastavnik.getAdresa().getId(),nastavnik.getAdresa().getUlica(),nastavnik.getAdresa().getBroj(),null),
                        new ZvanjeDTO(nastavnik.getZvanje().getId(), nastavnik.getZvanje().getDatumIzbora(),
                                nastavnik.getZvanje().getDatumPrestanka(),null,null)
                );
                // Conversion logic
                return nastavnikDTO;
            }
        });
        return new ResponseEntity<Page<NastavnikDTO>>(nastavnici, HttpStatus.OK);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<NastavnikDTO> getNastavnik(@PathVariable("nastavnikId") Long nastavnikId) {
        Optional<Nastavnik> nastavnik = nastavnikService.findOne(nastavnikId);
        if (nastavnik.isPresent()) {
            NastavnikDTO kartaDTO = new NastavnikDTO(nastavnik.get().getId(),nastavnik.get().getKorisnickoIme(),nastavnik.get().getLozinka(),
                    nastavnik.get().getEmail(),nastavnik.get().getIme(),nastavnik.get().getBiografija(), nastavnik.get().getJmbg(),
                    new AdresaDTO(nastavnik.get().getAdresa().getId(), nastavnik.get().getAdresa().getUlica(),
                            nastavnik.get().getAdresa().getBroj(),null),
                    new ZvanjeDTO(nastavnik.get().getZvanje().getId(), nastavnik.get().getZvanje().getDatumIzbora(),
                            nastavnik.get().getZvanje().getDatumPrestanka(),null,null));
            return new ResponseEntity<NastavnikDTO>(kartaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<NastavnikDTO> create(@RequestBody Nastavnik nastavnik) {
        try {
            nastavnikService.save(nastavnik);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(nastavnik.getId(),nastavnik.getKorisnickoIme(),nastavnik.getLozinka(),
                    nastavnik.getEmail(),nastavnik.getIme(),nastavnik.getBiografija(), nastavnik.getJmbg(),
                    new AdresaDTO(nastavnik.getAdresa().getId(), nastavnik.getAdresa().getUlica(),
                            nastavnik.getAdresa().getBroj(), null),
                    new ZvanjeDTO(nastavnik.getZvanje().getId(), nastavnik.getZvanje().getDatumIzbora(),
                            nastavnik.getZvanje().getDatumPrestanka(), null, null));
            return new ResponseEntity<NastavnikDTO>(nastavnikDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<NastavnikDTO> update(@PathVariable("nastavnikId") Long nastavnikId,
                                               @RequestBody Nastavnik izmenjenNastavnik) {
        Nastavnik nastavnik = nastavnikService.findOne(nastavnikId).orElse(null);
        if (nastavnik != null) {
            izmenjenNastavnik.setId(nastavnikId);
            nastavnikService.save(izmenjenNastavnik); //DONE: Sa ovim radi bez BUG-a (Beskonacna rekurzija!)
            NastavnikDTO nastavnikDTO = new NastavnikDTO(izmenjenNastavnik.getId(),izmenjenNastavnik.getKorisnickoIme(),izmenjenNastavnik.getLozinka(),
                    izmenjenNastavnik.getEmail(),izmenjenNastavnik.getIme(),izmenjenNastavnik.getBiografija(), izmenjenNastavnik.getJmbg(),
                    new AdresaDTO(izmenjenNastavnik.getAdresa().getId(), izmenjenNastavnik.getAdresa().getUlica(),
                            izmenjenNastavnik.getAdresa().getBroj(),null),
                    new ZvanjeDTO(izmenjenNastavnik.getZvanje().getId(), izmenjenNastavnik.getZvanje().getDatumIzbora(),
                            izmenjenNastavnik.getZvanje().getDatumPrestanka(),null, null));
            return new ResponseEntity<NastavnikDTO>(nastavnikDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Nastavnik> delete(@PathVariable("nastavnikId") Long nastavnikId) {
        if (nastavnikService.findOne(nastavnikId).isPresent()) {
            nastavnikService.delete(nastavnikId);
            return new ResponseEntity<Nastavnik>(HttpStatus.OK);
        }
        return new ResponseEntity<Nastavnik>(HttpStatus.NOT_FOUND);
    }

    //Metoda za preuzimanje PDF dokumenta - Potrebno (PdfService, pom.xml, resources)
    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public void downloadPdf(HttpServletResponse response){
        try{
            Path file = Paths.get(pdfService.generateNastavniciPdf().getAbsolutePath());
            if (Files.exists(file)){
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
