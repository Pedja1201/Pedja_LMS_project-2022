package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Mesto;

@Repository
public interface MestoRepository extends CrudRepository<Mesto, Long> {
    //Metoda i upit za pronalaženje svih karata koje je kupio zadati putnik(Zadatak sa klk)
    @Query(value = "SELECT m FROM Mesto m WHERE m.drzava.naziv = :naziv")
    public Iterable<Mesto> findDrzava(String naziv);
}
