package rs.ac.singidunum.isa.app.dto;


import java.util.ArrayList;


public class PohadjanjePredmetaDTO {
    private Long id;
    private int konacnaOcena;
    private RealizacijaPredmetaDTO realizacijaPredmeta;

//    private ArrayList<StudentDTO> studenti = new ArrayList<StudentDTO>();
    private StudentDTO student;

    public PohadjanjePredmetaDTO() {super();
    }

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena, RealizacijaPredmetaDTO realizacijaPredmeta, StudentDTO student) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(int konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
