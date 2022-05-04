package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.AdresaDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Adresa;
import rs.ac.singidunum.isa.app.service.AdresaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/adrese")
public class AdresaController {
    @Autowired
    private AdresaService adresaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AdresaDTO>> getAll() {
        Iterable<Adresa> adrese = adresaService.findAll();
        ArrayList<AdresaDTO> racuniDTO = new ArrayList<AdresaDTO>();
        for (Adresa adresa : adrese) {
            racuniDTO.add(new AdresaDTO(adresa.getId(), adresa.getUlica(),adresa.getBroj(),
                    new MestoDTO(adresa.getMesto().getId(), adresa.getMesto().getNaziv(),null)));
        }

        return new ResponseEntity<Iterable<AdresaDTO>>(racuniDTO, HttpStatus.OK);
    }


    @RequestMapping(path = "/{adresaId}", method = RequestMethod.GET)
    public ResponseEntity<AdresaDTO> get(@PathVariable("adresaId") Long adresaId) {
        Optional<Adresa> adresa = adresaService.findOne(adresaId);
        if (adresa.isPresent()) {
            AdresaDTO adresaDTO = new AdresaDTO(adresa.get().getId(),adresa.get().getUlica(),adresa.get().getBroj(),
                    new MestoDTO(adresa.get().getMesto().getId(), adresa.get().getMesto().getNaziv(),null));
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Adresa> create(@RequestBody Adresa adresa) {
        try {
            adresaService.save(adresa);
            return new ResponseEntity<Adresa>(adresa, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Adresa>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.PUT)
    public ResponseEntity<Adresa> update(@PathVariable("adresaId") Long adresaId,
                                                   @RequestBody Adresa izmenjenaAdresa) {
        Adresa adresa = adresaService.findOne(adresaId).orElse(null);
        if (adresa != null) {
            izmenjenaAdresa.setId(adresaId);
            izmenjenaAdresa = adresaService.save(izmenjenaAdresa);
            return new ResponseEntity<Adresa>(izmenjenaAdresa, HttpStatus.OK);
        }
        return new ResponseEntity<Adresa>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Adresa> delete(@PathVariable("adresaId") Long adresaId) {
        if (adresaService.findOne(adresaId).isPresent()) {
            adresaService.delete(adresaId);
            return new ResponseEntity<Adresa>(HttpStatus.OK);
        }
        return new ResponseEntity<Adresa>(HttpStatus.NOT_FOUND);
    }
}
