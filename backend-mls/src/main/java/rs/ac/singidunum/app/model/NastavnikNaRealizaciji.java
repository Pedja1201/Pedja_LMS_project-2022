package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NastavnikNaRealizaciji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int brojCasova;

    @ManyToOne
    private Nastavnik nastavnik;
    @ManyToOne
    private TipNastave tipNastave;

    @OneToMany(mappedBy = "nastavnikNaRealizaciji")
    private Set<RealizacijaPredmeta> realizacijePredmeta = new HashSet<RealizacijaPredmeta>();

    public NastavnikNaRealizaciji() {super();
    }

    public NastavnikNaRealizaciji(Long id, int brojCasova, Nastavnik nastavnik, TipNastave tipNastave) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.nastavnik = nastavnik;
        this.tipNastave = tipNastave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }
}
