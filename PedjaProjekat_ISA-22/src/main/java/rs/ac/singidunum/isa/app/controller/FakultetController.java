package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.aspect.Logged;
import rs.ac.singidunum.isa.app.dto.AdresaDTO;
import rs.ac.singidunum.isa.app.dto.FakultetDTO;
import rs.ac.singidunum.isa.app.dto.NastavnikDTO;
import rs.ac.singidunum.isa.app.dto.UniverzitetDTO;
import rs.ac.singidunum.isa.app.model.Fakultet;
import rs.ac.singidunum.isa.app.service.FakultetService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/fakulteti")
public class FakultetController {
    @Autowired
    private FakultetService fakultetService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<FakultetDTO>> getAll() {
        ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();

        for (Fakultet fakultet : fakultetService.findAll()) {
            fakulteti.add(new FakultetDTO(fakultet.getId(),fakultet.getNaziv(),
                    new UniverzitetDTO(fakultet.getUniverzitet().getId(),fakultet.getUniverzitet().getNaziv(),
                            fakultet.getUniverzitet().getDatumVremeOsnivanja(),null,null),
                    new AdresaDTO(fakultet.getAdresa().getId(), fakultet.getAdresa().getUlica(),fakultet.getAdresa().getBroj(),null),
                    new NastavnikDTO(fakultet.getNastavnik().getId(),fakultet.getNastavnik().getKorisnickoIme(),fakultet.getNastavnik().getLozinka(),
                            fakultet.getNastavnik().getIme(), fakultet.getNastavnik().getBiografija(), fakultet.getNastavnik().getJmbg(),null,null)));
        }

        return new ResponseEntity<Iterable<FakultetDTO>>(fakulteti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.GET)
    public ResponseEntity<FakultetDTO> get(@PathVariable("fakultetId") Long fakultetId) {
        Optional<Fakultet> fakultet = fakultetService.findOne(fakultetId);
        if (fakultet.isPresent()) {
            FakultetDTO prodajaDTO = new FakultetDTO(fakultet.get().getId(),fakultet.get().getNaziv(),
                    new UniverzitetDTO(fakultet.get().getUniverzitet().getId(), fakultet.get().getUniverzitet().getNaziv(),
                            fakultet.get().getUniverzitet().getDatumVremeOsnivanja(),null,null),
                    new AdresaDTO(fakultet.get().getAdresa().getId(),fakultet.get().getAdresa().getUlica(),
                            fakultet.get().getAdresa().getBroj(),null),
                    new NastavnikDTO(fakultet.get().getNastavnik().getId(), fakultet.get().getNastavnik().getKorisnickoIme(),
                            fakultet.get().getNastavnik().getLozinka(), fakultet.get().getNastavnik().getIme(),
                            fakultet.get().getNastavnik().getBiografija(),fakultet.get().getNastavnik().getJmbg(),null,null));

            return new ResponseEntity<FakultetDTO>(prodajaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Fakultet> create(@RequestBody Fakultet fakultet) {
        try {
            fakultetService.save(fakultet);
            return new ResponseEntity<Fakultet>(fakultet, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Fakultet>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.PUT)
    public ResponseEntity<Fakultet> update(@PathVariable("fakultetId") Long fakultetId,
                                               @RequestBody Fakultet izmenjeniFakultet) {
        Fakultet fakultet = fakultetService.findOne(fakultetId).orElse(null);
        if (fakultet != null) {
            izmenjeniFakultet.setId(fakultetId);
            fakultetService.save(izmenjeniFakultet); //FIXME: Sa ovim radi bez BUG-a (Beskonacna rekurzija!)
//            izmenjeniFakultet = fakultetService.save(izmenjeniFakultet);
            return new ResponseEntity<Fakultet>(izmenjeniFakultet, HttpStatus.OK);
        }
        return new ResponseEntity<Fakultet>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.DELETE)
    public ResponseEntity<Fakultet> delete(@PathVariable("fakultetId") Long fakultetId) {
        if (fakultetService.findOne(fakultetId).isPresent()) {
            fakultetService.delete(fakultetId);
            return new ResponseEntity<Fakultet>(HttpStatus.OK);
        }
        return new ResponseEntity<Fakultet>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje svih karata koje je kupio zadati putnik(Zadatak sa klk)
    @RequestMapping(path = "/findUniverzitet/{univerzitetNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<FakultetDTO>> findUniverzitetFakulteta(@PathVariable("univerzitetNaziv") String univerzitetNaziv) {
        ArrayList<FakultetDTO> fakultetiDTO = new ArrayList<>();
        for(Fakultet fakultet : fakultetService.findUniverzitetFakulteta(univerzitetNaziv)) {
            System.out.println(fakultet.getAdresa());
            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(fakultet.getUniverzitet().getId(),fakultet.getUniverzitet().getNaziv(),
                    fakultet.getUniverzitet().getDatumVremeOsnivanja(),
                    new AdresaDTO(fakultet.getUniverzitet().getAdresa().getId(),fakultet.getUniverzitet().getAdresa().getUlica(),
                            fakultet.getUniverzitet().getAdresa().getBroj(), null), null);
            fakultetiDTO.add(new FakultetDTO(fakultet.getId(), fakultet.getNaziv(), univerzitetDTO, null, null));
        }
        return new ResponseEntity<Iterable<FakultetDTO>>(fakultetiDTO, HttpStatus.OK);

    }

}
