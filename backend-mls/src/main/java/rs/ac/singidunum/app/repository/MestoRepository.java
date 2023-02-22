package rs.ac.singidunum.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Mesto;

@Repository
public interface MestoRepository extends PagingAndSortingRepository<Mesto, Long> {
    //Metoda i upit za pronalaženje svih karata koje je kupio zadati putnik(Zadatak sa klk)
    @Query(value = "SELECT m FROM Mesto m WHERE m.drzava.naziv = :naziv")
    public Iterable<Mesto> findDrzava(String naziv);
}
