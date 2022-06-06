package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;

@Repository
public interface RealizacijaPredmetaRepository extends PagingAndSortingRepository<RealizacijaPredmeta, Long> {
    //Metoda i upit za pronalaženje  Predmeta u ralizaciji
    @Query(value = "SELECT r FROM RealizacijaPredmeta r WHERE r.predmet.naziv = :naziv")
    public Iterable<RealizacijaPredmeta> findPredmetURealizaciji(String naziv);
}
