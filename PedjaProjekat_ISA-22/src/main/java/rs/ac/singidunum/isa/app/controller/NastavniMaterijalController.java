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
import rs.ac.singidunum.isa.app.model.NastavniMaterijal;
import rs.ac.singidunum.isa.app.service.NastavniMaterijalService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/nastavniMaterijali")
public class NastavniMaterijalController {
    @Autowired
    private NastavniMaterijalService nastavniMaterijalService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<NastavniMaterijalDTO>> getAll(Pageable pageable) {
        Page<NastavniMaterijal> nastavniMaterijali = nastavniMaterijalService.findAll(pageable);
        return new ResponseEntity<Page<NastavniMaterijalDTO>>(
                nastavniMaterijali.map(nastavniMaterijal -> new NastavniMaterijalDTO(nastavniMaterijal.getId(), nastavniMaterijal.getNaziv(),
                        nastavniMaterijal.getAutor(),nastavniMaterijal.getGodinaIzdavanja(),
                        (ArrayList<IshodNastaveDTO>) nastavniMaterijal.getIshodiNastave().stream()
                                .map(ishodNastave -> new IshodNastaveDTO(ishodNastave.getId(), ishodNastave.getOpis(),null))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{nastavniMaterijalId}", method = RequestMethod.GET)
    public ResponseEntity<NastavniMaterijalDTO> get(@PathVariable("nastavniMaterijalId") Long nastavniMaterijalId) {
        Optional<NastavniMaterijal> nastavniMaterijal = nastavniMaterijalService.findOne(nastavniMaterijalId);
        NastavniMaterijalDTO nastavniMaterijalDTO;
        if (nastavniMaterijal.isPresent()) {
            ArrayList<IshodNastaveDTO> ishodiNasatve = new ArrayList<IshodNastaveDTO>();
            for (IshodNastave ishodNastave : nastavniMaterijal.get().getIshodiNastave()) {
                ishodiNasatve.add(new IshodNastaveDTO(ishodNastave.getId(), ishodNastave.getOpis(),null));
            }
            nastavniMaterijalDTO = new NastavniMaterijalDTO(nastavniMaterijal.get().getId(),
                    nastavniMaterijal.get().getNaziv(), nastavniMaterijal.get().getAutor(),
                    nastavniMaterijal.get().getGodinaIzdavanja(), ishodiNasatve);

            return new ResponseEntity<NastavniMaterijalDTO>(nastavniMaterijalDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavniMaterijalDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NastavniMaterijalDTO> create(@RequestBody NastavniMaterijal nastavniMaterijal) {
        try {
            nastavniMaterijalService.save(nastavniMaterijal);
            ArrayList<IshodNastaveDTO> ishodiNastave = new ArrayList<IshodNastaveDTO>();
            for(IshodNastave ishodNastave : nastavniMaterijal.getIshodiNastave()) {
                ishodiNastave.add(new IshodNastaveDTO(ishodNastave.getId(),ishodNastave.getOpis(),null));
            }
            NastavniMaterijalDTO nastavniMaterijalDTO = new NastavniMaterijalDTO(nastavniMaterijal.getId(),
                    nastavniMaterijal.getNaziv(), nastavniMaterijal.getAutor(), nastavniMaterijal.getGodinaIzdavanja(), ishodiNastave);
            return new ResponseEntity<NastavniMaterijalDTO>(nastavniMaterijalDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NastavniMaterijalDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{nastavniMaterijalId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NastavniMaterijalDTO> update(@PathVariable("nastavniMaterijalId") Long nastavniMaterijalId,
                                            @RequestBody NastavniMaterijal izmenjenNastavniMaterijal) {
        NastavniMaterijal nastavniMaterijal = nastavniMaterijalService.findOne(nastavniMaterijalId).orElse(null);
        if (nastavniMaterijal != null) {
            izmenjenNastavniMaterijal.setId(nastavniMaterijalId);
            izmenjenNastavniMaterijal = nastavniMaterijalService.save(izmenjenNastavniMaterijal);
            ArrayList<IshodNastaveDTO> ishodiNastave = new ArrayList<IshodNastaveDTO>();
            for(IshodNastave ishodNastave : izmenjenNastavniMaterijal.getIshodiNastave()) {
                ishodiNastave.add(new IshodNastaveDTO(ishodNastave.getId(),ishodNastave.getOpis(),null));
            }
            NastavniMaterijalDTO nastavniMaterijalDTO = new NastavniMaterijalDTO(izmenjenNastavniMaterijal.getId(),
                    izmenjenNastavniMaterijal.getNaziv(),  izmenjenNastavniMaterijal.getAutor(),
                    izmenjenNastavniMaterijal.getGodinaIzdavanja(), ishodiNastave);
            return new ResponseEntity<NastavniMaterijalDTO>(nastavniMaterijalDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavniMaterijalDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavniMaterijalId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NastavniMaterijalDTO> delete(@PathVariable("nastavniMaterijalId") Long nastavniMaterijalId) {
        if (nastavniMaterijalService.findOne(nastavniMaterijalId).isPresent()) {
            nastavniMaterijalService.delete(nastavniMaterijalId);
            return new ResponseEntity<NastavniMaterijalDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NastavniMaterijalDTO>(HttpStatus.NOT_FOUND);
    }
}
