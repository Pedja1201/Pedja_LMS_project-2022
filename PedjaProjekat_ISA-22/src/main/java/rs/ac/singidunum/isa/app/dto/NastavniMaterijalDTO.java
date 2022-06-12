package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;
import java.util.Date;

public class NastavniMaterijalDTO {
    private Long id;
    private String naziv;
    private String autor;
    private Date godinaIzdavanja;

    private ArrayList<IshodNastaveDTO> ishodiNastave = new ArrayList<IshodNastaveDTO>();

    public NastavniMaterijalDTO() {super();
    }

    public NastavniMaterijalDTO(Long id, String naziv, String autor, Date godinaIzdavanja, ArrayList<IshodNastaveDTO> ishodiNastave) {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdavanja = godinaIzdavanja;
        this.ishodiNastave = ishodiNastave;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(Date godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public ArrayList<IshodNastaveDTO> getIshodiNastave() {
        return ishodiNastave;
    }

    public void setIshodiNastave(ArrayList<IshodNastaveDTO> ishodiNastave) {
        this.ishodiNastave = ishodiNastave;
    }
}
