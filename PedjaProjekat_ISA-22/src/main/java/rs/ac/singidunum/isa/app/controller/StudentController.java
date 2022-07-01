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
import rs.ac.singidunum.isa.app.aspect.LoggedStudent;
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.model.Predmet;
import rs.ac.singidunum.isa.app.model.Student;
import rs.ac.singidunum.isa.app.service.NastavnikService;
import rs.ac.singidunum.isa.app.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/studenti")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private NastavnikService nastavnikService;

    @LoggedStudent
    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<Page<StudentDTO>> getAll(Pageable pageable) {
        Page<Student> student = studentService.findAll(pageable);
        Page<StudentDTO> studenti = student.map(new Function<Student, StudentDTO>() {
            public StudentDTO apply(Student student) {
                StudentDTO studentDTO = new StudentDTO(student.getId(),student.getKorisnickoIme(),student.getLozinka(),
                        student.getEmail(), student.getJmbg(), student.getIme(),
                        new AdresaDTO(student.getAdresa().getId(), student.getAdresa().getUlica(),
                                student.getAdresa().getBroj(),null),
                        new StudentNaGodiniDTO(student.getStudentNaGodini().getId(), student.getStudentNaGodini().getDatumUpisa(),
                                student.getStudentNaGodini().getBrojIndeksa(),null)
                );
                // Conversion logic
                return studentDTO;
            }
        });
        return new ResponseEntity<Page<StudentDTO>>(studenti, HttpStatus.OK);
    }

    @LoggedStudent
    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<StudentDTO> get(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        if (student.isPresent()) {
            StudentDTO studentDTO = new StudentDTO(student.get().getId(),student.get().getKorisnickoIme(),student.get().getLozinka(),
                    student.get().getEmail(), student.get().getJmbg(),student.get().getIme(),
                    new AdresaDTO(student.get().getAdresa().getId(), student.get().getAdresa().getUlica(),
                            student.get().getAdresa().getBroj(), null),
                    new StudentNaGodiniDTO(student.get().getStudentNaGodini().getId(), student.get().getStudentNaGodini().getDatumUpisa(),
                            student.get().getStudentNaGodini().getBrojIndeksa(),null));

            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedStudent
    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<StudentDTO> create(@RequestBody Student student) {
        try {
            studentService.save(student);
            StudentDTO studentDTO = new StudentDTO(student.getId(),student.getKorisnickoIme(),student.getLozinka(),
                    student.getEmail(), student.getJmbg(), student.getIme(),
                    new AdresaDTO(student.getAdresa().getId(), student.getAdresa().getUlica(),
                            student.getAdresa().getBroj(),null),
                    new StudentNaGodiniDTO(student.getStudentNaGodini().getId(), student.getStudentNaGodini().getDatumUpisa(),
                            student.getStudentNaGodini().getBrojIndeksa(),null));
            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedStudent
    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{studentId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Student> update(@PathVariable("studentId") Long studentId,
                                          @RequestBody Student izmenjenStudent) {
        Student student = studentService.findOne(studentId).orElse(null);
        if (student != null) {
            izmenjenStudent.setId(studentId);
            studentService.save(izmenjenStudent);  //DONE:Sa ovim radi bez BUG-a (Beskonacna rekurzija!)-Roditelj
            StudentDTO studentDTO = new StudentDTO(izmenjenStudent.getId(),izmenjenStudent.getKorisnickoIme(),izmenjenStudent.getLozinka(),
                    izmenjenStudent.getEmail(),izmenjenStudent.getJmbg(), izmenjenStudent.getIme(),
                    new AdresaDTO(izmenjenStudent.getAdresa().getId(), izmenjenStudent.getAdresa().getUlica(),
                            izmenjenStudent.getAdresa().getBroj(),null),
                    new StudentNaGodiniDTO(izmenjenStudent.getStudentNaGodini().getId(), izmenjenStudent.getStudentNaGodini().getDatumUpisa(),
                            izmenjenStudent.getStudentNaGodini().getBrojIndeksa(),null));
            return new ResponseEntity<Student>(izmenjenStudent, HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    @LoggedStudent
    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> delete(@PathVariable("studentId") Long studentId) {
        if (studentService.findOne(studentId).isPresent()) {
            studentService.delete(studentId);
            return new ResponseEntity<Student>(HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    //ispravljena metoda za dobavljanje svih predmeta na kojima je angazovan nastavnik
    @RequestMapping(path = "/nastavnik/{id}", method = RequestMethod.GET)
    @Secured({"ROLE_NASTAVNIK"})
    public ResponseEntity<Iterable<StudentDTO>> pronadjiStudenteNaPredmetimaNastavnika(@PathVariable("id")Long nastavnikId) {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<StudentDTO>();
        Nastavnik n = this.nastavnikService.findOne(nastavnikId).orElse(null);
        for (Student student : this.studentService.studentiNaPredmetimaNastavnika(n)) {
            studentDTOS.add(new StudentDTO(student.getId(), student.getEmail(), student.getKorisnickoIme(), student.getLozinka(), student.getJmbg(), student.getIme(),
                    new AdresaDTO(student.getAdresa().getId(), student.getAdresa().getUlica(),
                            student.getAdresa().getBroj(), null),
                    new StudentNaGodiniDTO(student.getStudentNaGodini().getId(), student.getStudentNaGodini().getDatumUpisa(),
                            student.getStudentNaGodini().getBrojIndeksa(), null)));
        }
        return new ResponseEntity<Iterable<StudentDTO>>(studentDTOS, HttpStatus.OK);
    }

    //TODO:upotrebiti ovo sa vise smisla
    //metoda za dovavljanje preosecne ocene i ukupnog broja espb bodova treba je jos upotrebiti
    @RequestMapping(path = "/{id}/prosecnaOcena", method = RequestMethod.GET)
    public void izracunajProsecnu(@PathVariable("id")Long studentId){
        System.out.println(this.studentService.prosecnaOcena(this.studentService.findOne(studentId).orElse(null)));
        System.out.println(this.studentService.brojEspb(this.studentService.findOne(studentId).orElse(null)));
    }


    //metoda za dobavljanje svih predmeta koje slusa student sa {id}
    @RequestMapping(path = "/{id}/predmeti", method = RequestMethod.GET)
    public ResponseEntity<Iterable<PredmetDTO>> predmetiKojeSlusaStudent(@PathVariable("id")Long studentId){
        ArrayList<PredmetDTO> predmetDTOS = new ArrayList<PredmetDTO>();
        for(Predmet p : this.studentService.predmetiKojeSlusaStudent(this.studentService.findOne(studentId).orElse(null))){
            predmetDTOS.add(new PredmetDTO(p.getId(), p.getNaziv(),p.getEspb(),p.isObavezan(),p.getBrojPredavanja(),p.getBrojVezbi(),p.getDrugiObliciNastave(),p.getIstrazivackiRad(),p.getOstaliCasovi()));
        }
        return new ResponseEntity<Iterable<PredmetDTO>>(predmetDTOS, HttpStatus.OK);
    }

}
