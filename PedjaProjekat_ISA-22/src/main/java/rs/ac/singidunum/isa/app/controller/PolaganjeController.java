package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.aspect.Logged;
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.Polaganje;
import rs.ac.singidunum.isa.app.service.PolaganjeService;


import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/polaganja")
public class PolaganjeController {
    @Autowired
    private PolaganjeService polaganjeService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT", "ROLE_ADMIN"})
    public ResponseEntity<Page<PolaganjeDTO>> getAll(Pageable pageable) {
        Page<Polaganje> polaganje = polaganjeService.findAll(pageable);
        Page<PolaganjeDTO> polaganja = polaganje.map(new Function<Polaganje, PolaganjeDTO>() {
            public PolaganjeDTO apply(Polaganje polaganje) {
                PolaganjeDTO polaganjeDTO = new PolaganjeDTO(polaganje.getId(), polaganje.getBodovi(),polaganje.getNapomena(),
                        new EvaluacijaZnanjaDTO(polaganje.getEvaluacijaZnanja().getId(),polaganje.getEvaluacijaZnanja().getVremePocetka(),
                                polaganje.getEvaluacijaZnanja().getVremeZavrsetka(),null,null),
                        new StudentNaGodiniDTO(polaganje.getStudentNaGodini().getId(), polaganje.getStudentNaGodini().getDatumUpisa(),
                                polaganje.getStudentNaGodini().getBrojIndeksa(),null)
                );
                // Conversion logic
                return polaganjeDTO;
            }
        });
        return new ResponseEntity<Page<PolaganjeDTO>>(polaganja, HttpStatus.OK);
    }

    @RequestMapping(path = "/{polaganjeId}", method = RequestMethod.GET)
    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT", "ROLE_ADMIN"})
    public ResponseEntity<PolaganjeDTO> get(@PathVariable("polaganjeId") Long polaganjeId) {
        Optional<Polaganje> polaganje = polaganjeService.findOne(polaganjeId);
        if (polaganje.isPresent()) {
            PolaganjeDTO polaganjeDTO = new PolaganjeDTO(polaganje.get().getId(),polaganje.get().getBodovi(),polaganje.get().getNapomena(),
                    new EvaluacijaZnanjaDTO(polaganje.get().getEvaluacijaZnanja().getId(), polaganje.get().getEvaluacijaZnanja().getVremePocetka(),
                            polaganje.get().getEvaluacijaZnanja().getVremeZavrsetka(),null,null),
                    new StudentNaGodiniDTO(polaganje.get().getStudentNaGodini().getId(),polaganje.get().getStudentNaGodini().getDatumUpisa(),
                            polaganje.get().getStudentNaGodini().getBrojIndeksa(),null));

            return new ResponseEntity<PolaganjeDTO>(polaganjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PolaganjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PolaganjeDTO> create(@RequestBody Polaganje polaganje) {
        try {
            polaganjeService.save(polaganje);
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(polaganje.getEvaluacijaZnanja().getId(),
                    polaganje.getEvaluacijaZnanja().getVremePocetka(), polaganje.getEvaluacijaZnanja().getVremeZavrsetka(),null,null);
            StudentNaGodiniDTO studentNaGodiniDTO =new StudentNaGodiniDTO(polaganje.getStudentNaGodini().getId(),
                    polaganje.getStudentNaGodini().getDatumUpisa(), polaganje.getStudentNaGodini().getBrojIndeksa(), null);

            PolaganjeDTO polaganjeDTO = new PolaganjeDTO(polaganje.getId(),polaganje.getBodovi(),
                    polaganje.getNapomena(), evaluacijaZnanjaDTO, studentNaGodiniDTO);
            return new ResponseEntity<PolaganjeDTO>(polaganjeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PolaganjeDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{polaganjeId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PolaganjeDTO> update(@PathVariable("polaganjeId") Long polaganjeId,
                                              @RequestBody Polaganje izmenjenoPolaganje) {
        Polaganje polaganje = polaganjeService.findOne(polaganjeId).orElse(null);
        if (polaganje != null) {
            izmenjenoPolaganje.setId(polaganjeId);
            izmenjenoPolaganje = polaganjeService.save(izmenjenoPolaganje);
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(izmenjenoPolaganje.getEvaluacijaZnanja().getId(),
                    izmenjenoPolaganje.getEvaluacijaZnanja().getVremePocetka(), izmenjenoPolaganje.getEvaluacijaZnanja().getVremeZavrsetka(),null,null);
            StudentNaGodiniDTO studentNaGodiniDTO =new StudentNaGodiniDTO(izmenjenoPolaganje.getStudentNaGodini().getId(),
                    izmenjenoPolaganje.getStudentNaGodini().getDatumUpisa(), izmenjenoPolaganje.getStudentNaGodini().getBrojIndeksa(), null);

            PolaganjeDTO polaganjeDTO = new PolaganjeDTO(izmenjenoPolaganje.getId(),izmenjenoPolaganje.getBodovi(), izmenjenoPolaganje.getNapomena(),
                    evaluacijaZnanjaDTO, studentNaGodiniDTO);
            return new ResponseEntity<PolaganjeDTO>(polaganjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PolaganjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{polaganjeId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PolaganjeDTO> delete(@PathVariable("polaganjeId") Long polaganjeId) {
        if (polaganjeService.findOne(polaganjeId).isPresent()) {
            polaganjeService.delete(polaganjeId);
            return new ResponseEntity<PolaganjeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PolaganjeDTO>(HttpStatus.NOT_FOUND);
    }

    //Done:Metoda za pronalazenje  studenta na godini koji polazu
    @RequestMapping(path = "/findStudentNaGodini/{brojIndeksa}", method = RequestMethod.GET)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<PolaganjeDTO>> findStudentPolaganja(@PathVariable("brojIndeksa") String brojIndeksa) {
        ArrayList<PolaganjeDTO> polaganjeDTO = new ArrayList<>();
        for(Polaganje polaganje : polaganjeService.findStudentNaGodini(brojIndeksa)) {
            StudentNaGodiniDTO studentDTO = new StudentNaGodiniDTO(polaganje.getStudentNaGodini().getId(),
                    polaganje.getStudentNaGodini().getDatumUpisa(), polaganje.getStudentNaGodini().getBrojIndeksa(),
                    new GodinaStudijaDTO(polaganje.getStudentNaGodini().getGodinaStudija().getId(),polaganje.getStudentNaGodini().getGodinaStudija().getGodina(),null));
            polaganjeDTO.add(new PolaganjeDTO(polaganje.getId(), polaganje.getBodovi(), polaganje.getNapomena(),null, studentDTO));
        }
        return new ResponseEntity<Iterable<PolaganjeDTO>>(polaganjeDTO, HttpStatus.OK);

    }
}
