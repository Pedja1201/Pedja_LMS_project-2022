package rs.ac.singidunum.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Polaganje;

@Repository
public interface PolaganjeRepository extends PagingAndSortingRepository<Polaganje, Long> {
    //Metoda za pronalazenje  studenta na godini koji polaze
    @Query(value = "SELECT p FROM Polaganje p WHERE p.studentNaGodini.brojIndeksa = :brojIndeksa")
    public Iterable<Polaganje> findStudentNaGodini(String brojIndeksa);
}
