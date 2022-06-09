package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipZvanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;


    @OneToMany(mappedBy = "tipZvanja")
    private Set<Zvanje> zvanja = new HashSet<Zvanje>();

    public TipZvanja() {super();
    }

    public TipZvanja(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    public Set<Zvanje> getZvanja() {
        return zvanja;
    }

    public void setZvanja(Set<Zvanje> zvanja) {
        this.zvanja = zvanja;
    }
}
