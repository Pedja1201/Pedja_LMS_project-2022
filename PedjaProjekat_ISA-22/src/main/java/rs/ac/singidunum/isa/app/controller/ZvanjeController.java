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
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.ZvanjeService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/zvanja")
public class ZvanjeController {
    @Autowired
    private ZvanjeService zvanjeService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ZvanjeDTO>> getAll() {

        ArrayList<ZvanjeDTO> karte = new ArrayList<ZvanjeDTO>();

        for (Zvanje zvanje : zvanjeService.findAll()) {
            karte.add(new ZvanjeDTO(zvanje.getId(),zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                    new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(), zvanje.getNaucnaOblast().getNaziv(),null),
                    new TipZvanjaDTO(zvanje.getTipZvanja().getId(), zvanje.getTipZvanja().getNaziv(),null)));
        }

        return new ResponseEntity<Iterable<ZvanjeDTO>>(karte, HttpStatus.OK);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.GET)
    public ResponseEntity<ZvanjeDTO> get(@PathVariable("zvanjeId") Long zvanjeId) {
        Optional<Zvanje> zvanje = zvanjeService.findOne(zvanjeId);
        if (zvanje.isPresent()) {
            ZvanjeDTO zvanjeDTO = new ZvanjeDTO(zvanje.get().getId(), zvanje.get().getDatumIzbora(),zvanje.get().getDatumPrestanka(),
                    new NaucnaOblastDTO(zvanje.get().getNaucnaOblast().getId(), zvanje.get().getNaucnaOblast().getNaziv(),null),
                    new TipZvanjaDTO(zvanje.get().getTipZvanja().getId(), zvanje.get().getTipZvanja().getNaziv(),null));
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Zvanje> create(@RequestBody Zvanje zvanje) {
        try {
            zvanjeService.save(zvanje);
            return new ResponseEntity<Zvanje>(zvanje, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Zvanje>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.PUT)
    public ResponseEntity<Zvanje> update(@PathVariable("zvanjeId") Long zvanjeId,
                                             @RequestBody Zvanje izmenjenoZvanje) {
        Zvanje zvanje = zvanjeService.findOne(zvanjeId).orElse(null);
        if (zvanje != null) {
            izmenjenoZvanje.setId(zvanjeId);
            zvanjeService.save(izmenjenoZvanje); //FIXME: Sa ovim radi bez BUG-a (Beskonacna rekurzija!)
            return new ResponseEntity<Zvanje>(izmenjenoZvanje, HttpStatus.OK);
        }
        return new ResponseEntity<Zvanje>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Zvanje> delete(@PathVariable("zvanjeId") Long zvanjeId) {
        if (zvanjeService.findOne(zvanjeId).isPresent()) {
            zvanjeService.delete(zvanjeId);
            return new ResponseEntity<Zvanje>(HttpStatus.OK);
        }
        return new ResponseEntity<Zvanje>(HttpStatus.NOT_FOUND);
    }

}
