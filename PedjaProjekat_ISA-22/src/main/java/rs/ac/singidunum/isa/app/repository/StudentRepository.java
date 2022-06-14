package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.model.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
//    @Query("select s from Student s join s.pohadjanjePredmeta sPohadjanje join sPohadjanje.realizacijaPredmeta spRealizacija join spRealizacija.predmet sprPredmet " +
//            "join sprPredmet.realizacijePredmeta sprpRealizacija join sprpRealizacija.nastavnikNaRealizaciji sprprNastavnik join sprprNastavnik.nastavnik sprprN where sprprN = :nastavnik")
//    public Iterable<Student> pronadjiSpisakStudenataZaPredmeteNaKojimaJeAngazovanProfesor(Nastavnik nastavnik);
}
