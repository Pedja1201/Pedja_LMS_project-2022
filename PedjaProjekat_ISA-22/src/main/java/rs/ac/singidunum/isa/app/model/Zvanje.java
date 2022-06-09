package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zvanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIzbora;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPrestanka;

    @ManyToOne
    private NaucnaOblast naucnaOblast;
    @ManyToOne(optional = false)
    private TipZvanja tipZvanja;

    @OneToMany(mappedBy = "zvanje")
    private Set<Nastavnik> nastavnici = new HashSet<Nastavnik>();

    public Zvanje() {super();
    }

    public Zvanje(Long id, Date datumIzbora, Date datumPrestanka, NaucnaOblast naucnaOblast, TipZvanja tipZvanja) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.naucnaOblast = naucnaOblast;
        this.tipZvanja = tipZvanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public TipZvanja getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanja tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public Set<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(Set<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }
}
