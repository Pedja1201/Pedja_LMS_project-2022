package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;

public class IshodNastaveDTO {
    private Long id;
    private String opis;
    private NastavniMaterijalDTO nastavniMaterijal;

    private ArrayList<TerminNastaveDTO> terminiNasatve = new ArrayList<TerminNastaveDTO>();

    public IshodNastaveDTO() {super();
    }

    public IshodNastaveDTO(Long id, String opis, NastavniMaterijalDTO nastavniMaterijal) {
        this.id = id;
        this.opis = opis;
        this.nastavniMaterijal = nastavniMaterijal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public NastavniMaterijalDTO getNastavniMaterijal() {
        return nastavniMaterijal;
    }

    public void setNastavniMaterijal(NastavniMaterijalDTO nastavniMaterijal) {
        this.nastavniMaterijal = nastavniMaterijal;
    }

    public ArrayList<TerminNastaveDTO> getTerminiNasatve() {
        return terminiNasatve;
    }

    public void setTerminiNasatve(ArrayList<TerminNastaveDTO> terminiNasatve) {
        this.terminiNasatve = terminiNasatve;
    }
}
