package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Korisnik;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends PagingAndSortingRepository<Korisnik, Long> {
    ///Metoda koja dobovalja Korisnika iz baze podataka.
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}

