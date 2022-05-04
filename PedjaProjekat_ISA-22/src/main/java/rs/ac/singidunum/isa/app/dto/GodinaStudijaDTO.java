package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;
import java.util.Date;


public class GodinaStudijaDTO {
    private Long id;
    private Date godina;
    private PredmetDTO predmet;

    private ArrayList<StudentNaGodiniDTO> studentiNaGodini = new ArrayList<StudentNaGodiniDTO>();

    public GodinaStudijaDTO() {super();
    }

    public GodinaStudijaDTO(Long id, Date godina, PredmetDTO predmet) {
        this.id = id;
        this.godina = godina;
        this.predmet = predmet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGodina() {
        return godina;
    }

    public void setGodina(Date godina) {
        this.godina = godina;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public ArrayList<StudentNaGodiniDTO> getStudentiNaGodini() {
        return studentiNaGodini;
    }

    public void setStudentiNaGodini(ArrayList<StudentNaGodiniDTO> studentiNaGodini) {
        this.studentiNaGodini = studentiNaGodini;
    }
}
