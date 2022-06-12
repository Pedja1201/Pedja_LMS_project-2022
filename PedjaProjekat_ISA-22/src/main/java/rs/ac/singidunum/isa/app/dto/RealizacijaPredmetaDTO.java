package rs.ac.singidunum.isa.app.dto;


import rs.ac.singidunum.isa.app.model.EvaluacijaZnanja;

import java.util.ArrayList;


public class RealizacijaPredmetaDTO {
    private Long id;
    private String naziv;
    private NastavnikNaRealizacijiDTO nastavnikNaRealizaciji;
    private PredmetDTO predmet;
    private EvaluacijaZnanjaDTO evaluacijaZnanja;
    private TerminNastaveDTO terminNastave;

    private ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = new ArrayList<PohadjanjePredmetaDTO>();

    public RealizacijaPredmetaDTO() {super();
    }

    public RealizacijaPredmetaDTO(Long id, String naziv, NastavnikNaRealizacijiDTO nastavnikNaRealizaciji,
                                  PredmetDTO predmet, EvaluacijaZnanjaDTO evaluacijaZnanja, TerminNastaveDTO terminNastave) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.predmet = predmet;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.terminNastave = terminNastave;
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

    public EvaluacijaZnanjaDTO getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
    }

    public TerminNastaveDTO getTerminNastave() {
        return terminNastave;
    }

    public void setTerminNastave(TerminNastaveDTO terminNastave) {
        this.terminNastave = terminNastave;
    }
}
