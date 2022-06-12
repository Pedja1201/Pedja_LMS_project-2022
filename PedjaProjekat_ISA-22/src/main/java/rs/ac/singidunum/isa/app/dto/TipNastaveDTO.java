package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;

public class TipNastaveDTO {
    private Long id;
    private String naziv;

    private ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji = new ArrayList<NastavnikNaRealizacijiDTO>();
    private ArrayList<TerminNastaveDTO> terminiNastave = new ArrayList<TerminNastaveDTO>();

    public TipNastaveDTO() {super();
    }

    public TipNastaveDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    public ArrayList<NastavnikNaRealizacijiDTO> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public ArrayList<TerminNastaveDTO> getTerminiNastave() {
        return terminiNastave;
    }

    public void setTerminiNastave(ArrayList<TerminNastaveDTO> terminiNastave) {
        this.terminiNastave = terminiNastave;
    }
}
