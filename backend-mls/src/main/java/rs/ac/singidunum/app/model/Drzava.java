package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Drzava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "drzava")
    private Set<Mesto> mesta = new HashSet<Mesto>();

    public Drzava() {super();
    }

    public Drzava(Long id, String naziv) {
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

    public Set<Mesto> getMesta() {
        return mesta;
    }

    public void setMesta(Set<Mesto> mesta) {
        this.mesta = mesta;
    }
}
