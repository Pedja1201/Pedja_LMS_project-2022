package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private int espb;
    @Column(nullable = false)
    private boolean obavezan;
    @Column(nullable = false)
    private int brojPredavanja;
    @Column(nullable = false)
    private int brojVezbi;
    @Column(nullable = false)
    private int drugiObliciNastave;
    @Column(nullable = false)
    private int istrazivackiRad;
    @Column(nullable = false)
    private int ostaliCasovi;

    @OneToMany(mappedBy = "predmet")
    private Set<RealizacijaPredmeta> realizacijePredmeta = new HashSet<RealizacijaPredmeta>();
    @OneToMany(mappedBy = "predmet")
    private Set<GodinaStudija> godineStudija = new HashSet<GodinaStudija>();
    @OneToMany(mappedBy = "predmet")
    private Set<Ishod> ishodi = new HashSet<Ishod>();

    public Predmet() {super();
    }

    public Predmet(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public boolean isObavezan() {
        return obavezan;
    }

    public void setObavezan(boolean obavezan) {
        this.obavezan = obavezan;
    }

    public int getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(int brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
    }

    public int getBrojVezbi() {
        return brojVezbi;
    }

    public void setBrojVezbi(int brojVezbi) {
        this.brojVezbi = brojVezbi;
    }

    public int getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(int drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public int getIstrazivackiRad() {
        return istrazivackiRad;
    }

    public void setIstrazivackiRad(int istrazivackiRad) {
        this.istrazivackiRad = istrazivackiRad;
    }

    public int getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(int ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Set<GodinaStudija> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(Set<GodinaStudija> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public Set<Ishod> getIshodi() {
        return ishodi;
    }

    public void setIshodi(Set<Ishod> ishodi) {
        this.ishodi = ishodi;
    }
}
