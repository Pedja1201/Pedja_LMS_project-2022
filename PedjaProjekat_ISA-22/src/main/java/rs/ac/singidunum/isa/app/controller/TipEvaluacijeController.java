package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.EvaluacijaZnanjaDTO;
import rs.ac.singidunum.isa.app.dto.IshodDTO;
import rs.ac.singidunum.isa.app.dto.TipEvaluacijeDTO;
import rs.ac.singidunum.isa.app.model.EvaluacijaZnanja;
import rs.ac.singidunum.isa.app.model.TipEvaluacije;
import rs.ac.singidunum.isa.app.service.TipEvaluacijeService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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

    @RequestMapping(path = "", method = RequestMethod.POST)
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

    @RequestMapping(path = "/{tipEvaluacijeId}", method = RequestMethod.PUT)
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

    @RequestMapping(path = "/{tipEvaluacijeId}", method = RequestMethod.DELETE)
    public ResponseEntity<TipEvaluacijeDTO> delete(@PathVariable("tipEvaluacijeId") Long tipEvaluacijeId) {
        if (tipEvaluacijeService.findOne(tipEvaluacijeId).isPresent()) {
            tipEvaluacijeService.delete(tipEvaluacijeId);
            return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipEvaluacijeDTO>(HttpStatus.NOT_FOUND);
    }
}
