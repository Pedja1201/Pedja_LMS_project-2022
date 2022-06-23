package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.aspect.Logged;
import rs.ac.singidunum.isa.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.isa.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.TipZvanja;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.TipZvanjaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/tipoviZvanja")
public class TipZvanjaController {
    @Autowired
    private TipZvanjaService tipZvanjaService;

    //    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<TipZvanjaDTO>> getAllTipZvanja(Pageable pageable) {
        Page<TipZvanja> tipoviZvanja = tipZvanjaService.findAll(pageable);
        return new ResponseEntity<Page<TipZvanjaDTO>>(
                tipoviZvanja.map(tipZvanja -> new TipZvanjaDTO(tipZvanja.getId(), tipZvanja.getNaziv(),
                        (ArrayList<ZvanjeDTO>) tipZvanja.getZvanja().stream()
                                .map(zvanja -> new ZvanjeDTO(zvanja.getId(), zvanja.getDatumIzbora(),
                                        zvanja.getDatumPrestanka(),
                                        new NaucnaOblastDTO(zvanja.getNaucnaOblast().getId(),
                                                zvanja.getNaucnaOblast().getNaziv(), null),null))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.GET)
    public ResponseEntity<TipZvanjaDTO> getTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId) {
        Optional<TipZvanja> tipZvanja = tipZvanjaService.findOne(tipZvanjaId);
        TipZvanjaDTO tipZvanjaDTO;
        if (tipZvanja.isPresent()) {
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for (Zvanje zvanje : tipZvanja.get().getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(),zvanje.getDatumPrestanka(),
                        new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(),
                                zvanje.getNaucnaOblast().getNaziv(), null),null));
            }
            tipZvanjaDTO = new TipZvanjaDTO(tipZvanja.get().getId(), tipZvanja.get().getNaziv(), zvanja);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<TipZvanjaDTO> createTipZvanja(@RequestBody TipZvanja tipZvanja) {
        try {
            tipZvanjaService.save(tipZvanja);
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for(Zvanje zvanje : tipZvanja.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(),
                                zvanje.getNaucnaOblast().getNaziv(), null),null));
            }
            TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(tipZvanja.getId(), tipZvanja.getNaziv(),  zvanja);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO,HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<TipZvanjaDTO> updateTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId,
                                                        @RequestBody TipZvanja izmenjenTipZvanja) {
        TipZvanja tipZvanja = tipZvanjaService.findOne(tipZvanjaId).orElse(null);
        if (tipZvanja != null) {
            izmenjenTipZvanja.setId(tipZvanjaId);
            izmenjenTipZvanja = tipZvanjaService.save(izmenjenTipZvanja);
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for(Zvanje zvanje : tipZvanja.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(),
                                zvanje.getNaucnaOblast().getNaziv(), null),null));
            }
            TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(izmenjenTipZvanja.getId(), izmenjenTipZvanja.getNaziv(),  zvanja);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<TipZvanjaDTO> deleteTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId) {
        if (tipZvanjaService.findOne(tipZvanjaId).isPresent()) {
            tipZvanjaService.delete(tipZvanjaId);
            return new ResponseEntity<TipZvanjaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }
}
