package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Mesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @ManyToOne(optional = false)
    private Drzava drzava;

    @OneToMany(mappedBy = "mesto")
    private Set<Adresa> adrese = new HashSet<Adresa>();

    public Mesto() {super();
    }

    public Mesto(Long id, String naziv, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
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

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public Set<Adresa> getAdrese() {
        return adrese;
    }

    public void setAdrese(Set<Adresa> adrese) {
        this.adrese = adrese;
    }
}
