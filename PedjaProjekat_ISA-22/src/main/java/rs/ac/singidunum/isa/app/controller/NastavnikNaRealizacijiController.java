package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.NastavnikDTO;
import rs.ac.singidunum.isa.app.dto.NastavnikNaRealizacijiDTO;
import rs.ac.singidunum.isa.app.dto.TipNastaveDTO;
import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;
import rs.ac.singidunum.isa.app.service.NastavnikNaRealizacijiService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/nastavniciNaRealizaciji")
public class NastavnikNaRealizacijiController {
    @Autowired
    private NastavnikNaRealizacijiService nastavnikNaRealizacijiService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<NastavnikNaRealizacijiDTO>> getAll() {
        ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji = new ArrayList<NastavnikNaRealizacijiDTO>();

        for (NastavnikNaRealizaciji nastavnikNaRealizaciji : nastavnikNaRealizacijiService.findAll()) {
            nastavniciNaRealizaciji.add(new NastavnikNaRealizacijiDTO(nastavnikNaRealizaciji.getId(),
                    nastavnikNaRealizaciji.getBrojCasova(),
                    new NastavnikDTO(nastavnikNaRealizaciji.getNastavnik().getId(),nastavnikNaRealizaciji.getNastavnik().getKorisnickoIme(),
                            nastavnikNaRealizaciji.getNastavnik().getLozinka(), nastavnikNaRealizaciji.getNastavnik().getIme(),
                            nastavnikNaRealizaciji.getNastavnik().getBiografija(), nastavnikNaRealizaciji.getNastavnik().getJmbg(),
                            null,null),
                    new TipNastaveDTO(nastavnikNaRealizaciji.getTipNastave().getId(), nastavnikNaRealizaciji.getTipNastave().getNaziv())));
        }

        return new ResponseEntity<Iterable<NastavnikNaRealizacijiDTO>>(nastavniciNaRealizaciji, HttpStatus.OK);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.GET)
    public ResponseEntity<NastavnikNaRealizacijiDTO> get(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId) {
        Optional<NastavnikNaRealizaciji> nastavnikNaRealizaciji = nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId);
        if (nastavnikNaRealizaciji.isPresent()) {
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(nastavnikNaRealizaciji.get().getId(),
                    nastavnikNaRealizaciji.get().getBrojCasova(),
                    new NastavnikDTO(nastavnikNaRealizaciji.get().getNastavnik().getId(), nastavnikNaRealizaciji.get().getNastavnik().getKorisnickoIme(),
                            nastavnikNaRealizaciji.get().getNastavnik().getLozinka(),nastavnikNaRealizaciji.get().getNastavnik().getIme(),
                            nastavnikNaRealizaciji.get().getNastavnik().getBiografija(), nastavnikNaRealizaciji.get().getNastavnik().getJmbg(),
                            null,null),
                    new TipNastaveDTO(nastavnikNaRealizaciji.get().getTipNastave().getId(),
                            nastavnikNaRealizaciji.get().getTipNastave().getNaziv()));
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<NastavnikNaRealizacijiDTO> create(@RequestBody NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        try {
            nastavnikNaRealizacijiService.save(nastavnikNaRealizaciji);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(nastavnikNaRealizaciji.getNastavnik().getId(),nastavnikNaRealizaciji.getNastavnik().getKorisnickoIme(),
                    nastavnikNaRealizaciji.getNastavnik().getLozinka(), nastavnikNaRealizaciji.getNastavnik().getIme(),
                    nastavnikNaRealizaciji.getNastavnik().getBiografija(), nastavnikNaRealizaciji.getNastavnik().getJmbg(),
                    null,null);
            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(nastavnikNaRealizaciji.getTipNastave().getId(), nastavnikNaRealizaciji.getTipNastave().getNaziv());

            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(nastavnikNaRealizaciji.getId(),
                    nastavnikNaRealizaciji.getBrojCasova(),  nastavnikDTO, tipNastaveDTO);

            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.PUT)
    public ResponseEntity<NastavnikNaRealizacijiDTO> update(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId,
                                                        @RequestBody NastavnikNaRealizaciji izmenjenNastavnikNaRealizaciji) {
        NastavnikNaRealizaciji nastavnikNaRealizaciji = nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId).orElse(null);
        if (nastavnikNaRealizaciji != null) {
            izmenjenNastavnikNaRealizaciji.setId(nastavnikNaRealizacijiId);
            izmenjenNastavnikNaRealizaciji = nastavnikNaRealizacijiService.save(izmenjenNastavnikNaRealizaciji);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(izmenjenNastavnikNaRealizaciji.getNastavnik().getId(),izmenjenNastavnikNaRealizaciji.getNastavnik().getKorisnickoIme(),
                    izmenjenNastavnikNaRealizaciji.getNastavnik().getLozinka(), izmenjenNastavnikNaRealizaciji.getNastavnik().getIme(),
                    izmenjenNastavnikNaRealizaciji.getNastavnik().getBiografija(), izmenjenNastavnikNaRealizaciji.getNastavnik().getJmbg(),
                    null,null);
            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(izmenjenNastavnikNaRealizaciji.getTipNastave().getId(), izmenjenNastavnikNaRealizaciji.getTipNastave().getNaziv());

            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(izmenjenNastavnikNaRealizaciji.getId(),
                    izmenjenNastavnikNaRealizaciji.getBrojCasova(),  nastavnikDTO, tipNastaveDTO);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.DELETE)
    public ResponseEntity<NastavnikNaRealizacijiDTO> delete(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId) {
        if (nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId).isPresent()) {
            nastavnikNaRealizacijiService.delete(nastavnikNaRealizacijiId);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }
}
