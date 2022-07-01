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
import rs.ac.singidunum.isa.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.Fakultet;
import rs.ac.singidunum.isa.app.service.FakultetService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/fakulteti")
public class FakultetController {
    @Autowired
    private FakultetService fakultetService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<FakultetDTO>> getAll(Pageable pageable) {
        Page<Fakultet> fakultet = fakultetService.findAll(pageable);
        Page<FakultetDTO> fakulteti = fakultet.map(new Function<Fakultet, FakultetDTO>() {
            public FakultetDTO apply(Fakultet fakultet) {
                FakultetDTO fakultetDTO = new FakultetDTO(fakultet.getId(), fakultet.getNaziv(),
                        new UniverzitetDTO(fakultet.getUniverzitet().getId(),fakultet.getUniverzitet().getNaziv(),
                                fakultet.getUniverzitet().getDatumVremeOsnivanja(),null,null),
                        new AdresaDTO(fakultet.getAdresa().getId(), fakultet.getAdresa().getUlica(),fakultet.getAdresa().getBroj(),null),
                        new NastavnikDTO(fakultet.getNastavnik().getId(),fakultet.getNastavnik().getKorisnickoIme(),fakultet.getNastavnik().getLozinka(),
                                fakultet.getNastavnik().getEmail(), fakultet.getNastavnik().getIme(), fakultet.getNastavnik().getBiografija(),
                                fakultet.getNastavnik().getJmbg(),null,null)
                );
                // Conversion logic
                return fakultetDTO;
            }
        });
        return new ResponseEntity<Page<FakultetDTO>>(fakulteti, HttpStatus.OK);
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
                            fakultet.get().getNastavnik().getLozinka(),fakultet.get().getNastavnik().getEmail(), fakultet.get().getNastavnik().getIme(),
                            fakultet.get().getNastavnik().getBiografija(),fakultet.get().getNastavnik().getJmbg(),null,null));

            return new ResponseEntity<FakultetDTO>(prodajaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<FakultetDTO> create(@RequestBody Fakultet fakultet) {
        try {
            fakultetService.save(fakultet);
            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(fakultet.getUniverzitet().getId(),fakultet.getUniverzitet().getNaziv(),
                    fakultet.getUniverzitet().getDatumVremeOsnivanja(),null,null);
            AdresaDTO adresaDTO = new AdresaDTO(fakultet.getAdresa().getId(), fakultet.getAdresa().getUlica(),fakultet.getAdresa().getBroj(),null);
            NastavnikDTO nastavnikDTO =new NastavnikDTO(fakultet.getNastavnik().getId(), fakultet.getNastavnik().getKorisnickoIme(),
                    fakultet.getNastavnik().getLozinka(), fakultet.getNastavnik().getEmail(),fakultet.getNastavnik().getIme(),
                    fakultet.getNastavnik().getBiografija(),fakultet.getNastavnik().getJmbg(),null,null);

            FakultetDTO fakultetDTO = new FakultetDTO(fakultet.getId(),fakultet.getNaziv(), univerzitetDTO, adresaDTO, nastavnikDTO);
            return new ResponseEntity<FakultetDTO>(fakultetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<FakultetDTO> update(@PathVariable("fakultetId") Long fakultetId,
                                               @RequestBody Fakultet izmenjeniFakultet) {
        Fakultet fakultet = fakultetService.findOne(fakultetId).orElse(null);
        if (fakultet != null) {
            izmenjeniFakultet.setId(fakultetId);
            izmenjeniFakultet = fakultetService.save(izmenjeniFakultet);
            UniverzitetDTO univerzitetDTO = new UniverzitetDTO(izmenjeniFakultet.getUniverzitet().getId(),
                    izmenjeniFakultet.getUniverzitet().getNaziv(), izmenjeniFakultet.getUniverzitet().getDatumVremeOsnivanja(),null,null);
            AdresaDTO adresaDTO = new AdresaDTO(izmenjeniFakultet.getAdresa().getId(), izmenjeniFakultet.getAdresa().getUlica(),izmenjeniFakultet.getAdresa().getBroj(),null);
            NastavnikDTO nastavnikDTO =new NastavnikDTO(izmenjeniFakultet.getNastavnik().getId(), izmenjeniFakultet.getNastavnik().getKorisnickoIme(),
                    izmenjeniFakultet.getNastavnik().getLozinka(),izmenjeniFakultet.getNastavnik().getEmail(), izmenjeniFakultet.getNastavnik().getIme(),
                    izmenjeniFakultet.getNastavnik().getBiografija(),izmenjeniFakultet.getNastavnik().getJmbg(),null,null);

            FakultetDTO fakultetDTO = new FakultetDTO(izmenjeniFakultet.getId(),izmenjeniFakultet.getNaziv(), univerzitetDTO, adresaDTO, nastavnikDTO);
            return new ResponseEntity<FakultetDTO>(fakultetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<FakultetDTO> delete(@PathVariable("fakultetId") Long fakultetId) {
        if (fakultetService.findOne(fakultetId).isPresent()) {
            fakultetService.delete(fakultetId);
            return new ResponseEntity<FakultetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
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

    //DONE: Metoda i upit za pronalaženje svih nastavnika
    @RequestMapping(path = "/findNastavnik/{nastavnikIme}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<FakultetDTO>> findNastavnikaFakulteta(@PathVariable("nastavnikIme") String nastavnikIme) {
        ArrayList<FakultetDTO> fakultetiDTO = new ArrayList<>();
        for(Fakultet fakultet : fakultetService.findNastavnikaFakulteta(nastavnikIme)) {
            System.out.println(fakultet.getAdresa());
            NastavnikDTO nastavnikDTO = new NastavnikDTO(fakultet.getNastavnik().getId(),fakultet.getNastavnik().getKorisnickoIme(),null,
                    fakultet.getNastavnik().getEmail(), fakultet.getNastavnik().getIme(), fakultet.getNastavnik().getBiografija(),fakultet.getNastavnik().getJmbg(),
                    new AdresaDTO(fakultet.getNastavnik().getAdresa().getId(),fakultet.getNastavnik().getAdresa().getUlica(),
                            fakultet.getNastavnik().getAdresa().getBroj(), null),
                    new ZvanjeDTO(fakultet.getNastavnik().getZvanje().getId(),fakultet.getNastavnik().getZvanje().getDatumIzbora(),
                            fakultet.getNastavnik().getZvanje().getDatumPrestanka(),null, null));
            fakultetiDTO.add(new FakultetDTO(fakultet.getId(), fakultet.getNaziv(), null, null, nastavnikDTO));
        }
        return new ResponseEntity<Iterable<FakultetDTO>>(fakultetiDTO, HttpStatus.OK);

    }

}
