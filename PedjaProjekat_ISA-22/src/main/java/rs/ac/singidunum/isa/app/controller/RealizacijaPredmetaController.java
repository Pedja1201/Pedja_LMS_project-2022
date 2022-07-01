package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;
import rs.ac.singidunum.isa.app.service.RealizacijaPredmetaService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/realizacijePredmeta")
public class RealizacijaPredmetaController {
    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<Page<RealizacijaPredmetaDTO>> getAll(Pageable pageable) {
        Page<RealizacijaPredmeta> realizacijaPredmeta = realizacijaPredmetaService.findAll(pageable);
        Page<RealizacijaPredmetaDTO> realizacijePredmeta = realizacijaPredmeta.map(new Function<RealizacijaPredmeta, RealizacijaPredmetaDTO>() {
            public RealizacijaPredmetaDTO apply(RealizacijaPredmeta realizacijaPredmeta) {
                RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(realizacijaPredmeta.getId(), realizacijaPredmeta.getNaziv(),
                        new NastavnikNaRealizacijiDTO(realizacijaPredmeta.getNastavnikNaRealizaciji().getId(),
                                realizacijaPredmeta.getNastavnikNaRealizaciji().getBrojCasova(),null,null),
                        new PredmetDTO(realizacijaPredmeta.getPredmet().getId(), realizacijaPredmeta.getPredmet().getNaziv(),
                                realizacijaPredmeta.getPredmet().getEspb(),realizacijaPredmeta.getPredmet().isObavezan(),
                                realizacijaPredmeta.getPredmet().getBrojPredavanja(),realizacijaPredmeta.getPredmet().getBrojVezbi(),
                                realizacijaPredmeta.getPredmet().getDrugiObliciNastave(),realizacijaPredmeta.getPredmet().getIstrazivackiRad(),
                                realizacijaPredmeta.getPredmet().getOstaliCasovi()),
                        new EvaluacijaZnanjaDTO(realizacijaPredmeta.getEvaluacijaZnanja().getId(),
                                realizacijaPredmeta.getEvaluacijaZnanja().getVremePocetka(),realizacijaPredmeta.getEvaluacijaZnanja().getVremeZavrsetka(),null,null),
                        new TerminNastaveDTO(realizacijaPredmeta.getTerminNastave().getId(),
                                realizacijaPredmeta.getTerminNastave().getVremePocetka(),realizacijaPredmeta.getTerminNastave().getVremeKraja(),null,null)
                );
                // Conversion logic
                return realizacijaPredmetaDTO;
            }
        });
        return new ResponseEntity<Page<RealizacijaPredmetaDTO>>(realizacijePredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.GET)
//    @Secured({"ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<RealizacijaPredmetaDTO> get(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId) {
        Optional<RealizacijaPredmeta> realizacijaPredmeta = realizacijaPredmetaService.findOne(realizacijaPredmetaId);
        if (realizacijaPredmeta.isPresent()) {
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(realizacijaPredmeta.get().getId(),
                    realizacijaPredmeta.get().getNaziv(),
                    new NastavnikNaRealizacijiDTO(realizacijaPredmeta.get().getNastavnikNaRealizaciji().getId(),
                            realizacijaPredmeta.get().getNastavnikNaRealizaciji().getBrojCasova(), null,null),
                    new PredmetDTO(realizacijaPredmeta.get().getPredmet().getId(),realizacijaPredmeta.get().getPredmet().getNaziv(),
                            realizacijaPredmeta.get().getPredmet().getEspb(), realizacijaPredmeta.get().getPredmet().isObavezan(),
                            realizacijaPredmeta.get().getPredmet().getBrojPredavanja(),realizacijaPredmeta.get().getPredmet().getBrojVezbi(),
                            realizacijaPredmeta.get().getPredmet().getDrugiObliciNastave(),realizacijaPredmeta.get().getPredmet().getIstrazivackiRad(),
                            realizacijaPredmeta.get().getPredmet().getOstaliCasovi()),
                    new EvaluacijaZnanjaDTO(realizacijaPredmeta.get().getEvaluacijaZnanja().getId(),
                            realizacijaPredmeta.get().getEvaluacijaZnanja().getVremePocetka(),realizacijaPredmeta.get().getEvaluacijaZnanja().getVremeZavrsetka(),null,null),
                    new TerminNastaveDTO(realizacijaPredmeta.get().getTerminNastave().getId(),
                            realizacijaPredmeta.get().getTerminNastave().getVremePocetka(),realizacijaPredmeta.get().getTerminNastave().getVremeKraja(),null,null));

            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<RealizacijaPredmetaDTO> create(@RequestBody RealizacijaPredmeta realizacijaPredmeta) {
        try {
            realizacijaPredmetaService.save(realizacijaPredmeta);
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(realizacijaPredmeta.getNastavnikNaRealizaciji().getId(),
                    realizacijaPredmeta.getNastavnikNaRealizaciji().getBrojCasova(),null,null);
            PredmetDTO predmetDTO = new PredmetDTO(realizacijaPredmeta.getPredmet().getId(), realizacijaPredmeta.getPredmet().getNaziv(),
                    realizacijaPredmeta.getPredmet().getEspb(),realizacijaPredmeta.getPredmet().isObavezan(),
                    realizacijaPredmeta.getPredmet().getBrojPredavanja(),realizacijaPredmeta.getPredmet().getBrojVezbi(),
                    realizacijaPredmeta.getPredmet().getDrugiObliciNastave(),realizacijaPredmeta.getPredmet().getIstrazivackiRad(),
                    realizacijaPredmeta.getPredmet().getOstaliCasovi());
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(realizacijaPredmeta.getEvaluacijaZnanja().getId(), realizacijaPredmeta.getEvaluacijaZnanja().getVremePocetka(),
                    realizacijaPredmeta.getEvaluacijaZnanja().getVremeZavrsetka(), null,null);
            TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(realizacijaPredmeta.getTerminNastave().getId(), realizacijaPredmeta.getTerminNastave().getVremePocetka(),
                    realizacijaPredmeta.getTerminNastave().getVremeKraja(), null,null);

            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(realizacijaPredmeta.getId(),
                    realizacijaPredmeta.getNaziv(),  nastavnikNaRealizacijiDTO, predmetDTO, evaluacijaZnanjaDTO, terminNastaveDTO);

            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<RealizacijaPredmetaDTO> update(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId,
                                                   @RequestBody RealizacijaPredmeta izmenjenaRealizacijaPredmeta) {
        RealizacijaPredmeta realizacijaPredmeta = realizacijaPredmetaService.findOne(realizacijaPredmetaId).orElse(null);
        if (realizacijaPredmeta != null) {
            izmenjenaRealizacijaPredmeta.setId(realizacijaPredmetaId);
            izmenjenaRealizacijaPredmeta = realizacijaPredmetaService.save(izmenjenaRealizacijaPredmeta);
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(izmenjenaRealizacijaPredmeta.getNastavnikNaRealizaciji().getId(),
                    izmenjenaRealizacijaPredmeta.getNastavnikNaRealizaciji().getBrojCasova(),null,null);
            PredmetDTO predmetDTO = new PredmetDTO(izmenjenaRealizacijaPredmeta.getPredmet().getId(), izmenjenaRealizacijaPredmeta.getPredmet().getNaziv(),
                    izmenjenaRealizacijaPredmeta.getPredmet().getEspb(),izmenjenaRealizacijaPredmeta.getPredmet().isObavezan(),
                    izmenjenaRealizacijaPredmeta.getPredmet().getBrojPredavanja(),izmenjenaRealizacijaPredmeta.getPredmet().getBrojVezbi(),
                    izmenjenaRealizacijaPredmeta.getPredmet().getDrugiObliciNastave(),izmenjenaRealizacijaPredmeta.getPredmet().getIstrazivackiRad(),
                    izmenjenaRealizacijaPredmeta.getPredmet().getOstaliCasovi());
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(izmenjenaRealizacijaPredmeta.getEvaluacijaZnanja().getId(), izmenjenaRealizacijaPredmeta.getEvaluacijaZnanja().getVremePocetka(),
                    izmenjenaRealizacijaPredmeta.getEvaluacijaZnanja().getVremeZavrsetka(), null,null);
            TerminNastaveDTO terminNastaveDTO = new TerminNastaveDTO(izmenjenaRealizacijaPredmeta.getTerminNastave().getId(), izmenjenaRealizacijaPredmeta.getTerminNastave().getVremePocetka(),
                    izmenjenaRealizacijaPredmeta.getTerminNastave().getVremeKraja(), null,null);

            RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(izmenjenaRealizacijaPredmeta.getId(),
                    izmenjenaRealizacijaPredmeta.getNaziv(),  nastavnikNaRealizacijiDTO, predmetDTO,evaluacijaZnanjaDTO,terminNastaveDTO);
            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<RealizacijaPredmetaDTO> delete(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId) {
        if (realizacijaPredmetaService.findOne(realizacijaPredmetaId).isPresent()) {
            realizacijaPredmetaService.delete(realizacijaPredmetaId);
            return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    //DONE: Metoda i upit za pronalaženje Predmeta u realizcaiji
    @RequestMapping(path = "/findPredmet/{predmetNaziv}", method = RequestMethod.GET)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<RealizacijaPredmetaDTO>> findPredmetURealizaciji(@PathVariable("predmetNaziv") String predmetNaziv) {
        ArrayList<RealizacijaPredmetaDTO> realizacijePredmetaDTO = new ArrayList<>();
        for(RealizacijaPredmeta realizacijaPredmeta : realizacijaPredmetaService.findPredmetURealizaciji(predmetNaziv)) {
            System.out.println(realizacijaPredmeta.getNaziv());
            PredmetDTO predmetDTO = new PredmetDTO(realizacijaPredmeta.getPredmet().getId(),realizacijaPredmeta.getPredmet().getNaziv(),
                    realizacijaPredmeta.getPredmet().getEspb(),realizacijaPredmeta.getPredmet().isObavezan(),
                    realizacijaPredmeta.getPredmet().getBrojPredavanja(), realizacijaPredmeta.getPredmet().getBrojVezbi(),
                    realizacijaPredmeta.getPredmet().getDrugiObliciNastave(),realizacijaPredmeta.getPredmet().getIstrazivackiRad(),
                    realizacijaPredmeta.getPredmet().getOstaliCasovi());

            realizacijePredmetaDTO.add(new RealizacijaPredmetaDTO(realizacijaPredmeta.getId(), realizacijaPredmeta.getNaziv(),
                    null,predmetDTO, null, null));
        }
        return new ResponseEntity<Iterable<RealizacijaPredmetaDTO>>(realizacijePredmetaDTO, HttpStatus.OK);

    }
}
