package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVremeOsnivanja;

    @ManyToOne(optional = false)
    private Adresa adresa;

    @ManyToOne(optional = false)
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "univerzitet")
    private Set<Fakultet> fakulteti = new HashSet<Fakultet>();

    public Univerzitet() {super();
    }

    public Univerzitet(Long id, String naziv, Date datumVremeOsnivanja,Adresa adresa, Nastavnik nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.datumVremeOsnivanja = datumVremeOsnivanja;
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

    public Date getDatumVremeOsnivanja() {
        return datumVremeOsnivanja;
    }

    public void setDatumVremeOsnivanja(Date datumVremeOsnivanja) {
        this.datumVremeOsnivanja = datumVremeOsnivanja;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Set<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(Set<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }
}
