package rs.ac.singidunum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.app.aspect.LoggedNastavnik;
import rs.ac.singidunum.app.dto.GodinaStudijaDTO;
import rs.ac.singidunum.app.dto.StudentNaGodiniDTO;
import rs.ac.singidunum.app.model.StudentNaGodini;
import rs.ac.singidunum.app.service.StudentNaGodiniService;

import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/studentiNaGodini")
public class StudentNaGodiniController {
    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
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

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<StudentNaGodiniDTO> delete(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        if (studentNaGodiniService.findOne(studentNaGodiniId).isPresent()) {
            studentNaGodiniService.delete(studentNaGodiniId);
            return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }
}
