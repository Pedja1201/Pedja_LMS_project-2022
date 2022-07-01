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
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.model.Predmet;
import rs.ac.singidunum.isa.app.service.NastavnikService;
import rs.ac.singidunum.isa.app.service.PredmetService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/predmeti")
public class PredmetController {
    @Autowired
    private PredmetService predmetService;

    @Autowired
    private NastavnikService nastavnikService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<Page<PredmetDTO>> getAll(Pageable pageable) {
        Page<Predmet> predmet = predmetService.findAll(pageable);
        Page<PredmetDTO> predmeti = predmet.map(new Function<Predmet, PredmetDTO>() {
            public PredmetDTO apply(Predmet predmet) {
                PredmetDTO predmetDTO = new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEspb(),
                        predmet.isObavezan(), predmet.getBrojPredavanja(), predmet.getBrojVezbi(),
                        predmet.getDrugiObliciNastave(), predmet.getIstrazivackiRad(), predmet.getOstaliCasovi()
                );
                // Conversion logic
                return predmetDTO;
            }
        });
        return new ResponseEntity<Page<PredmetDTO>>(predmeti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK"})
    public ResponseEntity<PredmetDTO> get(@PathVariable("predmetId") Long predmetId) {
        Optional<Predmet> predmet = predmetService.findOne(predmetId);
        if (predmet.isPresent()) {
            PredmetDTO predmetDTO = new PredmetDTO(predmet.get().getId(), predmet.get().getNaziv(),
                    predmet.get().getEspb(), predmet.get().isObavezan(), predmet.get().getBrojPredavanja(),
                    predmet.get().getBrojVezbi(), predmet.get().getDrugiObliciNastave(), predmet.get().getIstrazivackiRad(),
                    predmet.get().getOstaliCasovi());

            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PredmetDTO> create(@RequestBody Predmet predmet) {
        try {
            predmetService.save(predmet);
            PredmetDTO predmetDTO = new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEspb(),
                    predmet.isObavezan(), predmet.getBrojPredavanja(), predmet.getBrojVezbi(),
                    predmet.getDrugiObliciNastave(), predmet.getIstrazivackiRad(), predmet.getOstaliCasovi());

            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PredmetDTO> update(@PathVariable("predmetId") Long predmetId,
                                                   @RequestBody Predmet izmenjenPredmet) {
        Predmet predmet = predmetService.findOne(predmetId).orElse(null);
        if (predmet != null) {
            izmenjenPredmet.setId(predmetId);
            izmenjenPredmet = predmetService.save(izmenjenPredmet);
            PredmetDTO predmetDTO = new PredmetDTO(izmenjenPredmet.getId(), izmenjenPredmet.getNaziv(), izmenjenPredmet.getEspb(),
                    izmenjenPredmet.isObavezan(), izmenjenPredmet.getBrojPredavanja(), izmenjenPredmet.getBrojVezbi(),
                    izmenjenPredmet.getDrugiObliciNastave(), izmenjenPredmet.getIstrazivackiRad(), izmenjenPredmet.getOstaliCasovi());
            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<PredmetDTO> delete(@PathVariable("predmetId") Long predmetId) {
        if (predmetService.findOne(predmetId).isPresent()) {
            predmetService.delete(predmetId);
            return new ResponseEntity<PredmetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);
    }

    //TODO: ovu metodu treba izbaciti i zameniti je sa ovom ispod getPredmetForNastavnik
//    //ovo je stara metoda koja ne radi fali joj @Param u repositoriju iz nekog razloga
//    @RequestMapping(path = "/nastavnik/{nastavnikId}", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK"})
//    public ResponseEntity<Iterable<PredmetDTO>> getPredmyByNastavnik(@PathVariable("nastavnikId")Long nastavnikId){
//        if(nastavnikService.findOne(nastavnikId).isPresent()){
//            ArrayList<PredmetDTO> predmeti = new ArrayList<PredmetDTO>();
//            for (Predmet predmet : predmetService.findByNastavnik(nastavnikService.findOne(nastavnikId))) {
//                predmeti.add(new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEspb(),
//                        predmet.isObavezan(), predmet.getBrojPredavanja(), predmet.getBrojVezbi(),
//                        predmet.getDrugiObliciNastave(), predmet.getIstrazivackiRad(), predmet.getOstaliCasovi()));
//            }
//            return new ResponseEntity<Iterable<PredmetDTO>>(predmeti ,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(path = "/nastavnik/{nastavnikId}", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK"})
    public ResponseEntity<Iterable<PredmetDTO>> getPredmtForNastavnik(@PathVariable("nastavnikId")Long nastavnikId){
        if(nastavnikService.findOne(nastavnikId).isPresent()){
            ArrayList<PredmetDTO> predmeti = new ArrayList<PredmetDTO>();
            for (Predmet predmet : predmetService.findPredmetForNastavnik(nastavnikService.findOne(nastavnikId))) {
                predmeti.add(new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEspb(),
                        predmet.isObavezan(), predmet.getBrojPredavanja(), predmet.getBrojVezbi(),
                        predmet.getDrugiObliciNastave(), predmet.getIstrazivackiRad(), predmet.getOstaliCasovi()));
            }
            return new ResponseEntity<Iterable<PredmetDTO>>(predmeti ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
