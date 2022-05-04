package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.TipNastaveDTO;
import rs.ac.singidunum.isa.app.model.TipNastave;
import rs.ac.singidunum.isa.app.service.TipNastaveService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/tipNastave")
public class TipNastaveController {
    @Autowired
    private TipNastaveService tipNastaveService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<TipNastaveDTO>> getAll() {
        ArrayList<TipNastaveDTO> tipoviNastave = new ArrayList<TipNastaveDTO>();

        for (TipNastave tipNastave : tipNastaveService.findAll()) {
            tipoviNastave.add(new TipNastaveDTO(tipNastave.getId(), tipNastave.getNaziv()));
        }

        return new ResponseEntity<Iterable<TipNastaveDTO>>(tipoviNastave, HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.GET)
    public ResponseEntity<TipNastaveDTO> get(@PathVariable("tipNastaveId") Long tipNastaveId) {
        Optional<TipNastave> tipNastave = tipNastaveService.findOne(tipNastaveId);
        if (tipNastave.isPresent()) {
            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(tipNastave.get().getId(), tipNastave.get().getNaziv());

            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TipNastave> create(@RequestBody TipNastave tipNastave) {
        try {
            tipNastaveService.save(tipNastave);
            return new ResponseEntity<TipNastave>(tipNastave, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipNastave>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.PUT)
    public ResponseEntity<TipNastave> update(@PathVariable("tipNastaveId") Long tipNastaveId,
                                             @RequestBody TipNastave izmenjenTipNastave) {
        TipNastave tipNastave = tipNastaveService.findOne(tipNastaveId).orElse(null);
        if (tipNastave != null) {
            izmenjenTipNastave.setId(tipNastaveId);
            tipNastaveService.save(izmenjenTipNastave);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<TipNastave>(izmenjenTipNastave, HttpStatus.OK);
        }
        return new ResponseEntity<TipNastave>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.DELETE)
    public ResponseEntity<TipNastave> delete(@PathVariable("tipNastaveId") Long tipNastaveId) {
        if (tipNastaveService.findOne(tipNastaveId).isPresent()) {
            tipNastaveService.delete(tipNastaveId);
            return new ResponseEntity<TipNastave>(HttpStatus.OK);
        }
        return new ResponseEntity<TipNastave>(HttpStatus.NOT_FOUND);
    }
}
