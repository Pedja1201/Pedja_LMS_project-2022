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
import rs.ac.singidunum.isa.app.dto.StudentNaGodiniDTO;
import rs.ac.singidunum.isa.app.model.StudentNaGodini;
import rs.ac.singidunum.isa.app.service.StudentNaGodiniService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studentNaGodini")
public class StudentNaGodiniController {
    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StudentNaGodiniDTO>> getAll() {
        ArrayList<StudentNaGodiniDTO> studentiNaGodini = new ArrayList<StudentNaGodiniDTO>();

        for (StudentNaGodini studentNaGodini : studentNaGodiniService.findAll()) {
            studentiNaGodini.add(new StudentNaGodiniDTO(studentNaGodini.getId(),
                                            studentNaGodini.getDatumUpisa(),studentNaGodini.getBrojIndeksa(),
                    new GodinaStudijaDTO(studentNaGodini.getGodinaStudija().getId(),
                                studentNaGodini.getGodinaStudija().getGodina(),null)));
        }

        return new ResponseEntity<Iterable<StudentNaGodiniDTO>>(studentiNaGodini, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.GET)
    public ResponseEntity<StudentNaGodiniDTO> get(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        Optional<StudentNaGodini> studentNaGodini = studentNaGodiniService.findOne(studentNaGodiniId);
        if (studentNaGodini.isPresent()) {
            StudentNaGodiniDTO studentNaGodiniDTO = new StudentNaGodiniDTO(studentNaGodini.get().getId(),
                                        studentNaGodini.get().getDatumUpisa(), studentNaGodini.get().getBrojIndeksa(),
                    new GodinaStudijaDTO(studentNaGodini.get().getGodinaStudija().getId(),
                            studentNaGodini.get().getGodinaStudija().getGodina(), null));
            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<StudentNaGodini> create(@RequestBody StudentNaGodini studentNaGodini) {
        try {
            studentNaGodiniService.save(studentNaGodini);
            return new ResponseEntity<StudentNaGodini>(studentNaGodini, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudentNaGodini>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.PUT)
    public ResponseEntity<StudentNaGodini> update(@PathVariable("studentNaGodiniId") Long studentNaGodiniId,
                                                   @RequestBody StudentNaGodini izmenjeniStudentNaGodini) {
        StudentNaGodini studentNaGodini = studentNaGodiniService.findOne(studentNaGodiniId).orElse(null);
        if (studentNaGodini != null) {
            izmenjeniStudentNaGodini.setId(studentNaGodiniId);
            studentNaGodiniService.save(izmenjeniStudentNaGodini);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<StudentNaGodini>(izmenjeniStudentNaGodini, HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodini>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentNaGodini> delete(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        if (studentNaGodiniService.findOne(studentNaGodiniId).isPresent()) {
            studentNaGodiniService.delete(studentNaGodiniId);
            return new ResponseEntity<StudentNaGodini>(HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodini>(HttpStatus.NOT_FOUND);
    }
}
