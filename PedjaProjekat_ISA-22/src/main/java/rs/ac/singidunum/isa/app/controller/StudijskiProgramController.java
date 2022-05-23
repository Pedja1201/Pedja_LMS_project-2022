package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.aspect.Logged;
import rs.ac.singidunum.isa.app.dto.FakultetDTO;
import rs.ac.singidunum.isa.app.dto.GodinaStudijaDTO;
import rs.ac.singidunum.isa.app.dto.NastavnikDTO;
import rs.ac.singidunum.isa.app.dto.StudijskiProgramDTO;
import rs.ac.singidunum.isa.app.model.StudijskiProgram;
import rs.ac.singidunum.isa.app.service.StudijskiProgramService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studijskiProgrami")
public class StudijskiProgramController {
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @Logged
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StudijskiProgramDTO>> getAll() {
        ArrayList<StudijskiProgramDTO> studijskiProgrami = new ArrayList<StudijskiProgramDTO>();

        for (StudijskiProgram studijskiProgram : studijskiProgramService.findAll()) {
            studijskiProgrami.add(new StudijskiProgramDTO(studijskiProgram.getId(),studijskiProgram.getNaziv(),
                    new FakultetDTO(studijskiProgram.getFakultet().getId(),studijskiProgram.getFakultet().getNaziv(),
                                                                null,null,null),
                    new NastavnikDTO(studijskiProgram.getNastavnik().getId(), studijskiProgram.getNastavnik().getKorisnickoIme(),
                            studijskiProgram.getNastavnik().getLozinka(),studijskiProgram.getNastavnik().getIme(),
                            studijskiProgram.getNastavnik().getBiografija(),studijskiProgram.getNastavnik().getJmbg(),null,null),
                    new GodinaStudijaDTO(studijskiProgram.getGodinaStudija().getId(), studijskiProgram.getGodinaStudija().getGodina(),null)));
        }
        return new ResponseEntity<Iterable<StudijskiProgramDTO>>(studijskiProgrami, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studijskiProgramId}", method = RequestMethod.GET)
    public ResponseEntity<StudijskiProgramDTO> get(@PathVariable("studijskiProgramId") Long studijskiProgramId) {
        Optional<StudijskiProgram> studijskiProgram = studijskiProgramService.findOne(studijskiProgramId);
        if (studijskiProgram.isPresent()) {
            StudijskiProgramDTO prodajaDTO = new StudijskiProgramDTO(studijskiProgram.get().getId(),studijskiProgram.get().getNaziv(),
                    new FakultetDTO(studijskiProgram.get().getFakultet().getId(),
                            studijskiProgram.get().getFakultet().getNaziv(),null,null,null),
                    new NastavnikDTO(studijskiProgram.get().getNastavnik().getId(),studijskiProgram.get().getNastavnik().getKorisnickoIme(),
                            studijskiProgram.get().getNastavnik().getLozinka(),studijskiProgram.get().getNastavnik().getIme(),
                            studijskiProgram.get().getNastavnik().getBiografija(), studijskiProgram.get().getNastavnik().getJmbg(),
                                            null,null),
                    new GodinaStudijaDTO(studijskiProgram.get().getGodinaStudija().getId(),
                            studijskiProgram.get().getGodinaStudija().getGodina(), null));

            return new ResponseEntity<StudijskiProgramDTO>(prodajaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<StudijskiProgram> create(@RequestBody StudijskiProgram studijskiProgram) {
        try {
            studijskiProgramService.save(studijskiProgram);
            return new ResponseEntity<StudijskiProgram>(studijskiProgram, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudijskiProgram>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studisjkiProgramId}", method = RequestMethod.PUT)
    public ResponseEntity<StudijskiProgram> update(@PathVariable("studisjkiProgramId") Long studisjkiProgramId,
                                               @RequestBody StudijskiProgram izmenjeniStudijskiProgram) {
        StudijskiProgram studijskiProgram = studijskiProgramService.findOne(studisjkiProgramId).orElse(null);
        if (studijskiProgram != null) {
            izmenjeniStudijskiProgram.setId(studisjkiProgramId);
            izmenjeniStudijskiProgram = studijskiProgramService.save(izmenjeniStudijskiProgram);
            return new ResponseEntity<StudijskiProgram>(izmenjeniStudijskiProgram, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgram>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studisjkiProgramId}", method = RequestMethod.DELETE)
    public ResponseEntity<StudijskiProgram> delete(@PathVariable("studisjkiProgramId") Long studisjkiProgramId) {
        if (studijskiProgramService.findOne(studisjkiProgramId).isPresent()) {
            studijskiProgramService.delete(studisjkiProgramId);
            return new ResponseEntity<StudijskiProgram>(HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgram>(HttpStatus.NOT_FOUND);
    }
}
