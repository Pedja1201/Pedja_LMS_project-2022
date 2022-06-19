package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EvaluacijaZnanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date vremePocetka;
    @Temporal(TemporalType.DATE)
    private Date vremeZavrsetka;
    @ManyToOne(optional = false)
    private TipEvaluacije tipEvaluacije;
    @ManyToOne(optional = false)
    private Ishod ishod;

    @OneToMany(mappedBy = "evaluacijaZnanja")
    private Set<Polaganje> polaganja = new HashSet<Polaganje>();
    @OneToMany(mappedBy = "evaluacijaZnanja")
    private Set<RealizacijaPredmeta> realizacijePredmeta = new HashSet<RealizacijaPredmeta>();

    public EvaluacijaZnanja() {super();
    }

    public EvaluacijaZnanja(Long id, Date vremePocetka, Date vremeZavrsetka, TipEvaluacije tipEvaluacije, Ishod ishod) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.tipEvaluacije = tipEvaluacije;
        this.ishod = ishod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }


    public TipEvaluacije getTipEvaluacije() {
        return tipEvaluacije;
    }

    public void setTipEvaluacije(TipEvaluacije tipEvaluacije) {
        this.tipEvaluacije = tipEvaluacije;
    }

    public Ishod getIshod() {
        return ishod;
    }

    public void setIshod(Ishod ishod) {
        this.ishod = ishod;
    }

    public Set<Polaganje> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(Set<Polaganje> polaganja) {
        this.polaganja = polaganja;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }
}
