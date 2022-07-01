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
import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.isa.app.service.PohadjanjePredmetaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/pohadjanjaPredmeta")
public class PohadjanjePredmetaController {
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<Page<PohadjanjePredmetaDTO>> getAll(Pageable pageable) {
        Page<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findAll(pageable);
        Page<PohadjanjePredmetaDTO> pohadjanjaPredmeta = pohadjanjePredmeta.map(new Function<PohadjanjePredmeta, PohadjanjePredmetaDTO>() {
            public PohadjanjePredmetaDTO apply(PohadjanjePredmeta pohadjanjePredmeta) {
                PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(), pohadjanjePredmeta.getKonacnaOcena(),
                        new RealizacijaPredmetaDTO(pohadjanjePredmeta.getRealizacijaPredmeta().getId(),
                                pohadjanjePredmeta.getRealizacijaPredmeta().getNaziv(),null,null,null,null),
                        new StudentDTO(pohadjanjePredmeta.getStudent().getId(), pohadjanjePredmeta.getStudent().getKorisnickoIme(),null,
                                pohadjanjePredmeta.getStudent().getEmail(),pohadjanjePredmeta.getStudent().getJmbg(),pohadjanjePredmeta.getStudent().getIme(),
                                new AdresaDTO(pohadjanjePredmeta.getStudent().getAdresa().getId(), pohadjanjePredmeta.getStudent().getAdresa().getUlica(), pohadjanjePredmeta.getStudent().getAdresa().getBroj(),null),
                                new StudentNaGodiniDTO(pohadjanjePredmeta.getStudent().getStudentNaGodini().getId(),pohadjanjePredmeta.getStudent().getStudentNaGodini().getDatumUpisa(),pohadjanjePredmeta.getStudent().getStudentNaGodini().getBrojIndeksa(), null))
                );
                // Conversion logic
                return pohadjanjePredmetaDTO;
            }
        });
        return new ResponseEntity<Page<PohadjanjePredmetaDTO>>(pohadjanjaPredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<PohadjanjePredmetaDTO> get(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        Optional<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId);
        if (pohadjanjePredmeta.isPresent()) {
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.get().getId(),
                    pohadjanjePredmeta.get().getKonacnaOcena(),
                    new RealizacijaPredmetaDTO(pohadjanjePredmeta.get().getRealizacijaPredmeta().getId(),
                            pohadjanjePredmeta.get().getRealizacijaPredmeta().getNaziv(),null,null,null,null),
                    new StudentDTO(pohadjanjePredmeta.get().getStudent().getId(), pohadjanjePredmeta.get().getStudent().getKorisnickoIme(),null,
                            pohadjanjePredmeta.get().getStudent().getEmail(), pohadjanjePredmeta.get().getStudent().getJmbg(),pohadjanjePredmeta.get().getStudent().getIme(),
                            new AdresaDTO(pohadjanjePredmeta.get().getStudent().getAdresa().getId(), pohadjanjePredmeta.get().getStudent().getAdresa().getUlica(), pohadjanjePredmeta.get().getStudent().getAdresa().getBroj(),null),
                            new StudentNaGodiniDTO(pohadjanjePredmeta.get().getStudent().getStudentNaGodini().getId(),pohadjanjePredmeta.get().getStudent().getStudentNaGodini().getDatumUpisa(),pohadjanjePredmeta.get().getStudent().getStudentNaGodini().getBrojIndeksa(), null)));
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PohadjanjePredmetaDTO> create(@RequestBody PohadjanjePredmeta pohadjanjePredmeta) {
        try {
            pohadjanjePredmetaService.save(pohadjanjePredmeta);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(pohadjanjePredmeta.getRealizacijaPredmeta().getId(),
                    pohadjanjePredmeta.getRealizacijaPredmeta().getNaziv(),null,null,null,null);
            StudentDTO studentDTO = new StudentDTO(pohadjanjePredmeta.getStudent().getId(),
                    pohadjanjePredmeta.getStudent().getKorisnickoIme(), null,pohadjanjePredmeta.getStudent().getEmail(),
                    pohadjanjePredmeta.getStudent().getJmbg(), pohadjanjePredmeta.getStudent().getIme(),null,null);

            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(),
                    pohadjanjePredmeta.getKonacnaOcena(),realizacijaPredmetaDTO, studentDTO);

            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PohadjanjePredmetaDTO> update(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId,
                                                   @RequestBody PohadjanjePredmeta izmenjenaPohadjeniPredmet) {
        PohadjanjePredmeta pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).orElse(null);
        if (pohadjanjePredmeta != null) {
            izmenjenaPohadjeniPredmet.setId(pohadjanjePredmetaId);
            izmenjenaPohadjeniPredmet = pohadjanjePredmetaService.save(izmenjenaPohadjeniPredmet);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(izmenjenaPohadjeniPredmet.getRealizacijaPredmeta().getId(),
                    izmenjenaPohadjeniPredmet.getRealizacijaPredmeta().getNaziv(),null,null,null,null);
            StudentDTO studentDTO = new StudentDTO(izmenjenaPohadjeniPredmet.getStudent().getId(),
                    izmenjenaPohadjeniPredmet.getStudent().getKorisnickoIme(), null, izmenjenaPohadjeniPredmet.getStudent().getEmail(),
                    izmenjenaPohadjeniPredmet.getStudent().getJmbg(), izmenjenaPohadjeniPredmet.getStudent().getIme(),null,null);

            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(izmenjenaPohadjeniPredmet.getId(),
                    izmenjenaPohadjeniPredmet.getKonacnaOcena(),realizacijaPredmetaDTO, studentDTO);
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PohadjanjePredmetaDTO> delete(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        if (pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).isPresent()) {
            pohadjanjePredmetaService.delete(pohadjanjePredmetaId);
            return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }
}
