package rs.ac.singidunum.isa.app.dto;


public class IshodDTO {
    private Long id;
    private String opis;
    private PredmetDTO predmet;

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
}
