package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NastavniMaterijal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private String autor;
    @Temporal(TemporalType.DATE)
    private Date godinaIzdavanja;

    @OneToMany(mappedBy = "nastavniMaterijal")
    private Set<IshodNastave> ishodiNastave = new HashSet<IshodNastave>();

    public NastavniMaterijal() {super();
    }

    public NastavniMaterijal(Long id, String naziv, String autor, Date godinaIzdavanja) {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdavanja = godinaIzdavanja;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(Date godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public Set<IshodNastave> getIshodiNastave() {
        return ishodiNastave;
    }

    public void setIshodiNastave(Set<IshodNastave> ishodiNastave) {
        this.ishodiNastave = ishodiNastave;
    }
}
