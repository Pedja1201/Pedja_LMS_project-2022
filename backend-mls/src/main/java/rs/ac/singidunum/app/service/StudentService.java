package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.Nastavnik;
import rs.ac.singidunum.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.app.model.Predmet;
import rs.ac.singidunum.app.model.Student;
import rs.ac.singidunum.app.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
        super();
    }

    public StudentService(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> findOne(Long id) {
        return studentRepository.findById(id);
    }


    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

//    public Iterable<Student> studentiNaPredmetimaNastavnika(Nastavnik nastavnik) { return this.studentRepository.pronadjiSpisakStudenataZaPredmeteNaKojimaJeAngazovanProfesor(nastavnik); }
//ispravljena metoda
public Iterable<Student> studentiNaPredmetimaNastavnika(Nastavnik nastavnik) { return this.studentRepository.pronadjiStudenteZaPredmeteNastavnika(nastavnik); }

    //TODO: Upotrebiti ovo verovatno ce nam trebati StudentPrikazDTO da bi dobavljali sve studente za metodu iz specifikacije kojom se dobavljaju podaci o studentu
    public double prosecnaOcena(Student student) {
        Iterable<PohadjanjePredmeta> polozeniPredmeti = this.studentRepository.prosecnaOcena(student);
        double sum = 0;
        int i = 0;
        for(PohadjanjePredmeta p : polozeniPredmeti){
            sum+= p.getKonacnaOcena();
            i += 1;
        }
        double prosecnaOcena = sum/i;
        return Math.round(prosecnaOcena * 100.0)/100.0;
    }

    //TODO: Upotrebiti ovo verovatno ce nam trebati StudentPrikazDTO da bi dobavljali sve studente za metodu iz specifikacije kojom se dobavljaju podaci o studentu
    public int brojEspb(Student student){
        Iterable<Predmet> polozeniPredmeti = this.studentRepository.ukupniBodovi(student);
        int espb=0;
        for(Predmet p : polozeniPredmeti){
            espb += p.getEspb();
        }
        return espb;
    }

    public Iterable<Predmet> predmetiKojeSlusaStudent(Student student){
        return this.studentRepository.predmetiKojeSlusaStudent(student);
    }

}
