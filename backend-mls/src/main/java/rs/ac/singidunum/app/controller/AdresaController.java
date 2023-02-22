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
import rs.ac.singidunum.app.dto.AdresaDTO;
import rs.ac.singidunum.app.dto.DrzavaDTO;
import rs.ac.singidunum.app.dto.MestoDTO;
import rs.ac.singidunum.app.model.Adresa;
import rs.ac.singidunum.app.service.AdresaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/adrese")
public class AdresaController {
    @Autowired
    private AdresaService adresaService;

//    @CrossOrigin  //CrosPolicy na clientu
    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<AdresaDTO>> getAll(Pageable pageable) {
        Page<Adresa> adresa = adresaService.findAll(pageable);
        Page<AdresaDTO> adrese = adresa.map(new Function<Adresa, AdresaDTO>() {
            public AdresaDTO apply(Adresa adresa) {
                AdresaDTO adresaDTO = new AdresaDTO(adresa.getId(), adresa.getUlica(), adresa.getBroj(),
                        new MestoDTO(adresa.getMesto().getId(), adresa.getMesto().getNaziv(),null)
                );
                // Conversion logic

                return adresaDTO;
            }
        });
        return new ResponseEntity<Page<AdresaDTO>>(adrese, HttpStatus.OK);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.GET)
    public ResponseEntity<AdresaDTO> get(@PathVariable("adresaId") Long adresaId) {
        Optional<Adresa> adresa = adresaService.findOne(adresaId);
        if (adresa.isPresent()) {
            AdresaDTO adresaDTO = new AdresaDTO(adresa.get().getId(),adresa.get().getUlica(),adresa.get().getBroj(),
                    new MestoDTO(adresa.get().getMesto().getId(), adresa.get().getMesto().getNaziv(),null));
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdresaDTO> create(@RequestBody Adresa adresa) {
        try {
            adresaService.save(adresa);
            MestoDTO mestoDTO =  new MestoDTO(adresa.getMesto().getId(), adresa.getMesto().getNaziv(),null);

            AdresaDTO adresaDTO = new AdresaDTO(adresa.getId(), adresa.getUlica(),adresa.getBroj(), mestoDTO);

            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin  //CrosPolicy na clientu
    @LoggedAdministrator
    @RequestMapping(path = "/{adresaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdresaDTO> update(@PathVariable("adresaId") Long adresaId,
                                                   @RequestBody Adresa izmenjenaAdresa) {
        Adresa adresa = adresaService.findOne(adresaId).orElse(null);
        if (adresa != null) {
            izmenjenaAdresa.setId(adresaId);
            izmenjenaAdresa = adresaService.save(izmenjenaAdresa);
            MestoDTO mestoDTO =  new MestoDTO(izmenjenaAdresa.getMesto().getId(), izmenjenaAdresa.getMesto().getNaziv(),null);

            AdresaDTO adresaDTO = new AdresaDTO(izmenjenaAdresa.getId(), izmenjenaAdresa.getUlica(),izmenjenaAdresa.getBroj(), mestoDTO);
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin  //CrosPolicy na clientu
    @LoggedAdministrator
    @RequestMapping(path = "/{adresaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdresaDTO> delete(@PathVariable("adresaId") Long adresaId) {
        if (adresaService.findOne(adresaId).isPresent()) {
            adresaService.delete(adresaId);
            return new ResponseEntity<AdresaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje mesta po nazivu
    @CrossOrigin  //CrosPolicy na clientu
    @RequestMapping(path = "/findMesto/{mestoNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AdresaDTO>> findMestoAdresee(@PathVariable("mestoNaziv") String mestoNaziv) {
        ArrayList<AdresaDTO> adreseDTO = new ArrayList<>();
        for(Adresa adresa : adresaService.findMestoAdresee(mestoNaziv)) {
            System.out.println(adresa.getUlica());
            MestoDTO mestoDTO = new MestoDTO(adresa.getMesto().getId(),adresa.getMesto().getNaziv(),
                    new DrzavaDTO(adresa.getMesto().getDrzava().getId(), adresa.getMesto().getDrzava().getNaziv(),null));
            adreseDTO.add(new AdresaDTO(adresa.getId(), adresa.getUlica(), adresa.getBroj(), mestoDTO));
        }
        return new ResponseEntity<Iterable<AdresaDTO>>(adreseDTO, HttpStatus.OK);

    }
}
