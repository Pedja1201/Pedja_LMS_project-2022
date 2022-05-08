package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.NastavnikNaRealizacijiDTO;
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.dto.RealizacijaPredmetaDTO;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;
import rs.ac.singidunum.isa.app.service.RealizacijaPredmetaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/realizacijaPredmeta")
public class RealizacijaPredmetaController {
    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<RealizacijaPredmetaDTO>> getAll() {
        ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();

        for (RealizacijaPredmeta realizacijaPredmeta : realizacijaPredmetaService.findAll()) {
            realizacijePredmeta.add(new RealizacijaPredmetaDTO(realizacijaPredmeta.getId(),realizacijaPredmeta.getNaziv(),
                    new NastavnikNaRealizacijiDTO(realizacijaPredmeta.getNastavnikNaRealizaciji().getId(),
                            realizacijaPredmeta.getNastavnikNaRealizaciji().getBrojCasova(),null,null),
                    new PredmetDTO(realizacijaPredmeta.getPredmet().getId(), realizacijaPredmeta.getPredmet().getNaziv(),
                            realizacijaPredmeta.getPredmet().getEspb(),realizacijaPredmeta.getPredmet().isObavezan(),
                            realizacijaPredmeta.getPredmet().getBrojPredavanja(),realizacijaPredmeta.getPredmet().getBrojVezbi(),
                            realizacijaPredmeta.getPredmet().getDrugiObliciNastave(),realizacijaPredmeta.getPredmet().getIstrazivackiRad(),
                            realizacijaPredmeta.getPredmet().getOstaliCasovi())));
        }

        return new ResponseEntity<Iterable<RealizacijaPredmetaDTO>>(realizacijePredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.GET)
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
                            realizacijaPredmeta.get().getPredmet().getOstaliCasovi()));

            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<RealizacijaPredmeta> create(@RequestBody RealizacijaPredmeta realizacijaPredmeta) {
        try {
            realizacijaPredmetaService.save(realizacijaPredmeta);
            return new ResponseEntity<RealizacijaPredmeta>(realizacijaPredmeta, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<RealizacijaPredmeta>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.PUT)
    public ResponseEntity<RealizacijaPredmeta> update(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId,
                                                   @RequestBody RealizacijaPredmeta izmenjenaRealizacijaPredmeta) {
        RealizacijaPredmeta realizacijaPredmeta = realizacijaPredmetaService.findOne(realizacijaPredmetaId).orElse(null);
        if (realizacijaPredmeta != null) {
            izmenjenaRealizacijaPredmeta.setId(realizacijaPredmetaId);
            realizacijaPredmetaService.save(izmenjenaRealizacijaPredmeta);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<RealizacijaPredmeta>(izmenjenaRealizacijaPredmeta, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmeta>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.DELETE)
    public ResponseEntity<RealizacijaPredmeta> delete(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId) {
        if (realizacijaPredmetaService.findOne(realizacijaPredmetaId).isPresent()) {
            realizacijaPredmetaService.delete(realizacijaPredmetaId);
            return new ResponseEntity<RealizacijaPredmeta>(HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmeta>(HttpStatus.NOT_FOUND);
    }
}