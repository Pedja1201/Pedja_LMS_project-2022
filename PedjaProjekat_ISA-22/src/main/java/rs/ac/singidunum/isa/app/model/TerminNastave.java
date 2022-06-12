package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TerminNastave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePocetka;
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeKraja;
    @ManyToOne
    private IshodNastave ishodNastave;
    @ManyToOne
    private TipNastave tipNastave;

    @OneToMany(mappedBy = "terminNastave")
    private Set<RealizacijaPredmeta> realizacijePredmeta = new HashSet<RealizacijaPredmeta>();

    public TerminNastave() {super();
    }

    public TerminNastave(Long id, Date vremePocetka, Date vremeKraja, IshodNastave ishodNastave, TipNastave tipNastave) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.ishodNastave = ishodNastave;
        this.tipNastave = tipNastave;
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

    public Date getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(Date vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public IshodNastave getIshodNastave() {
        return ishodNastave;
    }

    public void setIshodNastave(IshodNastave ishodNastave) {
        this.ishodNastave = ishodNastave;
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
