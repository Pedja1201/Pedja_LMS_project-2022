package rs.ac.singidunum.isa.app.dto;



import java.util.ArrayList;
import java.util.Date;

public class UniverzitetDTO {
    private Long id;
    private String naziv;
    private Date datumVremeOsnivanja;
    private AdresaDTO adresa;
    private NastavnikDTO nastavnik;

    private ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();

    public UniverzitetDTO() {super();
    }

    public UniverzitetDTO(Long id, String naziv, Date datumVremeOsnivanja, AdresaDTO adresa, NastavnikDTO nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.datumVremeOsnivanja = datumVremeOsnivanja;
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

    public Date getDatumVremeOsnivanja() {
        return datumVremeOsnivanja;
    }

    public void setDatumVremeOsnivanja(Date datumVremeOsnivanja) {
        this.datumVremeOsnivanja = datumVremeOsnivanja;
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

    public ArrayList<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(ArrayList<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }
}
