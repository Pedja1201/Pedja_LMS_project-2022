package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ishod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String opis;

    @ManyToOne(optional = false)
    private Predmet predmet;

    @OneToMany(mappedBy = "ishod")
    private Set<EvaluacijaZnanja> evaluacijeZnanja = new HashSet<EvaluacijaZnanja>();

    public Ishod() {super();
    }

    public Ishod(Long id, String opis, Predmet predmet) {
        this.id = id;
        this.opis = opis;
        this.predmet = predmet;
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

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Set<EvaluacijaZnanja> getEvaluacijeZnanja() {
        return evaluacijeZnanja;
    }

    public void setEvaluacijeZnanja(Set<EvaluacijaZnanja> evaluacijeZnanja) {
        this.evaluacijeZnanja = evaluacijeZnanja;
    }
}
