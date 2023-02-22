package rs.ac.singidunum.app.dto;


import java.util.ArrayList;

public class TipEvaluacijeDTO {
    private Long id;
    private String naziv;

    private ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja = new ArrayList<EvaluacijaZnanjaDTO>();

    public TipEvaluacijeDTO() {super();
    }

    public TipEvaluacijeDTO(Long id, String naziv, ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja) {
        this.id = id;
        this.naziv = naziv;
        this.evaluacijeZnanja = evaluacijeZnanja;
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

    public ArrayList<EvaluacijaZnanjaDTO> getEvaluacijeZnanja() {
        return evaluacijeZnanja;
    }

    public void setEvaluacijeZnanja(ArrayList<EvaluacijaZnanjaDTO> evaluacijeZnanja) {
        this.evaluacijeZnanja = evaluacijeZnanja;
    }
}
