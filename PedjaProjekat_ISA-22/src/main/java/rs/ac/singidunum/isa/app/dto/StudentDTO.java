package rs.ac.singidunum.isa.app.dto;



public class StudentDTO {
    private Long id;
    private String jmbg;
    private String ime;
    private AdresaDTO adresa;
    private PohadjanjePredmetaDTO pohadjanjePredmeta;
    private StudentNaGodiniDTO studentNaGodini;

    public StudentDTO() {super();
    }

    public StudentDTO(Long id, String jmbg, String ime, AdresaDTO adresa, PohadjanjePredmetaDTO pohadjanjePredmeta, StudentNaGodiniDTO studentNaGodini) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.pohadjanjePredmeta = pohadjanjePredmeta;
        this.studentNaGodini = studentNaGodini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PohadjanjePredmetaDTO getPohadjanjePredmeta() {
        return pohadjanjePredmeta;
    }

    public void setPohadjanjePredmeta(PohadjanjePredmetaDTO pohadjanjePredmeta) {
        this.pohadjanjePredmeta = pohadjanjePredmeta;
    }

    public StudentNaGodiniDTO getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodiniDTO studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }
}
