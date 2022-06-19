package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentNaGodini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date datumUpisa;
    @Column(nullable = false)
    private String brojIndeksa;

    @ManyToOne(optional = false)
    private GodinaStudija godinaStudija;

    @OneToMany(mappedBy = "studentNaGodini")
    private Set<Student> studenti = new HashSet<Student>();

    @OneToMany(mappedBy = "studentNaGodini")
    private Set<Polaganje> polaganja = new HashSet<Polaganje>();

    public StudentNaGodini() {super();
    }

    public StudentNaGodini(Long id, Date datumUpisa, String brojIndeksa, GodinaStudija godinaStudija) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.godinaStudija = godinaStudija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }

    public Set<Polaganje> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(Set<Polaganje> polaganja) {
        this.polaganja = polaganja;
    }
}
