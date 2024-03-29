package rs.ac.singidunum.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Fakultet;

@Repository
public interface FakultetRepository extends PagingAndSortingRepository<Fakultet, Long> {
    //Metoda i upit za pronalaženje   univerziteta
    @Query(value = "SELECT f FROM Fakultet f WHERE f.univerzitet.naziv = :naziv")
    public Iterable<Fakultet> findUniverzitetFakulteta(String naziv);

    //Metoda i upit za pronalaženje   nastavnika
    @Query(value = "SELECT f FROM Fakultet f WHERE f.nastavnik.ime = :ime")
    public Iterable<Fakultet> findNastavnikaFakulteta(String ime);
}
