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
import rs.ac.singidunum.isa.app.dto.DrzavaDTO;
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
        ArrayList<AdresaDTO> adreseDTO = new ArrayList<AdresaDTO>();
        for (Adresa adresa : adrese) {
            adreseDTO.add(new AdresaDTO(adresa.getId(), adresa.getUlica(),adresa.getBroj(),
                    new MestoDTO(adresa.getMesto().getId(), adresa.getMesto().getNaziv(),null)));
        }

        return new ResponseEntity<Iterable<AdresaDTO>>(adreseDTO, HttpStatus.OK);
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
    public ResponseEntity<AdresaDTO> create(@RequestBody Adresa adresa) {
        try {
            adresaService.save(adresa);
            MestoDTO mestoDTO =  new MestoDTO(adresa.getMesto().getId(), adresa.getMesto().getNaziv(),null);

            AdresaDTO adresaDTO = new AdresaDTO(adresa.getId(), adresa.getUlica(),adresa.getBroj(), mestoDTO);

            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.PUT)
    public ResponseEntity<AdresaDTO> update(@PathVariable("adresaId") Long adresaId,
                                                   @RequestBody Adresa izmenjenaAdresa) {
        Adresa adresa = adresaService.findOne(adresaId).orElse(null);
        if (adresa != null) {
            izmenjenaAdresa.setId(adresaId);
            izmenjenaAdresa = adresaService.save(izmenjenaAdresa);
            MestoDTO mestoDTO =  new MestoDTO(izmenjenaAdresa.getMesto().getId(), izmenjenaAdresa.getMesto().getNaziv(),null);

            AdresaDTO adresaDTO = new AdresaDTO(izmenjenaAdresa.getId(), izmenjenaAdresa.getUlica(),izmenjenaAdresa.getBroj(), mestoDTO);
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.DELETE)
    public ResponseEntity<AdresaDTO> delete(@PathVariable("adresaId") Long adresaId) {
        if (adresaService.findOne(adresaId).isPresent()) {
            adresaService.delete(adresaId);
            return new ResponseEntity<AdresaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje mesta po nazivu
    @RequestMapping(path = "/findMesto/{mestoNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AdresaDTO>> findMestoAdresee(@PathVariable("mestoNaziv") String mestoNaziv) {
        ArrayList<AdresaDTO> adreseDTO = new ArrayList<>();
        for(Adresa adresa : adresaService.findMestoAdresee(mestoNaziv)) {
            System.out.println(adresa.getUlica());
            MestoDTO mestoDTO = new MestoDTO(adresa.getMesto().getId(),adresa.getMesto().getNaziv(),
                    new DrzavaDTO(adresa.getMesto().getDrzava().getId(), adresa.getMesto().getDrzava().getNaziv(),null));
            adreseDTO.add(new AdresaDTO(adresa.getId(), adresa.getUlica(), adresa.getBroj(), mestoDTO));
        }
        return new ResponseEntity<Iterable<AdresaDTO>>(adreseDTO, HttpStatus.OK);

    }
}
