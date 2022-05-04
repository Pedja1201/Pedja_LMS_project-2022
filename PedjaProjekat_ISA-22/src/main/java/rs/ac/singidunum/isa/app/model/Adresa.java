package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String ulica;
    @Column(nullable = false)
    private String broj;

    @ManyToOne(optional = false)
    private Mesto mesto;

    @OneToMany(mappedBy = "adresa")
    private Set<Univerzitet> univerziteti = new HashSet<Univerzitet>();
    @OneToMany(mappedBy = "adresa")
    private Set<Fakultet> fakulteti = new HashSet<Fakultet>();
    @OneToMany(mappedBy = "adresa")
    private Set<Nastavnik> nastavnici = new HashSet<Nastavnik>();
    @OneToMany(mappedBy = "adresa")
    private Set<Student> studenti = new HashSet<Student>();


    public Adresa() {super();
    }

    public Adresa(Long id, String ulica, String broj, Mesto mesto) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Set<Univerzitet> getUniverziteti() {
        return univerziteti;
    }

    public void setUniverziteti(Set<Univerzitet> univerziteti) {
        this.univerziteti = univerziteti;
    }

    public Set<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(Set<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public Set<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(Set<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }
}
