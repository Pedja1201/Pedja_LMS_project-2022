package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.GodinaStudijaDTO;
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.model.GodinaStudija;
import rs.ac.singidunum.isa.app.service.GodinaStudijaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/godineStudija")
public class GodinaStudijaController {
    @Autowired
    private GodinaStudijaService godinaStudijaService;
    @CrossOrigin
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<GodinaStudijaDTO>> getAll(Pageable pageable) {
        Page<GodinaStudija> godinaStudija = godinaStudijaService.findAll(pageable);
        Page<GodinaStudijaDTO> godineStudija = godinaStudija.map(new Function<GodinaStudija, GodinaStudijaDTO>() {
            public GodinaStudijaDTO apply(GodinaStudija godinaStudija) {
                GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(godinaStudija.getId(), godinaStudija.getGodina(),
                        new PredmetDTO(godinaStudija.getPredmet().getId(), godinaStudija.getPredmet().getNaziv(),
                                godinaStudija.getPredmet().getEspb(), godinaStudija.getPredmet().isObavezan(),
                                godinaStudija.getPredmet().getBrojPredavanja(), godinaStudija.getPredmet().getBrojVezbi(),
                                godinaStudija.getPredmet().getDrugiObliciNastave(), godinaStudija.getPredmet().getIstrazivackiRad(),
                                godinaStudija.getPredmet().getOstaliCasovi())
                );
                // Conversion logic
                return godinaStudijaDTO;
            }
        });
        return new ResponseEntity<Page<GodinaStudijaDTO>>(godineStudija, HttpStatus.OK);
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
    public ResponseEntity<GodinaStudijaDTO> create(@RequestBody GodinaStudija godinaStudija) {
        try {
            godinaStudijaService.save(godinaStudija);
            PredmetDTO predmetDTO = new PredmetDTO(godinaStudija.getPredmet().getId(), godinaStudija.getPredmet().getNaziv(),
                    godinaStudija.getPredmet().getEspb(), godinaStudija.getPredmet().isObavezan(),
                    godinaStudija.getPredmet().getBrojPredavanja(), godinaStudija.getPredmet().getBrojVezbi(),
                    godinaStudija.getPredmet().getDrugiObliciNastave(), godinaStudija.getPredmet().getIstrazivackiRad(),
                    godinaStudija.getPredmet().getOstaliCasovi());

            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(godinaStudija.getId(), godinaStudija.getGodina(), predmetDTO);

            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.PUT)
    public ResponseEntity<GodinaStudijaDTO> update(@PathVariable("godinaStudijaId") Long godinaStudijaId,
                                                   @RequestBody GodinaStudija izmenjenaGodinaStudija) {
        GodinaStudija godinaStudija = godinaStudijaService.findOne(godinaStudijaId).orElse(null);
        if (godinaStudija != null) {
            izmenjenaGodinaStudija.setId(godinaStudijaId);
            izmenjenaGodinaStudija = godinaStudijaService.save(izmenjenaGodinaStudija);
            PredmetDTO predmetDTO = new PredmetDTO(izmenjenaGodinaStudija.getPredmet().getId(), izmenjenaGodinaStudija.getPredmet().getNaziv(),
                    izmenjenaGodinaStudija.getPredmet().getEspb(), izmenjenaGodinaStudija.getPredmet().isObavezan(),
                    izmenjenaGodinaStudija.getPredmet().getBrojPredavanja(), izmenjenaGodinaStudija.getPredmet().getBrojVezbi(),
                    izmenjenaGodinaStudija.getPredmet().getDrugiObliciNastave(), izmenjenaGodinaStudija.getPredmet().getIstrazivackiRad(),
                    izmenjenaGodinaStudija.getPredmet().getOstaliCasovi());

            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(izmenjenaGodinaStudija.getId(), izmenjenaGodinaStudija.getGodina(), predmetDTO);
            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.DELETE)
    public ResponseEntity<GodinaStudijaDTO> delete(@PathVariable("godinaStudijaId") Long godinaStudijaId) {
        if (godinaStudijaService.findOne(godinaStudijaId).isPresent()) {
            godinaStudijaService.delete(godinaStudijaId);
            return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);
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
