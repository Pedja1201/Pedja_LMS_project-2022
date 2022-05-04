package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;


public class NastavnikNaRealizacijiDTO {
    private Long id;
    private int brojCasova;
    private NastavnikDTO nastavnik;
    private TipNastaveDTO tipNastave;

    private ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();

    public NastavnikNaRealizacijiDTO() {super();
    }

    public NastavnikNaRealizacijiDTO(Long id, int brojCasova, NastavnikDTO nastavnik, TipNastaveDTO tipNastave) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.nastavnik = nastavnik;
        this.tipNastave = tipNastave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
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
