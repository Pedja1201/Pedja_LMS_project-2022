package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;

public class IshodDTO {
    private Long id;
    private String opis;
    private PredmetDTO predmet;

    private ArrayList<EvaluacijaZnanjaDTO> evalucijeZnanja = new ArrayList<EvaluacijaZnanjaDTO>();

    public IshodDTO() {super();
    }

    public IshodDTO(Long id, String opis, PredmetDTO predmet) {
        this.id = id;
        this.opis = opis;
        this.predmet = predmet;
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

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public ArrayList<EvaluacijaZnanjaDTO> getEvalucijeZnanja() {
        return evalucijeZnanja;
    }

    public void setEvalucijeZnanja(ArrayList<EvaluacijaZnanjaDTO> evalucijeZnanja) {
        this.evalucijeZnanja = evalucijeZnanja;
    }
}
