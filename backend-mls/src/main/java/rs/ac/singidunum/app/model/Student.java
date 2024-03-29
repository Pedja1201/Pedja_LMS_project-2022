package rs.ac.singidunum.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Korisnik {
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = false)
    private String jmbg;
    @Column(nullable = false)
    private String ime;

    @ManyToOne
    private Adresa adresa;
    @ManyToOne
    private StudentNaGodini studentNaGodini;
    //    @ManyToOne
//    private PohadjanjePredmeta pohadjanjePredmeta;

    @OneToMany(mappedBy = "student")
    private Set<PohadjanjePredmeta> pohadjanjaPredmeta = new HashSet<PohadjanjePredmeta>();

    public Student() {super();
    }

    public Student(Long id, String korisnickoIme, String lozinka,String email, String jmbg, String ime, Adresa adresa,
          StudentNaGodini studentNaGodini) {
        super(id, korisnickoIme, lozinka);
        this.email = email;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.studentNaGodini = studentNaGodini;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public StudentNaGodini getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public Set<PohadjanjePredmeta> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }
}
