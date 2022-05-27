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
import rs.ac.singidunum.isa.app.aspect.LoggedUniverzitet;
import rs.ac.singidunum.isa.app.dto.AdresaDTO;
import rs.ac.singidunum.isa.app.dto.NastavnikDTO;
import rs.ac.singidunum.isa.app.dto.UniverzitetDTO;
import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.Univerzitet;
import rs.ac.singidunum.isa.app.service.UniverzitetService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/univerziteti")
public class UniverzitetController {
    @Autowired
    private UniverzitetService univerzitetService;

    @LoggedUniverzitet
    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<UniverzitetDTO>> getAll() {
        ArrayList<UniverzitetDTO> univerziteti = new ArrayList<UniverzitetDTO>();

        for (Univerzitet univerzitet : univerzitetService.findAll()) {
            univerziteti.add(new UniverzitetDTO(univerzitet.getId(),univerzitet.getNaziv(), univerzitet.getDatumVremeOsnivanja(),
                    new AdresaDTO(univerzitet.getAdresa().getId(), univerzitet.getAdresa().getUlica(),
                            univerzitet.getAdresa().getBroj(),null),
                    new NastavnikDTO(univerzitet.getNastavnik().getId(),univerzitet.getNastavnik().getKorisnickoIme(),
                            univerzitet.getNastavnik().getLozinka(),univerzitet.getNastavnik().getIme(),
                            univerzitet.getNastavnik().getBiografija(), univerzitet.getNastavnik().getJmbg(),null,null)));
        }

        return new ResponseEntity<Iterable<UniverzitetDTO>>(univerziteti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.GET)
    public ResponseEntity<UniverzitetDTO> get(@PathVariable("univerzitetId") Long univerzitetId) {
        Optional<Univerzitet> univerzitet = univerzitetService.findOne(univerzitetId);
        if (univerzitet.isPresent()) {
            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(univerzitet.get().getId(),
                                        univerzitet.get().getNaziv(), univerzitet.get().getDatumVremeOsnivanja(),
                    new AdresaDTO(univerzitet.get().getAdresa().getId(), univerzitet.get().getAdresa().getUlica(),
                            univerzitet.get().getAdresa().getBroj(),null),
                    new NastavnikDTO(univerzitet.get().getNastavnik().getId(),univerzitet.get().getNastavnik().getKorisnickoIme(),
                            univerzitet.get().getNastavnik().getLozinka(),univerzitet.get().getNastavnik().getIme(),
                            univerzitet.get().getNastavnik().getBiografija(), univerzitet.get().getNastavnik().getJmbg(),
                                                     null,null));

            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Univerzitet> create(@RequestBody Univerzitet univerzitet) {
        try {
            univerzitetService.save(univerzitet);
            return new ResponseEntity<Univerzitet>(univerzitet, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Univerzitet>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.PUT)
    public ResponseEntity<Univerzitet> update(@PathVariable("univerzitetId") Long univerzitetId,
                                             @RequestBody Univerzitet izmenjenUniverzitet) {
        Univerzitet univerzitet = univerzitetService.findOne(univerzitetId).orElse(null);
        if (univerzitet != null) {
            izmenjenUniverzitet.setId(univerzitetId);
            univerzitetService.save(izmenjenUniverzitet); //FIXME: Sa ovim radi bez BUG-a (Beskonacna rekurzija!)
            return new ResponseEntity<Univerzitet>(izmenjenUniverzitet, HttpStatus.OK);
        }
        return new ResponseEntity<Univerzitet>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.DELETE)
    public ResponseEntity<Univerzitet> delete(@PathVariable("univerzitetId") Long univerzitetId) {
        if (univerzitetService.findOne(univerzitetId).isPresent()) {
            univerzitetService.delete(univerzitetId);
            return new ResponseEntity<Univerzitet>(HttpStatus.OK);
        }
        return new ResponseEntity<Univerzitet>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje svih nastavnika
    @RequestMapping(path = "/findNastavnik/{nastavnikIme}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UniverzitetDTO>> findNastavnikUniverziteta(@PathVariable("nastavnikIme") String nastavnikIme) {
        ArrayList<UniverzitetDTO> univerzitetiDTO = new ArrayList<>();
        for(Univerzitet univerzitet : univerzitetService.findNastavnikUniverziteta(nastavnikIme)) {
            System.out.println(univerzitet.getNaziv());
            NastavnikDTO nastavnikDTO = new NastavnikDTO(univerzitet.getNastavnik().getId(),univerzitet.getNastavnik().getKorisnickoIme(),
                    null,univerzitet.getNastavnik().getIme(),univerzitet.getNastavnik().getBiografija(),
                    univerzitet.getNastavnik().getJmbg(),null,
                    new ZvanjeDTO(univerzitet.getNastavnik().getZvanje().getId(), univerzitet.getNastavnik().getZvanje().getDatumIzbora(),
                            univerzitet.getNastavnik().getZvanje().getDatumPrestanka(), null, null));
            univerzitetiDTO.add(new UniverzitetDTO(univerzitet.getId(), univerzitet.getNaziv(),
                    univerzitet.getDatumVremeOsnivanja(), null, nastavnikDTO));
        }
        return new ResponseEntity<Iterable<UniverzitetDTO>>(univerzitetiDTO, HttpStatus.OK);

    }
}
