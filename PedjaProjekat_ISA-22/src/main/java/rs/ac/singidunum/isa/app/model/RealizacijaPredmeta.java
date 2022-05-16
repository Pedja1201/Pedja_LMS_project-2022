package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RealizacijaPredmeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @ManyToOne
    private NastavnikNaRealizaciji nastavnikNaRealizaciji;

    @ManyToOne(optional = false)
    private Predmet predmet;

    @OneToMany(mappedBy = "realizacijaPredmeta")
    private Set<PohadjanjePredmeta> pohadjanjaPredmeta = new HashSet<PohadjanjePredmeta>();

    public RealizacijaPredmeta() {super();
    }

    public RealizacijaPredmeta(Long id, String naziv, NastavnikNaRealizaciji nastavnikNaRealizaciji, Predmet predmet) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.predmet = predmet;
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

    public NastavnikNaRealizaciji getNastavnikNaRealizaciji() {
        return nastavnikNaRealizaciji;
    }

    public void setNastavnikNaRealizaciji(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Set<PohadjanjePredmeta> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }
}
