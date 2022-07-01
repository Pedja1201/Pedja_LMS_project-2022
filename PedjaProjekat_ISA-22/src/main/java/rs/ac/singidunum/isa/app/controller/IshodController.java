package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.isa.app.aspect.LoggedNastavnik;
import rs.ac.singidunum.isa.app.dto.IshodDTO;
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.model.Ishod;
import rs.ac.singidunum.isa.app.service.IshodService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/ishodi")
public class IshodController {
    @Autowired
    private IshodService ishodService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<IshodDTO>> getAll(Pageable pageable) {
        Page<Ishod> ishod = ishodService.findAll(pageable);
        Page<IshodDTO> ishodi = ishod.map(new Function<Ishod, IshodDTO>() {
            public IshodDTO apply(Ishod ishod) {
                IshodDTO ishodDTO = new IshodDTO(ishod.getId(), ishod.getOpis(),
                        new PredmetDTO(ishod.getPredmet().getId(), ishod.getPredmet().getNaziv(),
                                ishod.getPredmet().getEspb(), ishod.getPredmet().isObavezan(),
                                ishod.getPredmet().getBrojPredavanja(), ishod.getPredmet().getBrojVezbi(),
                                ishod.getPredmet().getDrugiObliciNastave(), ishod.getPredmet().getIstrazivackiRad(),
                                ishod.getPredmet().getOstaliCasovi())
                );
                // Conversion logic
                return ishodDTO;
            }
        });
        return new ResponseEntity<Page<IshodDTO>>(ishodi, HttpStatus.OK);
    }

    @RequestMapping(path = "/{ishodId}", method = RequestMethod.GET)
    public ResponseEntity<IshodDTO> get(@PathVariable("ishodId") Long ishodId) {
        Optional<Ishod> ishod = ishodService.findOne(ishodId);
        if (ishod.isPresent()) {
            IshodDTO ishodDTO = new IshodDTO(ishod.get().getId(),ishod.get().getOpis(),
                    new PredmetDTO(ishod.get().getPredmet().getId(), ishod.get().getPredmet().getNaziv(),
                            ishod.get().getPredmet().getEspb(), ishod.get().getPredmet().isObavezan(),
                            ishod.get().getPredmet().getBrojPredavanja(), ishod.get().getPredmet().getBrojVezbi(),
                            ishod.get().getPredmet().getDrugiObliciNastave(), ishod.get().getPredmet().getIstrazivackiRad(),
                            ishod.get().getPredmet().getOstaliCasovi()));

            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodDTO> create(@RequestBody Ishod ishod) {
        try {
            ishodService.save(ishod);
            PredmetDTO predmetDTO = new PredmetDTO(ishod.getPredmet().getId(), ishod.getPredmet().getNaziv(),
                    ishod.getPredmet().getEspb(), ishod.getPredmet().isObavezan(),
                    ishod.getPredmet().getBrojPredavanja(), ishod.getPredmet().getBrojVezbi(),
                    ishod.getPredmet().getDrugiObliciNastave(), ishod.getPredmet().getIstrazivackiRad(),
                    ishod.getPredmet().getOstaliCasovi());

            IshodDTO ishodDTO = new IshodDTO(ishod.getId(), ishod.getOpis(), predmetDTO);

            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{ishodId}", method = RequestMethod.PUT)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodDTO> update(@PathVariable("ishodId") Long ishodId,
                                                @RequestBody Ishod izmenjenIshod) {
        Ishod ishod = ishodService.findOne(ishodId).orElse(null);
        if (ishod != null) {
            izmenjenIshod.setId(ishodId);
            izmenjenIshod = ishodService.save(izmenjenIshod);
            PredmetDTO predmetDTO = new PredmetDTO(izmenjenIshod.getPredmet().getId(), izmenjenIshod.getPredmet().getNaziv(),
                    izmenjenIshod.getPredmet().getEspb(), izmenjenIshod.getPredmet().isObavezan(),
                    izmenjenIshod.getPredmet().getBrojPredavanja(), izmenjenIshod.getPredmet().getBrojVezbi(),
                    izmenjenIshod.getPredmet().getDrugiObliciNastave(), izmenjenIshod.getPredmet().getIstrazivackiRad(),
                    izmenjenIshod.getPredmet().getOstaliCasovi());

            IshodDTO ishodDTO = new IshodDTO(izmenjenIshod.getId(), izmenjenIshod.getOpis(), predmetDTO);
            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{ishodId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_NASTAVNIK", "ROLE_ADMIN"})
    public ResponseEntity<IshodDTO> delete(@PathVariable("ishodId") Long ishodId) {
        if (ishodService.findOne(ishodId).isPresent()) {
            ishodService.delete(ishodId);
            return new ResponseEntity<IshodDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.NOT_FOUND);
    }
}
