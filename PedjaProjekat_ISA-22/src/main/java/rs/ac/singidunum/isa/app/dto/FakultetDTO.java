package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;


public class FakultetDTO {
    private Long id;
    private String naziv;
    private UniverzitetDTO univerzitet;
    private AdresaDTO adresa;
    private NastavnikDTO nastavnik;

    private ArrayList<StudijskiProgramDTO> studijskiProgrami = new ArrayList<StudijskiProgramDTO>();

    public FakultetDTO() {super();
    }

    public FakultetDTO(Long id, String naziv, UniverzitetDTO univerzitet, AdresaDTO adresa, NastavnikDTO nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.adresa = adresa;
        this.nastavnik = nastavnik;
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

    public UniverzitetDTO getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(UniverzitetDTO univerzitet) {
        this.univerzitet = univerzitet;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }

    public ArrayList<StudijskiProgramDTO> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
