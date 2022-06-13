package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/studentiNaGodini")
public class StudentNaGodiniController {
    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<StudentNaGodiniDTO>> getAll(Pageable pageable) {
        Page<StudentNaGodini> studentNaGodini = studentNaGodiniService.findAll(pageable);
        Page<StudentNaGodiniDTO> studentiNaGodini = studentNaGodini.map(new Function<StudentNaGodini, StudentNaGodiniDTO>() {
            public StudentNaGodiniDTO apply(StudentNaGodini studentNaGodini) {
                StudentNaGodiniDTO studentNaGodiniDTO = new StudentNaGodiniDTO(studentNaGodini.getId(),
                        studentNaGodini.getDatumUpisa(),studentNaGodini.getBrojIndeksa(),
                        new GodinaStudijaDTO(studentNaGodini.getGodinaStudija().getId(),
                                studentNaGodini.getGodinaStudija().getGodina(),null)
                );
                // Conversion logic
                return studentNaGodiniDTO;
            }
        });
        return new ResponseEntity<Page<StudentNaGodiniDTO>>(studentiNaGodini, HttpStatus.OK);
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
    public ResponseEntity<StudentNaGodiniDTO> create(@RequestBody StudentNaGodini studentNaGodini) {
        try {
            studentNaGodiniService.save(studentNaGodini);
            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(studentNaGodini.getGodinaStudija().getId(),
                    studentNaGodini.getGodinaStudija().getGodina(),null);

            StudentNaGodiniDTO studentNaGodiniDTO = new StudentNaGodiniDTO(studentNaGodini.getId(), studentNaGodini.getDatumUpisa(),
                    studentNaGodini.getBrojIndeksa(), godinaStudijaDTO);

            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.PUT)
    public ResponseEntity<StudentNaGodiniDTO> update(@PathVariable("studentNaGodiniId") Long studentNaGodiniId,
                                                   @RequestBody StudentNaGodini izmenjeniStudentNaGodini) {
        StudentNaGodini studentNaGodini = studentNaGodiniService.findOne(studentNaGodiniId).orElse(null);
        if (studentNaGodini != null) {
            izmenjeniStudentNaGodini.setId(studentNaGodiniId);
            izmenjeniStudentNaGodini = studentNaGodiniService.save(izmenjeniStudentNaGodini);
            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(izmenjeniStudentNaGodini.getGodinaStudija().getId(),
                    izmenjeniStudentNaGodini.getGodinaStudija().getGodina(),null);

            StudentNaGodiniDTO studentNaGodiniDTO = new StudentNaGodiniDTO(izmenjeniStudentNaGodini.getId(), izmenjeniStudentNaGodini.getDatumUpisa(),
                    izmenjeniStudentNaGodini.getBrojIndeksa(), godinaStudijaDTO);
            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentNaGodiniDTO> delete(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        if (studentNaGodiniService.findOne(studentNaGodiniId).isPresent()) {
            studentNaGodiniService.delete(studentNaGodiniId);
            return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }
}
