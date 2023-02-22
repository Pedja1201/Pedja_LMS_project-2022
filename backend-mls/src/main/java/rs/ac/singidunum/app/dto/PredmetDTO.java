package rs.ac.singidunum.app.dto;

import java.util.ArrayList;


public class PredmetDTO {
    private Long id;
    private String naziv;
    private int espb;
    private boolean obavezan;
    private int brojPredavanja;
    private int brojVezbi;
    private int drugiObliciNastave;
    private int istrazivackiRad;
    private int ostaliCasovi;

    private ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();
    private ArrayList<GodinaStudijaDTO> godineStudija = new ArrayList<GodinaStudijaDTO>();
    private ArrayList<IshodDTO> ishodi = new ArrayList<IshodDTO>();

    public PredmetDTO() {super();
    }

    public PredmetDTO(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public boolean isObavezan() {
        return obavezan;
    }

    public void setObavezan(boolean obavezan) {
        this.obavezan = obavezan;
    }

    public int getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(int brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
    }

    public int getBrojVezbi() {
        return brojVezbi;
    }

    public void setBrojVezbi(int brojVezbi) {
        this.brojVezbi = brojVezbi;
    }

    public int getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(int drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public int getIstrazivackiRad() {
        return istrazivackiRad;
    }

    public void setIstrazivackiRad(int istrazivackiRad) {
        this.istrazivackiRad = istrazivackiRad;
    }

    public int getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(int ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public ArrayList<RealizacijaPredmetaDTO> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public ArrayList<GodinaStudijaDTO> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(ArrayList<GodinaStudijaDTO> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public ArrayList<IshodDTO> getIshodi() {
        return ishodi;
    }

    public void setIshodi(ArrayList<IshodDTO> ishodi) {
        this.ishodi = ishodi;
    }
}
