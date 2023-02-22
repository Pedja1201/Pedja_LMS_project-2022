package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GodinaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date godina;

    @ManyToOne(optional = false)
    private Predmet predmet;

    @OneToMany(mappedBy = "godinaStudija")
    private Set<StudentNaGodini> studentiNaGodini = new HashSet<StudentNaGodini>();

    public GodinaStudija() {super();
    }

    public GodinaStudija(Long id, Date godina, Predmet predmet) {
        this.id = id;
        this.godina = godina;
        this.predmet = predmet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGodina() {
        return godina;
    }

    public void setGodina(Date godina) {
        this.godina = godina;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Set<StudentNaGodini> getStudentiNaGodini() {
        return studentiNaGodini;
    }

    public void setStudentiNaGodini(Set<StudentNaGodini> studentiNaGodini) {
        this.studentiNaGodini = studentiNaGodini;
    }
}
