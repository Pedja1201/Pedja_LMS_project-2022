package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;


public class MestoDTO {
    private Long id;
    private String naziv;
    private DrzavaDTO drzava;

    private ArrayList<AdresaDTO> adrese = new ArrayList<AdresaDTO>();

    public MestoDTO() {super();
    }

    public MestoDTO(Long id, String naziv, DrzavaDTO drzava) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
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

    public DrzavaDTO getDrzava() {
        return drzava;
    }

    public void setDrzava(DrzavaDTO drzava) {
        this.drzava = drzava;
    }

    public ArrayList<AdresaDTO> getAdrese() {
        return adrese;
    }

    public void setAdrese(ArrayList<AdresaDTO> adrese) {
        this.adrese = adrese;
    }
}
