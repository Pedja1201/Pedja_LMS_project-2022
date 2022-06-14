package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;

public class StudentDTO extends KorisnikDTO {
    private String jmbg;
    private String ime;
    private AdresaDTO adresa;
//    private PohadjanjePredmetaDTO pohadjanjePredmeta;
    private StudentNaGodiniDTO studentNaGodini;

    private ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = new ArrayList<PohadjanjePredmetaDTO>();

    public StudentDTO() {super();
    }

    public StudentDTO(Long id, String korisnickoIme, String lozinka, String jmbg, String ime, AdresaDTO adresa, StudentNaGodiniDTO studentNaGodini) {
        super(id, korisnickoIme, lozinka);
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.studentNaGodini = studentNaGodini;
    }

    public StudentDTO(Long id, String korisnickoIme, String lozinka, String jmbg, String ime) {
        super(id, korisnickoIme, lozinka);
        this.jmbg = jmbg;
        this.ime = ime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }



    public StudentNaGodiniDTO getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodiniDTO studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public ArrayList<PohadjanjePredmetaDTO> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }
}
