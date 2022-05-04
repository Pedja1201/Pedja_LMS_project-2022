package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Nastavnik;

@Repository
public interface NastvanikRepository extends CrudRepository<Nastavnik, Long> {
    //Metoda za pronalazenje prethodnih zvanja nastavnika (priprema klk)
    @Query(value = "SELECT n FROM Nastavnik n WHERE n.zvanje.id = :id")
    public Iterable<Nastavnik> findZvanjeNastavnik(Long id);
}
