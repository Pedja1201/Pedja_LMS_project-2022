package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;

public class NastavnikDTO {
    private Long id;
    private String ime;
    private String biografija;
    private String jmbg;
    private AdresaDTO adresa;
    private ZvanjeDTO zvanje;

    private ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();

    private ArrayList<StudijskiProgramDTO> studijskiProgrami = new ArrayList<StudijskiProgramDTO>();

    private ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji = new ArrayList<NastavnikNaRealizacijiDTO>();

    private ArrayList<UniverzitetDTO> univerziteti = new ArrayList<UniverzitetDTO>();

    public NastavnikDTO() {super();
    }

    public NastavnikDTO(Long id, String ime, String biografija, String jmbg, AdresaDTO adresa, ZvanjeDTO zvanje) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.zvanje = zvanje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public ZvanjeDTO getZvanje() {
        return zvanje;
    }

    public void setZvanje(ZvanjeDTO zvanje) {
        this.zvanje = zvanje;
    }

    public ArrayList<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(ArrayList<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public ArrayList<StudijskiProgramDTO> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }

    public ArrayList<NastavnikNaRealizacijiDTO> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public ArrayList<UniverzitetDTO> getUniverziteti() {
        return univerziteti;
    }

    public void setUniverziteti(ArrayList<UniverzitetDTO> univerziteti) {
        this.univerziteti = univerziteti;
    }
}
