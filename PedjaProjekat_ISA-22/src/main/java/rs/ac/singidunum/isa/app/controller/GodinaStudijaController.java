package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.GodinaStudijaDTO;
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.model.GodinaStudija;
import rs.ac.singidunum.isa.app.service.GodinaStudijaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/godinaStudija")
public class GodinaStudijaController {
    @Autowired
    private GodinaStudijaService godinaStudijaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<GodinaStudijaDTO>> getAll() {
        ArrayList<GodinaStudijaDTO> godineStudija = new ArrayList<GodinaStudijaDTO>();

        for (GodinaStudija godinaStudija : godinaStudijaService.findAll()) {
            godineStudija.add(new GodinaStudijaDTO(godinaStudija.getId(),godinaStudija.getGodina(),
                    new PredmetDTO(godinaStudija.getPredmet().getId(), godinaStudija.getPredmet().getNaziv(),
                            godinaStudija.getPredmet().getEspb(), godinaStudija.getPredmet().isObavezan(),
                            godinaStudija.getPredmet().getBrojPredavanja(), godinaStudija.getPredmet().getBrojVezbi(),
                            godinaStudija.getPredmet().getDrugiObliciNastave(), godinaStudija.getPredmet().getIstrazivackiRad(),
                            godinaStudija.getPredmet().getOstaliCasovi())));
        }

        return new ResponseEntity<Iterable<GodinaStudijaDTO>>(godineStudija, HttpStatus.OK);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.GET)
    public ResponseEntity<GodinaStudijaDTO> get(@PathVariable("godinaStudijaId") Long godinaStudijaId) {
        Optional<GodinaStudija> godinaStudija = godinaStudijaService.findOne(godinaStudijaId);
        if (godinaStudija.isPresent()) {
            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(godinaStudija.get().getId(),godinaStudija.get().getGodina(),
                    new PredmetDTO(godinaStudija.get().getPredmet().getId(), godinaStudija.get().getPredmet().getNaziv(),
                            godinaStudija.get().getPredmet().getEspb(), godinaStudija.get().getPredmet().isObavezan(),
                            godinaStudija.get().getPredmet().getBrojPredavanja(), godinaStudija.get().getPredmet().getBrojVezbi(),
                            godinaStudija.get().getPredmet().getDrugiObliciNastave(), godinaStudija.get().getPredmet().getIstrazivackiRad(),
                            godinaStudija.get().getPredmet().getOstaliCasovi()));

            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<GodinaStudija> create(@RequestBody GodinaStudija godinaStudija) {
        try {
            godinaStudijaService.save(godinaStudija);
            return new ResponseEntity<GodinaStudija>(godinaStudija, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<GodinaStudija>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.PUT)
    public ResponseEntity<GodinaStudija> update(@PathVariable("godinaStudijaaId") Long godinaStudijaId,
                                                   @RequestBody GodinaStudija izmenjenaGodinaStudija) {
        GodinaStudija godinaStudija = godinaStudijaService.findOne(godinaStudijaId).orElse(null);
        if (godinaStudija != null) {
            izmenjenaGodinaStudija.setId(godinaStudijaId);
            godinaStudijaService.save(izmenjenaGodinaStudija);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<GodinaStudija>(izmenjenaGodinaStudija, HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudija>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.DELETE)
    public ResponseEntity<GodinaStudija> delete(@PathVariable("godinaStudijaId") Long godinaStudijaId) {
        if (godinaStudijaService.findOne(godinaStudijaId).isPresent()) {
            godinaStudijaService.delete(godinaStudijaId);
            return new ResponseEntity<GodinaStudija>(HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudija>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje predmeta na God.Studija
    @RequestMapping(path = "/findPredmet/{predmetNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<GodinaStudijaDTO>> findPredmetGodineStudija(@PathVariable("predmetNaziv") String predmetNaziv) {
        ArrayList<GodinaStudijaDTO> godineStudijaDTO = new ArrayList<>();
        for(GodinaStudija godinaStudija : godinaStudijaService.findPredmetGodineStudija(predmetNaziv)) {
            System.out.println(godinaStudija.getGodina());
            PredmetDTO predmetDTO = new PredmetDTO(godinaStudija.getPredmet().getId(),godinaStudija.getPredmet().getNaziv(),
                    godinaStudija.getPredmet().getEspb(), godinaStudija.getPredmet().isObavezan(), godinaStudija.getPredmet().getBrojPredavanja(),
                    godinaStudija.getPredmet().getBrojVezbi(), godinaStudija.getPredmet().getDrugiObliciNastave(),
                    godinaStudija.getPredmet().getIstrazivackiRad(),godinaStudija.getPredmet().getOstaliCasovi());
            godineStudijaDTO.add(new GodinaStudijaDTO(godinaStudija.getId(), godinaStudija.getGodina(), predmetDTO));
        }
        return new ResponseEntity<Iterable<GodinaStudijaDTO>>(godineStudijaDTO, HttpStatus.OK);
    }
}
