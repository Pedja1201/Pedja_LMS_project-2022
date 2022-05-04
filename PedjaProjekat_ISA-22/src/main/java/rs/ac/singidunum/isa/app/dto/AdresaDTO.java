package rs.ac.singidunum.isa.app.dto;

import java.util.ArrayList;


public class AdresaDTO {
    private Long id;
    private String ulica;
    private String broj;
    private MestoDTO mesto;

    private ArrayList<UniverzitetDTO> univerziteti = new ArrayList<UniverzitetDTO>();
    private ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();
    private ArrayList<NastavnikDTO> nastavnici = new ArrayList<NastavnikDTO>();
    private ArrayList<StudentDTO> studenti = new ArrayList<StudentDTO>();

    public AdresaDTO() {super();
    }

    public AdresaDTO(Long id, String ulica, String broj, MestoDTO mesto) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public MestoDTO getMesto() {
        return mesto;
    }

    public void setMesto(MestoDTO mesto) {
        this.mesto = mesto;
    }

    public ArrayList<UniverzitetDTO> getUniverziteti() {
        return univerziteti;
    }

    public void setUniverziteti(ArrayList<UniverzitetDTO> univerziteti) {
        this.univerziteti = univerziteti;
    }

    public ArrayList<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(ArrayList<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public ArrayList<NastavnikDTO> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(ArrayList<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public ArrayList<StudentDTO> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<StudentDTO> studenti) {
        this.studenti = studenti;
    }
}
