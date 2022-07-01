package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.IshodNastaveDTO;
import rs.ac.singidunum.isa.app.dto.NastavniMaterijalDTO;
import rs.ac.singidunum.isa.app.model.IshodNastave;
import rs.ac.singidunum.isa.app.service.IshodNastaveService;

import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/ishodiNastave")
public class IshodNastaveController {
    @Autowired
    private IshodNastaveService ishodNastaveService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<IshodNastaveDTO>> getAll(Pageable pageable) {
        Page<IshodNastave> ishodNastave = ishodNastaveService.findAll(pageable);
        Page<IshodNastaveDTO> ishodiNastave = ishodNastave.map(new Function<IshodNastave, IshodNastaveDTO>() {
            public IshodNastaveDTO apply(IshodNastave ishodNastave) {
                IshodNastaveDTO ishodNastaveDTO = new IshodNastaveDTO(ishodNastave.getId(), ishodNastave.getOpis(),
                        new NastavniMaterijalDTO(ishodNastave.getNastavniMaterijal().getId(),
                                ishodNastave.getNastavniMaterijal().getNaziv(),ishodNastave.getNastavniMaterijal().getAutor(),
                                ishodNastave.getNastavniMaterijal().getGodinaIzdavanja(),null)
                );
                // Conversion logic
                return ishodNastaveDTO;
            }
        });
        return new ResponseEntity<Page<IshodNastaveDTO>>(ishodiNastave, HttpStatus.OK);
    }


    @RequestMapping(path = "/{ishodNastaveId}", method = RequestMethod.GET)
    public ResponseEntity<IshodNastaveDTO> get(@PathVariable("ishodNastaveId") Long ishodNastaveId) {
        Optional<IshodNastave> ishodNastave = ishodNastaveService.findOne(ishodNastaveId);
        if (ishodNastave.isPresent()) {
            IshodNastaveDTO ishodNastaveDTO = new IshodNastaveDTO(ishodNastave.get().getId(),ishodNastave.get().getOpis(),
                    new NastavniMaterijalDTO(ishodNastave.get().getNastavniMaterijal().getId(),
                            ishodNastave.get().getNastavniMaterijal().getNaziv(),ishodNastave.get().getNastavniMaterijal().getAutor(),
                            ishodNastave.get().getNastavniMaterijal().getGodinaIzdavanja(),null));
            return new ResponseEntity<IshodNastaveDTO>(ishodNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodNastaveDTO> create(@RequestBody IshodNastave ishodNastave) {
        try {
            ishodNastaveService.save(ishodNastave);
            NastavniMaterijalDTO nastavniMaterijalDTO =  new NastavniMaterijalDTO(ishodNastave.getNastavniMaterijal().getId(),
                    ishodNastave.getNastavniMaterijal().getNaziv(),ishodNastave.getNastavniMaterijal().getAutor(),
                    ishodNastave.getNastavniMaterijal().getGodinaIzdavanja(), null);

            IshodNastaveDTO ishodNastaveDTO = new IshodNastaveDTO(ishodNastave.getId(),
                    ishodNastave.getOpis(), nastavniMaterijalDTO);

            return new ResponseEntity<IshodNastaveDTO>(ishodNastaveDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<IshodNastaveDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{ishodNastaveId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodNastaveDTO> update(@PathVariable("ishodNastaveId") Long ishodNastaveId,
                                            @RequestBody IshodNastave izmenjenIshodNastave) {
        IshodNastave ishodNastave = ishodNastaveService.findOne(ishodNastaveId).orElse(null);
        if (ishodNastave != null) {
            izmenjenIshodNastave.setId(ishodNastaveId);
            izmenjenIshodNastave = ishodNastaveService.save(izmenjenIshodNastave);
            NastavniMaterijalDTO nastavniMaterijalDTO =  new NastavniMaterijalDTO(izmenjenIshodNastave.getNastavniMaterijal().getId(),
                    izmenjenIshodNastave.getNastavniMaterijal().getNaziv(),izmenjenIshodNastave.getNastavniMaterijal().getAutor(),
                    izmenjenIshodNastave.getNastavniMaterijal().getGodinaIzdavanja(), null);

            IshodNastaveDTO ishodNastaveDTO = new IshodNastaveDTO(izmenjenIshodNastave.getId(),
                    izmenjenIshodNastave.getOpis(), nastavniMaterijalDTO);
            return new ResponseEntity<IshodNastaveDTO>(ishodNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{ishodNastaveId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodNastaveDTO> delete(@PathVariable("ishodNastaveId") Long ishodNastaveId) {
        if (ishodNastaveService.findOne(ishodNastaveId).isPresent()) {
            ishodNastaveService.delete(ishodNastaveId);
            return new ResponseEntity<IshodNastaveDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<IshodNastaveDTO>(HttpStatus.NOT_FOUND);
    }
}
