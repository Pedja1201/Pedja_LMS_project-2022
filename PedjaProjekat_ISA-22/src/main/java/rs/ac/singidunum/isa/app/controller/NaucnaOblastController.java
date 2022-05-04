package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.isa.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.NaucnaOblast;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.NaucnaOblastService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/naucneOblasti")
public class NaucnaOblastController {
    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<NaucnaOblastDTO>> getAllNaucnaOblast() {
        ArrayList<NaucnaOblastDTO> naucneOblasti = new ArrayList<NaucnaOblastDTO>();
        for (NaucnaOblast naucnaOblast : naucnaOblastService.findAll()) {
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for (Zvanje zvanje : naucnaOblast.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        null,
                        new TipZvanjaDTO(zvanje.getTipZvanja().getId(), zvanje.getTipZvanja().getNaziv(), null)));
            }
            naucneOblasti.add(
                    new NaucnaOblastDTO(naucnaOblast.getId(), naucnaOblast.getNaziv(), zvanja));
        }

        return new ResponseEntity<Iterable<NaucnaOblastDTO>>(naucneOblasti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.GET)
    public ResponseEntity<NaucnaOblastDTO> getNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        Optional<NaucnaOblast> naucnaOblast = naucnaOblastService.findOne(naucnaOblastId);

        NaucnaOblastDTO naucnaOblastDTO;

        if (naucnaOblast.isPresent()) {

            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for (Zvanje zvanje : naucnaOblast.get().getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(),zvanje.getDatumPrestanka(),
                        null,
                        new TipZvanjaDTO(zvanje.getTipZvanja().getId(),
                                zvanje.getTipZvanja().getNaziv(), null)));
            }

            naucnaOblastDTO = new NaucnaOblastDTO(naucnaOblast.get().getId(), naucnaOblast.get().getNaziv(), zvanja);

            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<NaucnaOblast> createNaucnaOblast(@RequestBody NaucnaOblast naucnaOblast) {
        try {
            naucnaOblastService.save(naucnaOblast);
            return new ResponseEntity<NaucnaOblast>(naucnaOblast, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NaucnaOblast>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.PUT)
    public ResponseEntity<NaucnaOblast> updateNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId,
                                               @RequestBody NaucnaOblast izmenjenaNaucnaOblast) {
        NaucnaOblast naucnaOblast = naucnaOblastService.findOne(naucnaOblastId).orElse(null);
        if (naucnaOblast != null) {
            izmenjenaNaucnaOblast.setId(naucnaOblastId);
            izmenjenaNaucnaOblast = naucnaOblastService.save(izmenjenaNaucnaOblast);
            return new ResponseEntity<NaucnaOblast>(izmenjenaNaucnaOblast, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblast>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.DELETE)
    public ResponseEntity<NaucnaOblast> deleteNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        if (naucnaOblastService.findOne(naucnaOblastId).isPresent()) {
            naucnaOblastService.delete(naucnaOblastId);
            return new ResponseEntity<NaucnaOblast>(HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblast>(HttpStatus.NOT_FOUND);
    }
}
