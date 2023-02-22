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
import rs.ac.singidunum.app.aspect.LoggedNastavnik;
import rs.ac.singidunum.app.dto.EvaluacijaZnanjaDTO;
import rs.ac.singidunum.app.dto.IshodDTO;
import rs.ac.singidunum.app.dto.TipEvaluacijeDTO;
import rs.ac.singidunum.app.model.EvaluacijaZnanja;
import rs.ac.singidunum.app.model.TipEvaluacije;
import rs.ac.singidunum.app.service.TipEvaluacijeService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/tipoviEvaluacije")
public class TipEvaluacijeController {
    @Autowired
    private TipEvaluacijeService tipEvaluacijeService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<TipEvaluacijeDTO>> getAll(Pageable pageable) {
        Page<TipEvaluacije> tipoviEvaluacije = tipEvaluacijeService.findAll(pageable);
        return new ResponseEntity<Page<TipEvaluacijeDTO>>(
                tipoviEvaluacije.map(tipEvaluacije -> new TipEvaluacijeDTO(tipEvaluacije.getId(), tipEvaluacije.getNaziv(),
                        (ArrayList<EvaluacijaZnanjaDTO>) tipEvaluacije.getEvaluacijeZnanja().stream()
                                .map(evaluacijaZnanja -> new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                                       evaluacijaZnanja.getVremeZavrsetka(), null,
                                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(),null)))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipEvaluacijeId}", method = RequestMethod.GET)
    public ResponseEntity<TipEvaluacijeDTO> get(@PathVariable("tipEvaluacijeId") Long tipEvaluacijeId) {
        Optional<TipEvaluacije> tipEvaluacije = tipEvaluacijeService.findOne(tipEvaluacijeId);
        TipEvaluacijeDTO tipEvaluacijeDTO;
        if (tipEvaluacije.isPresent()) {
            ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja = new ArrayList<EvaluacijaZnanjaDTO>();
            for (EvaluacijaZnanja evaluacijaZnanja : tipEvaluacije.get().getEvaluacijeZnanja()) {
                evaluacijeZnanja.add(new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                        evaluacijaZnanja.getVremeZavrsetka(),null,
                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(), null)));
            }
            tipEvaluacijeDTO = new TipEvaluacijeDTO(tipEvaluacije.get().getId(), tipEvaluacije.get().getNaziv(), evaluacijeZnanja);

            return new ResponseEntity<TipEvaluacijeDTO>(tipEvaluacijeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<TipEvaluacijeDTO> create(@RequestBody TipEvaluacije tipEvaluacije) {
        try {
            tipEvaluacijeService.save(tipEvaluacije);
            ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja = new ArrayList<EvaluacijaZnanjaDTO>();
            for(EvaluacijaZnanja evaluacijaZnanja : tipEvaluacije.getEvaluacijeZnanja()) {
                evaluacijeZnanja.add(new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(),evaluacijaZnanja.getVremePocetka(),
                        evaluacijaZnanja.getVremeZavrsetka(),null,
                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(),null)));
            }
            TipEvaluacijeDTO tipEvaluacijeDTO = new TipEvaluacijeDTO(tipEvaluacije.getId(), tipEvaluacije.getNaziv(), evaluacijeZnanja);
            return new ResponseEntity<TipEvaluacijeDTO>(tipEvaluacijeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{tipEvaluacijeId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<TipEvaluacijeDTO> update(@PathVariable("tipEvaluacijeId") Long tipEvaluacijeId,
                                            @RequestBody TipEvaluacije izmenjenTipEvaluacije) {
        TipEvaluacije tipEvaluacije = tipEvaluacijeService.findOne(tipEvaluacijeId).orElse(null);
        if (tipEvaluacije != null) {
            izmenjenTipEvaluacije.setId(tipEvaluacijeId);
            izmenjenTipEvaluacije = tipEvaluacijeService.save(izmenjenTipEvaluacije);
            ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja = new ArrayList<EvaluacijaZnanjaDTO>();
            for(EvaluacijaZnanja evaluacijaZnanja : izmenjenTipEvaluacije.getEvaluacijeZnanja()) {
                evaluacijeZnanja.add(new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(),evaluacijaZnanja.getVremePocetka(),
                        evaluacijaZnanja.getVremeZavrsetka(),null,
                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(),null)));
            }
            TipEvaluacijeDTO tipEvaluacijeDTO = new TipEvaluacijeDTO(izmenjenTipEvaluacije.getId(), izmenjenTipEvaluacije.getNaziv(), evaluacijeZnanja);
            return new ResponseEntity<TipEvaluacijeDTO>(tipEvaluacijeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{tipEvaluacijeId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<TipEvaluacijeDTO> delete(@PathVariable("tipEvaluacijeId") Long tipEvaluacijeId) {
        if (tipEvaluacijeService.findOne(tipEvaluacijeId).isPresent()) {
            tipEvaluacijeService.delete(tipEvaluacijeId);
            return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.NOT_FOUND);
    }
}
