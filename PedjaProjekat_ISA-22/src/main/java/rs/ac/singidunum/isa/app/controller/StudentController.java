package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.aspect.LoggedStudent;
import rs.ac.singidunum.isa.app.dto.AdresaDTO;
import rs.ac.singidunum.isa.app.dto.PohadjanjePredmetaDTO;
import rs.ac.singidunum.isa.app.dto.StudentDTO;
import rs.ac.singidunum.isa.app.dto.StudentNaGodiniDTO;
import rs.ac.singidunum.isa.app.model.Student;
import rs.ac.singidunum.isa.app.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studenti")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @LoggedStudent
    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<StudentDTO>> getAll() {
        ArrayList<StudentDTO> studenti = new ArrayList<StudentDTO>();

        for (Student student : studentService.findAll()) {
            studenti.add(new StudentDTO(student.getId(),student.getKorisnickoIme(), student.getLozinka(),student.getJmbg(), student.getIme(),
                    new AdresaDTO(student.getAdresa().getId(), student.getAdresa().getUlica(),
                            student.getAdresa().getBroj(),null),
                    new PohadjanjePredmetaDTO(student.getPohadjanjePredmeta().getId(), student.getPohadjanjePredmeta().getKonacnaOcena(),
                            null),
                    new StudentNaGodiniDTO(student.getStudentNaGodini().getId(), student.getStudentNaGodini().getDatumUpisa(),
                            student.getStudentNaGodini().getBrojIndeksa(),null)));
        }

        return new ResponseEntity<Iterable<StudentDTO>>(studenti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> get(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        if (student.isPresent()) {
            StudentDTO studentDTO = new StudentDTO(student.get().getId(),student.get().getKorisnickoIme(),student.get().getLozinka(),
                                            student.get().getJmbg(), student.get().getIme(),
                    new AdresaDTO(student.get().getAdresa().getId(), student.get().getAdresa().getUlica(),
                            student.get().getAdresa().getBroj(), null),
                    new PohadjanjePredmetaDTO(student.get().getPohadjanjePredmeta().getId(),
                            student.get().getPohadjanjePredmeta().getKonacnaOcena(), null),
                    new StudentNaGodiniDTO(student.get().getStudentNaGodini().getId(), student.get().getStudentNaGodini().getDatumUpisa(),
                            student.get().getStudentNaGodini().getBrojIndeksa(),null));

            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        try {
            studentService.save(student);
            return new ResponseEntity<Student>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Student> update(@PathVariable("studentId") Long studentId,
                                                   @RequestBody Student izmenjenStudent) {
        Student student = studentService.findOne(studentId).orElse(null);
        if (student != null) {
            izmenjenStudent.setId(studentId);
            studentService.save(izmenjenStudent);  //FIXME:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            return new ResponseEntity<Student>(izmenjenStudent, HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> delete(@PathVariable("studentId") Long studentId) {
        if (studentService.findOne(studentId).isPresent()) {
            studentService.delete(studentId);
            return new ResponseEntity<Student>(HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
}
