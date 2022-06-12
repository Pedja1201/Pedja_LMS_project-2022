package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class IshodNastave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String opis;

    @ManyToOne(optional = true)
    private NastavniMaterijal nastavniMaterijal;

    @OneToMany(mappedBy = "ishodNastave")
    private Set<TerminNastave> terminiNastave = new HashSet<TerminNastave>();

    public IshodNastave() {super();
    }

    public IshodNastave(Long id, String opis, NastavniMaterijal nastavniMaterijal) {
        this.id = id;
        this.opis = opis;
        this.nastavniMaterijal = nastavniMaterijal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public NastavniMaterijal getNastavniMaterijal() {
        return nastavniMaterijal;
    }

    public void setNastavniMaterijal(NastavniMaterijal nastavniMaterijal) {
        this.nastavniMaterijal = nastavniMaterijal;
    }

    public Set<TerminNastave> getTerminiNastave() {
        return terminiNastave;
    }

    public void setTerminiNastave(Set<TerminNastave> terminiNastave) {
        this.terminiNastave = terminiNastave;
    }
}
