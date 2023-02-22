package rs.ac.singidunum.app.dto;

public class AdministratorDTO extends KorisnikDTO{

    private String ime;
    private String jmbg;

    public AdministratorDTO() {
        super();
    }

    public AdministratorDTO(Long id, String korisnickoIme, String lozinka,String ime,String jmbg) {
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
