package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Adresa;

@Repository
public interface AdresaRepository extends PagingAndSortingRepository<Adresa, Long> {
    //Metoda i upit za pronalaženje mesta po nazivu
    @Query(value = "SELECT a FROM Adresa a WHERE a.mesto.naziv = :naziv")
    public Iterable<Adresa> findMestoAdresee(String naziv);
}
