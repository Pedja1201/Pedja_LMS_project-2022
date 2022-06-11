package rs.ac.singidunum.isa.app.dto;



import java.util.ArrayList;
import java.util.Date;


public class StudentNaGodiniDTO {
    private Long id;
    private Date datumUpisa;
    private String brojIndeksa;
    private GodinaStudijaDTO godinaStudija;

    private ArrayList<StudentDTO> studenti = new ArrayList<StudentDTO>();
    private ArrayList<PolaganjeDTO> polaganja = new ArrayList<PolaganjeDTO>();

    public StudentNaGodiniDTO() {super();
    }

    public StudentNaGodiniDTO(Long id, Date datumUpisa, String brojIndeksa, GodinaStudijaDTO godinaStudija) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.godinaStudija = godinaStudija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public GodinaStudijaDTO getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public ArrayList<StudentDTO> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<StudentDTO> studenti) {
        this.studenti = studenti;
    }

    public ArrayList<PolaganjeDTO> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(ArrayList<PolaganjeDTO> polaganja) {
        this.polaganja = polaganja;
    }
}
