package rs.ac.singidunum.isa.app.dto;


public class PolaganjeDTO {
    private Long id;
    private int bodovi;
    private String napomena;

    private EvaluacijaZnanjaDTO evaluacijaZnanja;
    private StudentNaGodiniDTO studentNaGodini;

    public PolaganjeDTO() {super();
    }

    public PolaganjeDTO(Long id, int bodovi, String napomena, EvaluacijaZnanjaDTO evaluacijaZnanja, StudentNaGodiniDTO studentNaGodini) {
        this.id = id;
        this.bodovi = bodovi;
        this.napomena = napomena;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.studentNaGodini = studentNaGodini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public EvaluacijaZnanjaDTO getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
    }

    public StudentNaGodiniDTO getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodiniDTO studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }
}
