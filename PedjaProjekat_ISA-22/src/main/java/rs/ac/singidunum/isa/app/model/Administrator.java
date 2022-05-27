package rs.ac.singidunum.isa.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrator extends Korisnik{

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String jmbg;

    public Administrator() {
        super();
    }

    public Administrator(Long id, String korisnickoIme, String lozinka, String ime, String jmbg) {
        super(id, korisnickoIme, lozinka);
        this.ime = ime;
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
