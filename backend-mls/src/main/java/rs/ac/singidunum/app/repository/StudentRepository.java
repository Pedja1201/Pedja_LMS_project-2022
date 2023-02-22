package rs.ac.singidunum.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Nastavnik;
import rs.ac.singidunum.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.app.model.Predmet;
import rs.ac.singidunum.app.model.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
//    @Query("select s from Student s join s.pohadjanjePredmeta sPohadjanje join sPohadjanje.realizacijaPredmeta spRealizacija join spRealizacija.predmet sprPredmet " +
//            "join sprPredmet.realizacijePredmeta sprpRealizacija join sprpRealizacija.nastavnikNaRealizaciji sprprNastavnik join sprprNastavnik.nastavnik sprprN where sprprN = :nastavnik")
//    public Iterable<Student> pronadjiSpisakStudenataZaPredmeteNaKojimaJeAngazovanProfesor(Nastavnik nastavnik);
    @Query("select s from Student s join s.pohadjanjaPredmeta sPohadjanje join sPohadjanje.realizacijaPredmeta spRealizacija join spRealizacija.predmet join spRealizacija.nastavnikNaRealizaciji sprNastavnikNaRealizaciji join sprNastavnikNaRealizaciji.nastavnik sprnNastavnik where sprnNastavnik = :nnr")
    public Iterable<Student> pronadjiStudenteZaPredmeteNastavnika(@Param("nnr")Nastavnik nnr);

    @Query("select pp from PohadjanjePredmeta pp join pp.student ppStudent where ppStudent = :student and pp.konacnaOcena > 5 ")
    public Iterable<PohadjanjePredmeta> prosecnaOcena(@Param("student")Student student);

    @Query("select p from Predmet p join p.realizacijePredmeta pRealizacija join pRealizacija.pohadjanjaPredmeta prPPredmeta join prPPredmeta.student prppStudent where prppStudent = :student and prPPredmeta.konacnaOcena>5")
    public Iterable<Predmet> ukupniBodovi(@Param("student")Student student);

    @Query("select p from Predmet p join p.realizacijePredmeta pRealizacija join pRealizacija.pohadjanjaPredmeta prPPredmeta join prPPredmeta.student prppStudent where prppStudent = :student")
    public Iterable<Predmet> predmetiKojeSlusaStudent(@Param("student")Student student);
}


