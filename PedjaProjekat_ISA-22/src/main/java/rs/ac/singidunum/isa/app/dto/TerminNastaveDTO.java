package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.IshodNastave;
import rs.ac.singidunum.isa.app.model.TipNastave;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;

public class TerminNastaveDTO {
    private Long id;
    private Date vremePocetka;
    private Date vremeKraja;
    private IshodNastaveDTO ishodNastave;
    private TipNastaveDTO tipNastave;

    private ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();

    public TerminNastaveDTO() {super();
    }

    public TerminNastaveDTO(Long id, Date vremePocetka, Date vremeKraja, IshodNastaveDTO ishodNastave, TipNastaveDTO tipNastave) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.ishodNastave = ishodNastave;
        this.tipNastave = tipNastave;
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

    public Date getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(Date vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public IshodNastaveDTO getIshodNastave() {
        return ishodNastave;
    }

    public void setIshodNastave(IshodNastaveDTO ishodNastave) {
        this.ishodNastave = ishodNastave;
    }

    public TipNastaveDTO getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastaveDTO tipNastave) {
        this.tipNastave = tipNastave;
    }

    public ArrayList<RealizacijaPredmetaDTO> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }
}
