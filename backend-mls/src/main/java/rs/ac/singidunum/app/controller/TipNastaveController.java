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
import rs.ac.singidunum.app.dto.TipNastaveDTO;
import rs.ac.singidunum.app.model.TipNastave;
import rs.ac.singidunum.app.service.TipNastaveService;

import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<TipNastaveDTO> delete(@PathVariable("tipNastaveId") Long tipNastaveId) {
        if (tipNastaveService.findOne(tipNastaveId).isPresent()) {
            tipNastaveService.delete(tipNastaveId);
            return new ResponseEntity<TipNastaveDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }
}
