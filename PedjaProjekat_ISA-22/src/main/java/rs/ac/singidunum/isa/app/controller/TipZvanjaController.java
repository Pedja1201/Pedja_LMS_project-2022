package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.aspect.Logged;
import rs.ac.singidunum.isa.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.isa.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.TipZvanja;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.TipZvanjaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/tipZvanja")
public class TipZvanjaController {
    @Autowired
    private TipZvanjaService tipZvanjaService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<TipZvanjaDTO>> getAllTipZvanja() {
        ArrayList<TipZvanjaDTO> tipoviZvanja = new ArrayList<TipZvanjaDTO>();
        for (TipZvanja tipZvanja : tipZvanjaService.findAll()) {
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for (Zvanje zvanje : tipZvanja.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(), zvanje.getNaucnaOblast().getNaziv(), null),
                        null));
            }
            tipoviZvanja.add(
                    new TipZvanjaDTO(tipZvanja.getId(), tipZvanja.getNaziv(), zvanja));
        }

        return new ResponseEntity<Iterable<TipZvanjaDTO>>(tipoviZvanja, HttpStatus.OK);
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
    public ResponseEntity<TipZvanjaDTO> deleteTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId) {
        if (tipZvanjaService.findOne(tipZvanjaId).isPresent()) {
            tipZvanjaService.delete(tipZvanjaId);
            return new ResponseEntity<TipZvanjaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }
}
