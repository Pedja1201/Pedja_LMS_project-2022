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
import rs.ac.singidunum.isa.app.model.Drzava;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.DrzavaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/drzave")
public class DrzavaController {
    @Autowired
    private DrzavaService drzavaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<DrzavaDTO>> getAll() {
        ArrayList<DrzavaDTO> drzave = new ArrayList<DrzavaDTO>();
        for (Drzava drzava : drzavaService.findAll()) {
            ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();
            for (Mesto mesto : drzava.getMesta()) {
                mesta.add(new MestoDTO(mesto.getId(),mesto.getNaziv(),null));
            }
            drzave.add(
                    new DrzavaDTO(drzava.getId(), drzava.getNaziv(), mesta));
        }

        return new ResponseEntity<Iterable<DrzavaDTO>>(drzave, HttpStatus.OK);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.GET)
    public ResponseEntity<DrzavaDTO> get(@PathVariable("drzavaId") Long drzavaId) {
        Optional<Drzava> drzava = drzavaService.findOne(drzavaId);
        DrzavaDTO drzavaDTO;
        if (drzava.isPresent()) {
            ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();
            for (Mesto mesto : drzava.get().getMesta()) {
                mesta.add(new MestoDTO(mesto.getId(), mesto.getNaziv(),null));
            }
            drzavaDTO = new DrzavaDTO(drzava.get().getId(), drzava.get().getNaziv(), mesta);

            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Drzava> create(@RequestBody Drzava drzava) {
        try {
            drzavaService.save(drzava);
            return new ResponseEntity<Drzava>(drzava, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Drzava>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.PUT)
    public ResponseEntity<Drzava> update(@PathVariable("drzavaId") Long drzavaId,
                                                   @RequestBody Drzava izmenjenaDrzava) {
        Drzava drzava = drzavaService.findOne(drzavaId).orElse(null);
        if (drzava != null) {
            izmenjenaDrzava.setId(drzavaId);
            izmenjenaDrzava = drzavaService.save(izmenjenaDrzava);
            return new ResponseEntity<Drzava>(izmenjenaDrzava, HttpStatus.OK);
        }
        return new ResponseEntity<Drzava>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Drzava> delete(@PathVariable("drzavaId") Long drzavaId) {
        if (drzavaService.findOne(drzavaId).isPresent()) {
            drzavaService.delete(drzavaId);
            return new ResponseEntity<Drzava>(HttpStatus.OK);
        }
        return new ResponseEntity<Drzava>(HttpStatus.NOT_FOUND);
    }
}
