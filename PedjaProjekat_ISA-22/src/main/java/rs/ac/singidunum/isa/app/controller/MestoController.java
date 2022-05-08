package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.DrzavaDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.MestoService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/mesta")
public class MestoController {
    @Autowired
    private MestoService mestoService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<MestoDTO>> getAll() {
        ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();

        for (Mesto mesto : mestoService.findAll()) {
            mesta.add(new MestoDTO(mesto.getId(),mesto.getNaziv(),
                    new DrzavaDTO(mesto.getDrzava().getId(), mesto.getDrzava().getNaziv(),null)));
        }

        return new ResponseEntity<Iterable<MestoDTO>>(mesta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.GET)
    public ResponseEntity<MestoDTO> get(@PathVariable("mestoId") Long mestoId) {
        Optional<Mesto> mesto = mestoService.findOne(mestoId);
        if (mesto.isPresent()) {
            MestoDTO mestoDTO = new MestoDTO(mesto.get().getId(),mesto.get().getNaziv(),
                    new DrzavaDTO(mesto.get().getDrzava().getId(), mesto.get().getDrzava().getNaziv(), null));
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Mesto> create(@RequestBody Mesto mesto) {
        try {
            mestoService.save(mesto);
            return new ResponseEntity<Mesto>(mesto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Mesto>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.PUT)
    public ResponseEntity<Mesto> update(@PathVariable("mestoId") Long mestoId,
                                                   @RequestBody Mesto izmenjenoMesto) {
        Mesto mesto = mestoService.findOne(mestoId).orElse(null);
        if (mesto != null) {
            izmenjenoMesto.setId(mestoId);
            mestoService.save(izmenjenoMesto);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<Mesto>(izmenjenoMesto, HttpStatus.OK);
        }
        return new ResponseEntity<Mesto>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Mesto> delete(@PathVariable("mestoId") Long mestoId) {
        if (mestoService.findOne(mestoId).isPresent()) {
            mestoService.delete(mestoId);
            return new ResponseEntity<Mesto>(HttpStatus.OK);
        }
        return new ResponseEntity<Mesto>(HttpStatus.NOT_FOUND);
    }
}