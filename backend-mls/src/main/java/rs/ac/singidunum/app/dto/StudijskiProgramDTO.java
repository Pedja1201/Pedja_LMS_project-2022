package rs.ac.singidunum.app.dto;


public class StudijskiProgramDTO {
    private Long id;
    private String naziv;
    private FakultetDTO fakultet;
    private NastavnikDTO nastavnik;
    private GodinaStudijaDTO godinaStudija;

    public StudijskiProgramDTO() {super();
    }

    public StudijskiProgramDTO(Long id, String naziv, FakultetDTO fakultet, NastavnikDTO nastavnik, GodinaStudijaDTO godinaStudija) {
        this.id = id;
        this.naziv = naziv;
        this.fakultet = fakultet;
        this.nastavnik = nastavnik;
        this.godinaStudija = godinaStudija;
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

    public FakultetDTO getFakultet() {
        return fakultet;
    }

    public void setFakultet(FakultetDTO fakultet) {
        this.fakultet = fakultet;
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }

    public GodinaStudijaDTO getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
        this.godinaStudija = godinaStudija;
    }
}
