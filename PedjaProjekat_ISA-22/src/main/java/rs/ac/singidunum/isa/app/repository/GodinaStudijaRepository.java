package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.GodinaStudija;

@Repository
public interface GodinaStudijaRepository extends CrudRepository<GodinaStudija, Long> {
    //Metoda i upit za pronalaženje predmeta na Godini studija
    @Query(value = "SELECT g FROM GodinaStudija g WHERE g.predmet.naziv = :naziv")
    public Iterable<GodinaStudija> findPredmetGodineStudija(String naziv);
}
