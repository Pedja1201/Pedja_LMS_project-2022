package rs.ac.singidunum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.app.aspect.LoggedUniverzitet;
import rs.ac.singidunum.app.dto.AdresaDTO;
import rs.ac.singidunum.app.dto.NastavnikDTO;
import rs.ac.singidunum.app.dto.UniverzitetDTO;
import rs.ac.singidunum.app.dto.ZvanjeDTO;
import rs.ac.singidunum.app.model.Univerzitet;
import rs.ac.singidunum.app.service.UniverzitetService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/univerziteti")
public class UniverzitetController {
    @Autowired
    private UniverzitetService univerzitetService;

    @LoggedUniverzitet
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<UniverzitetDTO>> getAll(Pageable pageable) {
        Page<Univerzitet> univerzitet = univerzitetService.findAll(pageable);
        Page<UniverzitetDTO> univerziteti = univerzitet.map(new Function<Univerzitet, UniverzitetDTO>() {
            public UniverzitetDTO apply(Univerzitet univerzitet) {
                UniverzitetDTO univerzitetDTO = new UniverzitetDTO(univerzitet.getId(),univerzitet.getNaziv(), univerzitet.getDatumVremeOsnivanja(),
                        new AdresaDTO(univerzitet.getAdresa().getId(), univerzitet.getAdresa().getUlica(),
                                univerzitet.getAdresa().getBroj(),null),
                        new NastavnikDTO(univerzitet.getNastavnik().getId(),univerzitet.getNastavnik().getKorisnickoIme(),
                                univerzitet.getNastavnik().getLozinka(),univerzitet.getNastavnik().getEmail(),univerzitet.getNastavnik().getIme(),
                                univerzitet.getNastavnik().getBiografija(), univerzitet.getNastavnik().getJmbg(),null,null)
                );
                // Conversion logic
                return univerzitetDTO;
            }
        });
        return new ResponseEntity<Page<UniverzitetDTO>>(univerziteti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.GET)
    public ResponseEntity<UniverzitetDTO> get(@PathVariable("univerzitetId") Long univerzitetId) {
        Optional<Univerzitet> univerzitet = univerzitetService.findOne(univerzitetId);
        if (univerzitet.isPresent()) {
            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(univerzitet.get().getId(),
                                        univerzitet.get().getNaziv(), univerzitet.get().getDatumVremeOsnivanja(),
                    new AdresaDTO(univerzitet.get().getAdresa().getId(), univerzitet.get().getAdresa().getUlica(),
                            univerzitet.get().getAdresa().getBroj(),null),
                    new NastavnikDTO(univerzitet.get().getNastavnik().getId(),univerzitet.get().getNastavnik().getKorisnickoIme(),
                            univerzitet.get().getNastavnik().getLozinka(),univerzitet.get().getNastavnik().getEmail(),univerzitet.get().getNastavnik().getIme(),
                            univerzitet.get().getNastavnik().getBiografija(), univerzitet.get().getNastavnik().getJmbg(),
                                                     null,null));

            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UniverzitetDTO> create(@RequestBody Univerzitet univerzitet) {
        try {
            univerzitetService.save(univerzitet);
            AdresaDTO adresaDTO = new AdresaDTO(univerzitet.getAdresa().getId(), univerzitet.getAdresa().getUlica(),
                    univerzitet.getAdresa().getBroj(),null);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(univerzitet.getNastavnik().getId(),univerzitet.getNastavnik().getKorisnickoIme(),
                    univerzitet.getNastavnik().getLozinka(),univerzitet.getNastavnik().getEmail(),univerzitet.getNastavnik().getIme(),
                    univerzitet.getNastavnik().getBiografija(), univerzitet.getNastavnik().getJmbg(),null,null);

            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(univerzitet.getId(), univerzitet.getNaziv(),
                    univerzitet.getDatumVremeOsnivanja(), adresaDTO, nastavnikDTO);

            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UniverzitetDTO> update(@PathVariable("univerzitetId") Long univerzitetId,
                                             @RequestBody Univerzitet izmenjenUniverzitet) {
        Univerzitet univerzitet = univerzitetService.findOne(univerzitetId).orElse(null);
        if (univerzitet != null) {
            izmenjenUniverzitet.setId(univerzitetId);
            izmenjenUniverzitet = univerzitetService.save(izmenjenUniverzitet);
            AdresaDTO adresaDTO = new AdresaDTO(izmenjenUniverzitet.getAdresa().getId(), izmenjenUniverzitet.getAdresa().getUlica(),
                    izmenjenUniverzitet.getAdresa().getBroj(),null);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(izmenjenUniverzitet.getNastavnik().getId(),izmenjenUniverzitet.getNastavnik().getKorisnickoIme(),
                    izmenjenUniverzitet.getNastavnik().getLozinka(),izmenjenUniverzitet.getNastavnik().getEmail(),izmenjenUniverzitet.getNastavnik().getIme(),
                    izmenjenUniverzitet.getNastavnik().getBiografija(), izmenjenUniverzitet.getNastavnik().getJmbg(),null,null);

            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(izmenjenUniverzitet.getId(), izmenjenUniverzitet.getNaziv(),
                    izmenjenUniverzitet.getDatumVremeOsnivanja(), adresaDTO, nastavnikDTO);
            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UniverzitetDTO> delete(@PathVariable("univerzitetId") Long univerzitetId) {
        if (univerzitetService.findOne(univerzitetId).isPresent()) {
            univerzitetService.delete(univerzitetId);
            return new ResponseEntity<UniverzitetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje svih nastavnika
    @RequestMapping(path = "/findNastavnik/{nastavnikIme}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UniverzitetDTO>> findNastavnikUniverziteta(@PathVariable("nastavnikIme") String nastavnikIme) {
        ArrayList<UniverzitetDTO> univerzitetiDTO = new ArrayList<>();
        for(Univerzitet univerzitet : univerzitetService.findNastavnikUniverziteta(nastavnikIme)) {
            System.out.println(univerzitet.getNaziv());
            NastavnikDTO nastavnikDTO = new NastavnikDTO(univerzitet.getNastavnik().getId(),univerzitet.getNastavnik().getKorisnickoIme(),
                    null,univerzitet.getNastavnik().getEmail(), univerzitet.getNastavnik().getIme(),univerzitet.getNastavnik().getBiografija(),
                    univerzitet.getNastavnik().getJmbg(),null,
                    new ZvanjeDTO(univerzitet.getNastavnik().getZvanje().getId(), univerzitet.getNastavnik().getZvanje().getDatumIzbora(),
                            univerzitet.getNastavnik().getZvanje().getDatumPrestanka(), null, null));
            univerzitetiDTO.add(new UniverzitetDTO(univerzitet.getId(), univerzitet.getNaziv(),
                    univerzitet.getDatumVremeOsnivanja(), null, nastavnikDTO));
        }
        return new ResponseEntity<Iterable<UniverzitetDTO>>(univerzitetiDTO, HttpStatus.OK);

    }
}
