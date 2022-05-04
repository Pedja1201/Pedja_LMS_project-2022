package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Fakultet;

@Repository
public interface FakultetRepository extends CrudRepository<Fakultet, Long> {
    //Metoda i upit za pronalaženje svih  univerziteta
    @Query(value = "SELECT f FROM Fakultet f WHERE f.univerzitet.id = :id")
    public Iterable<Fakultet> findUniverzitet(Long id);
}
