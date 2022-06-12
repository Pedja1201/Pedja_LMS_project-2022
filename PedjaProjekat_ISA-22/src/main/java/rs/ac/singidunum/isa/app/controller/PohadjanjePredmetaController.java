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
import rs.ac.singidunum.isa.app.dto.PohadjanjePredmetaDTO;
import rs.ac.singidunum.isa.app.dto.RealizacijaPredmetaDTO;
import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.isa.app.service.PohadjanjePredmetaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/pohadjanjePredmeta")
public class PohadjanjePredmetaController {
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<PohadjanjePredmetaDTO>> getAll(Pageable pageable) {
        Page<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findAll(pageable);
        Page<PohadjanjePredmetaDTO> pohadjanjaPredmeta = pohadjanjePredmeta.map(new Function<PohadjanjePredmeta, PohadjanjePredmetaDTO>() {
            public PohadjanjePredmetaDTO apply(PohadjanjePredmeta pohadjanjePredmeta) {
                PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(), pohadjanjePredmeta.getKonacnaOcena(),
                        new RealizacijaPredmetaDTO(pohadjanjePredmeta.getRealizacijaPredmeta().getId(),
                                pohadjanjePredmeta.getRealizacijaPredmeta().getNaziv(),null,null,null,null)
                );
                // Conversion logic
                return pohadjanjePredmetaDTO;
            }
        });
        return new ResponseEntity<Page<PohadjanjePredmetaDTO>>(pohadjanjaPredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.GET)
    public ResponseEntity<PohadjanjePredmetaDTO> get(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        Optional<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId);
        if (pohadjanjePredmeta.isPresent()) {
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.get().getId(),
                    pohadjanjePredmeta.get().getKonacnaOcena(),
                    new RealizacijaPredmetaDTO(pohadjanjePredmeta.get().getRealizacijaPredmeta().getId(),
                            pohadjanjePredmeta.get().getRealizacijaPredmeta().getNaziv(),null,null,null,null));
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<PohadjanjePredmetaDTO> create(@RequestBody PohadjanjePredmeta pohadjanjePredmeta) {
        try {
            pohadjanjePredmetaService.save(pohadjanjePredmeta);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(pohadjanjePredmeta.getRealizacijaPredmeta().getId(),
                    pohadjanjePredmeta.getRealizacijaPredmeta().getNaziv(),null,null,null,null);

            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(),
                    pohadjanjePredmeta.getKonacnaOcena(),realizacijaPredmetaDTO);

            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.PUT)
    public ResponseEntity<PohadjanjePredmetaDTO> update(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId,
                                                   @RequestBody PohadjanjePredmeta izmenjenaPohadjeniPredmet) {
        PohadjanjePredmeta pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).orElse(null);
        if (pohadjanjePredmeta != null) {
            izmenjenaPohadjeniPredmet.setId(pohadjanjePredmetaId);
            izmenjenaPohadjeniPredmet = pohadjanjePredmetaService.save(izmenjenaPohadjeniPredmet);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(izmenjenaPohadjeniPredmet.getRealizacijaPredmeta().getId(),
                    izmenjenaPohadjeniPredmet.getRealizacijaPredmeta().getNaziv(),null,null,null,null);

            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(izmenjenaPohadjeniPredmet.getId(),
                    izmenjenaPohadjeniPredmet.getKonacnaOcena(),realizacijaPredmetaDTO);
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.DELETE)
    public ResponseEntity<PohadjanjePredmetaDTO> delete(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        if (pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).isPresent()) {
            pohadjanjePredmetaService.delete(pohadjanjePredmetaId);
            return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }
}
