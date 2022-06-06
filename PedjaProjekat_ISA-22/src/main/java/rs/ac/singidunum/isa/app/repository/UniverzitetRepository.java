package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Univerzitet;

@Repository
public interface UniverzitetRepository extends PagingAndSortingRepository<Univerzitet, Long> {
    //Metoda i upit za pronalaženje svih Nastavnika na Univerzitetu
    @Query(value = "SELECT u FROM Univerzitet u WHERE u.nastavnik.ime = :ime")
    public Iterable<Univerzitet> findNastavnikUniverziteta(String ime);
}
