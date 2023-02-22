package rs.ac.singidunum.app.model;

import javax.persistence.*;

@Entity
public class PohadjanjePredmeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int konacnaOcena;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;
    @ManyToOne
    private Student student;

//    @OneToMany(mappedBy = "pohadjanjePredmeta")
//    private Set<Student> studenti = new HashSet<Student>();


    public PohadjanjePredmeta() {
    }

    public PohadjanjePredmeta(Long id, int konacnaOcena, RealizacijaPredmeta realizacijaPredmeta, Student student) {
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

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
