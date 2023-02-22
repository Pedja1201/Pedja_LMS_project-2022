package rs.ac.singidunum.app.dto;


import java.util.ArrayList;
import java.util.Date;

public class ZvanjeDTO {
    private Long id;
    private Date datumIzbora;
    private Date datumPrestanka;
    private NaucnaOblastDTO naucnaOblast;
    private TipZvanjaDTO tipZvanja;
    private ArrayList<NastavnikDTO> nastavnici = new ArrayList<NastavnikDTO>();

    public ZvanjeDTO() {super();
    }

    public ZvanjeDTO(Long id, Date datumIzbora, Date datumPrestanka, NaucnaOblastDTO naucnaOblast, TipZvanjaDTO tipZvanja) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.naucnaOblast = naucnaOblast;
        this.tipZvanja = tipZvanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public NaucnaOblastDTO getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblastDTO naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public TipZvanjaDTO getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanjaDTO tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public ArrayList<NastavnikDTO> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(ArrayList<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }
}
