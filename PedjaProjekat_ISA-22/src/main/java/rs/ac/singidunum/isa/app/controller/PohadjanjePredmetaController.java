package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(path = "/api/pohadjanjePredmeta")
public class PohadjanjePredmetaController {
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<PohadjanjePredmetaDTO>> getAll() {
        ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = new ArrayList<PohadjanjePredmetaDTO>();

        for (PohadjanjePredmeta pohadjanjePredmeta : pohadjanjePredmetaService.findAll()) {
            pohadjanjaPredmeta.add(new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(),pohadjanjePredmeta.getKonacnaOcena(),
                    new RealizacijaPredmetaDTO(pohadjanjePredmeta.getRealizacijaPredmeta().getId(),
                            pohadjanjePredmeta.getRealizacijaPredmeta().getNaziv(),null,null)));
        }

        return new ResponseEntity<Iterable<PohadjanjePredmetaDTO>>(pohadjanjaPredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.GET)
    public ResponseEntity<PohadjanjePredmetaDTO> get(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        Optional<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId);
        if (pohadjanjePredmeta.isPresent()) {
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.get().getId(),
                    pohadjanjePredmeta.get().getKonacnaOcena(),
                    new RealizacijaPredmetaDTO(pohadjanjePredmeta.get().getRealizacijaPredmeta().getId(),
                            pohadjanjePredmeta.get().getRealizacijaPredmeta().getNaziv(),null,null));
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<PohadjanjePredmeta> create(@RequestBody PohadjanjePredmeta pohadjanjePredmeta) {
        try {
            pohadjanjePredmetaService.save(pohadjanjePredmeta);
            return new ResponseEntity<PohadjanjePredmeta>(pohadjanjePredmeta, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PohadjanjePredmeta>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.PUT)
    public ResponseEntity<PohadjanjePredmeta> update(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId,
                                                   @RequestBody PohadjanjePredmeta izmenjenaPohadjeniPredmet) {
        PohadjanjePredmeta pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).orElse(null);
        if (pohadjanjePredmeta != null) {
            izmenjenaPohadjeniPredmet.setId(pohadjanjePredmetaId);
            pohadjanjePredmetaService.save(izmenjenaPohadjeniPredmet);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<PohadjanjePredmeta>(izmenjenaPohadjeniPredmet, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmeta>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.DELETE)
    public ResponseEntity<PohadjanjePredmeta> delete(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        if (pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).isPresent()) {
            pohadjanjePredmetaService.delete(pohadjanjePredmetaId);
            return new ResponseEntity<PohadjanjePredmeta>(HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmeta>(HttpStatus.NOT_FOUND);
    }
}
