package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;


public class RealizacijaPredmetaDTO {
    private Long id;
    private String naziv;
    private NastavnikNaRealizacijiDTO nastavnikNaRealizaciji;
    private PredmetDTO predmet;

    private ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = new ArrayList<PohadjanjePredmetaDTO>();

    public RealizacijaPredmetaDTO() {super();
    }

    public RealizacijaPredmetaDTO(Long id, String naziv, NastavnikNaRealizacijiDTO nastavnikNaRealizaciji, PredmetDTO predmet) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.predmet = predmet;
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

    public NastavnikNaRealizacijiDTO getNastavnikNaRealizaciji() {
        return nastavnikNaRealizaciji;
    }

    public void setNastavnikNaRealizaciji(NastavnikNaRealizacijiDTO nastavnikNaRealizaciji) {
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public ArrayList<PohadjanjePredmetaDTO> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }
}
