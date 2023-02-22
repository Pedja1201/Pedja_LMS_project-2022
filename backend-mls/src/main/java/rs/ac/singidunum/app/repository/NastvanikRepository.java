package rs.ac.singidunum.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Nastavnik;

@Repository
public interface NastvanikRepository extends PagingAndSortingRepository<Nastavnik, Long> {
    //Metoda za pronalazenje prethodnih zvanja nastavnika (priprema klk)
    @Query(value = "SELECT n FROM Nastavnik n WHERE n.zvanje.id = :id")
    public Iterable<Nastavnik> findZvanjeNastavnik(Long id);
}
