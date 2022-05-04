package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;

@Entity
public class StudijskiProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @ManyToOne(optional = false)
    private Fakultet fakultet;

    @ManyToOne(optional = false)
    private Nastavnik nastavnik;

    @ManyToOne(optional = false)
    private GodinaStudija godinaStudija;

    public StudijskiProgram() {super();
    }

    public StudijskiProgram(Long id, String naziv, Fakultet fakultet, Nastavnik nastavnik, GodinaStudija godinaStudija) {
        this.id = id;
        this.naziv = naziv;
        this.fakultet = fakultet;
        this.nastavnik = nastavnik;
        this.godinaStudija = godinaStudija;
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

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }
}
