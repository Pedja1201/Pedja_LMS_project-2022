package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;

@Entity
public class Polaganje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int bodovi;
    @Column(nullable = false)
    private String napomena;

    @ManyToOne(optional = false)
    private EvaluacijaZnanja evaluacijaZnanja;
    @ManyToOne(optional = false)
    private StudentNaGodini studentNaGodini;

    public Polaganje() {super();
    }

    public Polaganje(Long id, int bodovi, String napomena, EvaluacijaZnanja evaluacijaZnanja, StudentNaGodini studentNaGodini) {
        this.id = id;
        this.bodovi = bodovi;
        this.napomena = napomena;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.studentNaGodini = studentNaGodini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public EvaluacijaZnanja getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(EvaluacijaZnanja evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
    }

    public StudentNaGodini getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }
}
