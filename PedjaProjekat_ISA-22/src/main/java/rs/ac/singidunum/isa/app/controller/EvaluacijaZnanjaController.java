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
import rs.ac.singidunum.isa.app.service.EvaluacijaZnanjaService;

import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/evaluacijeZnanja")
public class EvaluacijaZnanjaController {
    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<EvaluacijaZnanjaDTO>> getAll(Pageable pageable) {
        Page<EvaluacijaZnanja> evaluacijaZnanja = evaluacijaZnanjaService.findAll(pageable);
        Page<EvaluacijaZnanjaDTO> evaluacijeZnanja = evaluacijaZnanja.map(new Function<EvaluacijaZnanja, EvaluacijaZnanjaDTO>() {
            public EvaluacijaZnanjaDTO apply(EvaluacijaZnanja evaluacijaZnanja) {
                EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                        evaluacijaZnanja.getVremeZavrsetka(),
                        new TipEvaluacijeDTO(evaluacijaZnanja.getTipEvaluacije().getId(), evaluacijaZnanja.getTipEvaluacije().getNaziv(),null),
                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(), null)
                );
                // Conversion logic
                return evaluacijaZnanjaDTO;
            }
        });
        return new ResponseEntity<Page<EvaluacijaZnanjaDTO>>(evaluacijeZnanja, HttpStatus.OK);
    }


    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.GET)
    public ResponseEntity<EvaluacijaZnanjaDTO> get(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId) {
        Optional<EvaluacijaZnanja> evaluacijaZnanja = evaluacijaZnanjaService.findOne(evaluacijaZnanjaId);
        if (evaluacijaZnanja.isPresent()) {
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.get().getId(),evaluacijaZnanja.get().getVremePocetka(),evaluacijaZnanja.get().getVremeZavrsetka(),
                    new TipEvaluacijeDTO(evaluacijaZnanja.get().getTipEvaluacije().getId(), evaluacijaZnanja.get().getTipEvaluacije().getNaziv(),null),
                    new IshodDTO(evaluacijaZnanja.get().getIshod().getId(), evaluacijaZnanja.get().getIshod().getOpis(),null));
            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<EvaluacijaZnanjaDTO> create(@RequestBody EvaluacijaZnanja evaluacijaZnanja) {
        try {
            evaluacijaZnanjaService.save(evaluacijaZnanja);
            TipEvaluacijeDTO tipEvaluacijeDTO =  new TipEvaluacijeDTO(evaluacijaZnanja.getTipEvaluacije().getId(),
                    evaluacijaZnanja.getTipEvaluacije().getNaziv(),null);
            IshodDTO ishodDTO =  new IshodDTO(evaluacijaZnanja.getIshod().getId(),
                    evaluacijaZnanja.getIshod().getOpis(),null);

            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                    evaluacijaZnanja.getVremeZavrsetka(), tipEvaluacijeDTO, ishodDTO);

            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.PUT)
    public ResponseEntity<EvaluacijaZnanjaDTO> update(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId,
                                            @RequestBody EvaluacijaZnanja izmenjenaEvaluacijaZnanja) {
        EvaluacijaZnanja evaluacijaZnanja = evaluacijaZnanjaService.findOne(evaluacijaZnanjaId).orElse(null);
        if (evaluacijaZnanja != null) {
            izmenjenaEvaluacijaZnanja.setId(evaluacijaZnanjaId);
            izmenjenaEvaluacijaZnanja = evaluacijaZnanjaService.save(izmenjenaEvaluacijaZnanja);
            TipEvaluacijeDTO tipEvaluacijeDTO =  new TipEvaluacijeDTO(izmenjenaEvaluacijaZnanja.getTipEvaluacije().getId(),
                    izmenjenaEvaluacijaZnanja.getTipEvaluacije().getNaziv(),null);
            IshodDTO ishodDTO =  new IshodDTO(izmenjenaEvaluacijaZnanja.getIshod().getId(),
                    izmenjenaEvaluacijaZnanja.getIshod().getOpis(),null);

            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(izmenjenaEvaluacijaZnanja.getId(), izmenjenaEvaluacijaZnanja.getVremePocetka(),
                    izmenjenaEvaluacijaZnanja.getVremeZavrsetka(), tipEvaluacijeDTO, ishodDTO);
            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.DELETE)
    public ResponseEntity<EvaluacijaZnanjaDTO> delete(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId) {
        if (evaluacijaZnanjaService.findOne(evaluacijaZnanjaId).isPresent()) {
            evaluacijaZnanjaService.delete(evaluacijaZnanjaId);
            return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }
}
