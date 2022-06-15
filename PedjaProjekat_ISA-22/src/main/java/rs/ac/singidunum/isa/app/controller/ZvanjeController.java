package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.aspect.LoggedZvanje;
import rs.ac.singidunum.isa.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.isa.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.ZvanjeService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/zvanja")
public class ZvanjeController {
    @Autowired
    private ZvanjeService zvanjeService;

//    @LoggedZvanje //TODO:Pokrenuti Artemis ukoliko koristimo izvrsavanje metode
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<ZvanjeDTO>> getAll(Pageable pageable) {
        Page<Zvanje> zvanje = zvanjeService.findAll(pageable);
        Page<ZvanjeDTO> zvanja = zvanje.map(new Function<Zvanje, ZvanjeDTO>() {
            public ZvanjeDTO apply(Zvanje zvanje) {
                ZvanjeDTO zvanjeDTO = new ZvanjeDTO(zvanje.getId(),zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(), zvanje.getNaucnaOblast().getNaziv(),null),
                        new TipZvanjaDTO(zvanje.getTipZvanja().getId(), zvanje.getTipZvanja().getNaziv(),null)
                );
                // Conversion logic
                return zvanjeDTO;
            }
        });
        return new ResponseEntity<Page<ZvanjeDTO>>(zvanja, HttpStatus.OK);
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
    public ResponseEntity<ZvanjeDTO> create(@RequestBody Zvanje zvanje) {
        try {
            zvanjeService.save(zvanje);
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(), zvanje.getNaucnaOblast().getNaziv(),null);
            TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(zvanje.getTipZvanja().getId(), zvanje.getTipZvanja().getNaziv(),null);
            ZvanjeDTO zvanjeDTO = new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),naucnaOblastDTO, tipZvanjaDTO);

            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.PUT)
    public ResponseEntity<ZvanjeDTO> update(@PathVariable("zvanjeId") Long zvanjeId,
                                            @RequestBody Zvanje izmenjenoZvanje) {
        Zvanje zvanje = zvanjeService.findOne(zvanjeId).orElse(null);
        if (zvanje != null) {
            izmenjenoZvanje.setId(zvanjeId);
            izmenjenoZvanje = zvanjeService.save(izmenjenoZvanje);
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(izmenjenoZvanje.getNaucnaOblast().getId(), izmenjenoZvanje.getNaucnaOblast().getNaziv(),null);
            TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(izmenjenoZvanje.getTipZvanja().getId(), izmenjenoZvanje.getTipZvanja().getNaziv(),null);
            ZvanjeDTO zvanjeDTO = new ZvanjeDTO(izmenjenoZvanje.getId(), izmenjenoZvanje.getDatumIzbora(), izmenjenoZvanje.getDatumPrestanka(),naucnaOblastDTO, tipZvanjaDTO);
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.DELETE)
    public ResponseEntity<ZvanjeDTO> delete(@PathVariable("zvanjeId") Long zvanjeId) {
        if (zvanjeService.findOne(zvanjeId).isPresent()) {
            zvanjeService.delete(zvanjeId);
            return new ResponseEntity<ZvanjeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje naucnih oblasti po nazivu
    @RequestMapping(path = "/findNaucnaOblast/{nauznaOblastNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ZvanjeDTO>> findNaucnaOblast(@PathVariable("nauznaOblastNaziv") String nauznaOblastNaziv) {
        ArrayList<ZvanjeDTO> zvanjaDTO = new ArrayList<>();
        for(Zvanje zvanje : zvanjeService.findNaucnaOblast(nauznaOblastNaziv)) {
            System.out.println(zvanje.getNaucnaOblast());
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(zvanje.getNaucnaOblast().getId(),zvanje.getNaucnaOblast().getNaziv(), null);

            zvanjaDTO.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(), naucnaOblastDTO, null));
        }
        return new ResponseEntity<Iterable<ZvanjeDTO>>(zvanjaDTO, HttpStatus.OK);

    }

}
