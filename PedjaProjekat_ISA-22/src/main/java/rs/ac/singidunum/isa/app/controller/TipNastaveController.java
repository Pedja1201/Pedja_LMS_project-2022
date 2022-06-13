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
import rs.ac.singidunum.isa.app.dto.TipNastaveDTO;
import rs.ac.singidunum.isa.app.model.TipNastave;
import rs.ac.singidunum.isa.app.service.TipNastaveService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/tipoviNastave")
public class TipNastaveController {
    @Autowired
    private TipNastaveService tipNastaveService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<TipNastaveDTO>> getAll(Pageable pageable) {
        Page<TipNastave> tipNastave = tipNastaveService.findAll(pageable);
        Page<TipNastaveDTO> tipoviNastave = tipNastave.map(new Function<TipNastave, TipNastaveDTO>() {
            public TipNastaveDTO apply(TipNastave tipNastave) {
                TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(tipNastave.getId(), tipNastave.getNaziv()
                );
                // Conversion logic
                return tipNastaveDTO;
            }
        });
        return new ResponseEntity<Page<TipNastaveDTO>>(tipoviNastave, HttpStatus.OK);
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
    public ResponseEntity<TipNastaveDTO> create(@RequestBody TipNastave tipNastave) {
        try {
            tipNastaveService.save(tipNastave);
            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(tipNastave.getId(), tipNastave.getNaziv());

            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.PUT)
    public ResponseEntity<TipNastaveDTO> update(@PathVariable("tipNastaveId") Long tipNastaveId,
                                             @RequestBody TipNastave izmenjenTipNastave) {
        TipNastave tipNastave = tipNastaveService.findOne(tipNastaveId).orElse(null);
        if (tipNastave != null) {
            izmenjenTipNastave.setId(tipNastaveId);
            izmenjenTipNastave = tipNastaveService.save(izmenjenTipNastave);
            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(izmenjenTipNastave.getId(), izmenjenTipNastave.getNaziv());

            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.DELETE)
    public ResponseEntity<TipNastaveDTO> delete(@PathVariable("tipNastaveId") Long tipNastaveId) {
        if (tipNastaveService.findOne(tipNastaveId).isPresent()) {
            tipNastaveService.delete(tipNastaveId);
            return new ResponseEntity<TipNastaveDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }
}
