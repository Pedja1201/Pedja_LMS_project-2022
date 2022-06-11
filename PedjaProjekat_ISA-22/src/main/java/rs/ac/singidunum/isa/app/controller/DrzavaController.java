package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.DrzavaDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Drzava;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.DrzavaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/drzave")
public class DrzavaController {
    @Autowired
    private DrzavaService drzavaService;

    @CrossOrigin
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<DrzavaDTO>> getAll(Pageable pageable) {
        Page<Drzava> drzave = drzavaService.findAll(pageable);
        return new ResponseEntity<Page<DrzavaDTO>>(
                drzave.map(drzava -> new DrzavaDTO(drzava.getId(), drzava.getNaziv(),
                        (ArrayList<MestoDTO>) drzava.getMesta().stream()
                                .map(mesto -> new MestoDTO(mesto.getId(), mesto.getNaziv(),null))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
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
    public ResponseEntity<DrzavaDTO> create(@RequestBody Drzava drzava) {
        try {
            drzavaService.save(drzava);
            ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();
            for(Mesto mesto : drzava.getMesta()) {
                mesta.add(new MestoDTO(mesto.getId(),mesto.getNaziv(),null));
            }
            DrzavaDTO drzavaDTO = new DrzavaDTO(drzava.getId(), drzava.getNaziv(), mesta);
            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.PUT)
    public ResponseEntity<DrzavaDTO> update(@PathVariable("drzavaId") Long drzavaId,
                                                   @RequestBody Drzava izmenjenaDrzava) {
        Drzava drzava = drzavaService.findOne(drzavaId).orElse(null);
        if (drzava != null) {
            izmenjenaDrzava.setId(drzavaId);
            izmenjenaDrzava = drzavaService.save(izmenjenaDrzava);
            ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();
            for(Mesto mesto : izmenjenaDrzava.getMesta()) {
                mesta.add(new MestoDTO(mesto.getId(),mesto.getNaziv(),null));
            }
            DrzavaDTO drzavaDTO = new DrzavaDTO(izmenjenaDrzava.getId(), izmenjenaDrzava.getNaziv(), mesta);
            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.DELETE)
    public ResponseEntity<DrzavaDTO> delete(@PathVariable("drzavaId") Long drzavaId) {
        if (drzavaService.findOne(drzavaId).isPresent()) {
            drzavaService.delete(drzavaId);
            return new ResponseEntity<DrzavaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);
    }
}
