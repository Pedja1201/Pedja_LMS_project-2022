package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @ManyToOne
    private Univerzitet univerzitet;

    @ManyToOne(optional = false)
    private Adresa adresa;

    @ManyToOne
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "fakultet")
    private Set<StudijskiProgram> studijskiProgrami = new HashSet<StudijskiProgram>();

    public Fakultet() {super();
    }

    public Fakultet(Long id, String naziv, Univerzitet univerzitet, Adresa adresa, Nastavnik nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.adresa = adresa;
        this.nastavnik = nastavnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Univerzitet getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(Univerzitet univerzitet) {
        this.univerzitet = univerzitet;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
