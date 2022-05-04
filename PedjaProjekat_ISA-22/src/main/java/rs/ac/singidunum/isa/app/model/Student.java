package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String jmbg;
    @Column(nullable = false)
    private String ime;

    @ManyToOne(optional = false)
    private Adresa adresa;
    @ManyToOne(optional = false)
    private PohadjanjePredmeta pohadjanjePredmeta;
    @ManyToOne(optional = false)
    private StudentNaGodini studentNaGodini;

    public Student() {super();
    }

    public Student(Long id, String jmbg, String ime, Adresa adresa, PohadjanjePredmeta pohadjanjePredmeta, StudentNaGodini studentNaGodini) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.pohadjanjePredmeta = pohadjanjePredmeta;
        this.studentNaGodini = studentNaGodini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public PohadjanjePredmeta getPohadjanjePredmeta() {
        return pohadjanjePredmeta;
    }

    public void setPohadjanjePredmeta(PohadjanjePredmeta pohadjanjePredmeta) {
        this.pohadjanjePredmeta = pohadjanjePredmeta;
    }

    public StudentNaGodini getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }
}
