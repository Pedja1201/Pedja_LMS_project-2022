package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;

public class DrzavaDTO {
    private Long id;
    private String naziv;

    private ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();

    public DrzavaDTO() {super();
    }

    public DrzavaDTO(Long id, String naziv, ArrayList<MestoDTO> mesta) {
        this.id = id;
        this.naziv = naziv;
        this.mesta = mesta;
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

    public ArrayList<MestoDTO> getMesta() {
        return mesta;
    }

    public void setMesta(ArrayList<MestoDTO> mesta) {
        this.mesta = mesta;
    }
}
