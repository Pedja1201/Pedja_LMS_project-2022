package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipEvaluacije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;


    @OneToMany(mappedBy = "tipEvaluacije")
    private Set<EvaluacijaZnanja> evaluacijeZnanja = new HashSet<EvaluacijaZnanja>();

    public TipEvaluacije() {super();
    }

    public TipEvaluacije(Long id, String naziv) {
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

    public Set<EvaluacijaZnanja> getEvaluacijeZnanja() {
        return evaluacijeZnanja;
    }

    public void setEvaluacijeZnanja(Set<EvaluacijaZnanja> evaluacijeZnanja) {
        this.evaluacijeZnanja = evaluacijeZnanja;
    }
}
