package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.TipEvaluacije;


import java.util.ArrayList;
import java.util.Date;

public class EvaluacijaZnanjaDTO {
    private Long id;
    private Date vremePocetka;
    private Date vremeZavrsetka;
    private TipEvaluacijeDTO tipEvaluacije;
    private IshodDTO ishod;

    private ArrayList<PolaganjeDTO> polaganja = new ArrayList<PolaganjeDTO>();
    private ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();

    public EvaluacijaZnanjaDTO() {super();
    }

    public EvaluacijaZnanjaDTO(Long id, Date vremePocetka, Date vremeZavrsetka, TipEvaluacijeDTO tipEvaluacije, IshodDTO ishod) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.tipEvaluacije = tipEvaluacije;
        this.ishod = ishod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }


    public TipEvaluacijeDTO getTipEvaluacije() {
        return tipEvaluacije;
    }

    public void setTipEvaluacije(TipEvaluacijeDTO tipEvaluacije) {
        this.tipEvaluacije = tipEvaluacije;
    }

    public IshodDTO getIshod() {
        return ishod;
    }

    public void setIshod(IshodDTO ishod) {
        this.ishod = ishod;
    }

    public ArrayList<PolaganjeDTO> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(ArrayList<PolaganjeDTO> polaganja) {
        this.polaganja = polaganja;
    }

    public ArrayList<RealizacijaPredmetaDTO> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }
}
