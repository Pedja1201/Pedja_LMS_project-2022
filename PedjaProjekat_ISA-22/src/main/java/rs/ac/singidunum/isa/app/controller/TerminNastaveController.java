package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.IshodNastaveDTO;
import rs.ac.singidunum.isa.app.dto.NastavniMaterijalDTO;
import rs.ac.singidunum.isa.app.dto.TerminNastaveDTO;
import rs.ac.singidunum.isa.app.dto.TipNastaveDTO;
import rs.ac.singidunum.isa.app.model.TerminNastave;
import rs.ac.singidunum.isa.app.service.TerminNastaveService;


import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/terminiNastave")
public class TerminNastaveController {
    @Autowired
    private TerminNastaveService terminNastaveService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<TerminNastaveDTO>> getAll(Pageable pageable) {
        Page<TerminNastave> terminNastave = terminNastaveService.findAll(pageable);
        Page<TerminNastaveDTO> terminiNastave = terminNastave.map(new Function<TerminNastave, TerminNastaveDTO>() {
            public TerminNastaveDTO apply(TerminNastave terminNastave) {
                TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(terminNastave.getId(),
                        terminNastave.getVremePocetka(), terminNastave.getVremeKraja(),
                        new IshodNastaveDTO(terminNastave.getIshodNastave().getId(), terminNastave.getIshodNastave().getOpis(),
                                new NastavniMaterijalDTO(terminNastave.getIshodNastave().getNastavniMaterijal().getId(),
                                        terminNastave.getIshodNastave().getNastavniMaterijal().getNaziv(),
                                        terminNastave.getIshodNastave().getNastavniMaterijal().getAutor(),
                                        terminNastave.getIshodNastave().getNastavniMaterijal().getGodinaIzdavanja(),null)),
                        new TipNastaveDTO(terminNastave.getTipNastave().getId(), terminNastave.getTipNastave().getNaziv())
                );
                // Conversion logic
                return terminNastaveDTO;
            }
        });
        return new ResponseEntity<Page<TerminNastaveDTO>>(terminiNastave, HttpStatus.OK);
    }


    @RequestMapping(path = "/{terminNastaveId}", method = RequestMethod.GET)
    public ResponseEntity<TerminNastaveDTO> get(@PathVariable("terminNastaveId") Long terminNastaveId) {
        Optional<TerminNastave> terminNastave = terminNastaveService.findOne(terminNastaveId);
        if (terminNastave.isPresent()) {
            TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(terminNastave.get().getId(),
                    terminNastave.get().getVremePocetka(), terminNastave.get().getVremeKraja(),
                    new IshodNastaveDTO(terminNastave.get().getIshodNastave().getId(), terminNastave.get().getIshodNastave().getOpis(),
                            new NastavniMaterijalDTO(terminNastave.get().getIshodNastave().getNastavniMaterijal().getId(),terminNastave.get().getIshodNastave().getNastavniMaterijal().getNaziv(),
                                    terminNastave.get().getIshodNastave().getNastavniMaterijal().getAutor(), terminNastave.get().getIshodNastave().getNastavniMaterijal().getGodinaIzdavanja(),null )),
                    new TipNastaveDTO(terminNastave.get().getTipNastave().getId(),terminNastave.get().getTipNastave().getNaziv()));
            return new ResponseEntity<TerminNastaveDTO>(terminNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TerminNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TerminNastaveDTO> create(@RequestBody TerminNastave terminNastave) {
        try {
            terminNastaveService.save(terminNastave);
            IshodNastaveDTO ishodNastaveDTO =  new IshodNastaveDTO(terminNastave.getIshodNastave().getId(),
                    terminNastave.getIshodNastave().getOpis(),
                    new NastavniMaterijalDTO(terminNastave.getIshodNastave().getNastavniMaterijal().getId(),terminNastave.getIshodNastave().getNastavniMaterijal().getNaziv(),
                            terminNastave.getIshodNastave().getNastavniMaterijal().getAutor(),terminNastave.getIshodNastave().getNastavniMaterijal().getGodinaIzdavanja(),null));
            TipNastaveDTO tipNastaveDTO =  new TipNastaveDTO(terminNastave.getTipNastave().getId(),
                    terminNastave.getTipNastave().getNaziv());

            TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(terminNastave.getId(), terminNastave.getVremePocetka(),
                    terminNastave.getVremeKraja(), ishodNastaveDTO, tipNastaveDTO);

            return new ResponseEntity<TerminNastaveDTO>(terminNastaveDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TerminNastaveDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{terminNastaveId}", method = RequestMethod.PUT)
    public ResponseEntity<TerminNastaveDTO> update(@PathVariable("terminNastaveId") Long terminNastaveId,
                                            @RequestBody TerminNastave izmenjenTerminNastave) {
        TerminNastave terminNastave = terminNastaveService.findOne(terminNastaveId).orElse(null);
        if (terminNastave != null) {
            izmenjenTerminNastave.setId(terminNastaveId);
            izmenjenTerminNastave = terminNastaveService.save(izmenjenTerminNastave);
            IshodNastaveDTO ishodNastaveDTO =  new IshodNastaveDTO(izmenjenTerminNastave.getIshodNastave().getId(),
                    izmenjenTerminNastave.getIshodNastave().getOpis(),
                                    new NastavniMaterijalDTO(izmenjenTerminNastave.getIshodNastave().getNastavniMaterijal().getId(),izmenjenTerminNastave.getIshodNastave().getNastavniMaterijal().getNaziv(),
                                            izmenjenTerminNastave.getIshodNastave().getNastavniMaterijal().getAutor(),izmenjenTerminNastave.getIshodNastave().getNastavniMaterijal().getGodinaIzdavanja(),null));
            TipNastaveDTO tipNastaveDTO =  new TipNastaveDTO(izmenjenTerminNastave.getTipNastave().getId(),
                    izmenjenTerminNastave.getTipNastave().getNaziv());

            TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(izmenjenTerminNastave.getId(), izmenjenTerminNastave.getVremePocetka(),
                    izmenjenTerminNastave.getVremeKraja(), ishodNastaveDTO, tipNastaveDTO);
            return new ResponseEntity<TerminNastaveDTO>(terminNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TerminNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{terminNastaveId}", method = RequestMethod.DELETE)
    public ResponseEntity<TerminNastaveDTO> delete(@PathVariable("terminNastaveId") Long terminNastaveId) {
        if (terminNastaveService.findOne(terminNastaveId).isPresent()) {
            terminNastaveService.delete(terminNastaveId);
            return new ResponseEntity<TerminNastaveDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TerminNastaveDTO>(HttpStatus.NOT_FOUND);
    }
}
