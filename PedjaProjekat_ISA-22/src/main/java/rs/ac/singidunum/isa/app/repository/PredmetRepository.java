package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.model.Predmet;

import java.util.Optional;

@Repository
public interface PredmetRepository extends PagingAndSortingRepository<Predmet, Long> {

    //TODO: nepotrebna metoda ne radi nesto bez @Param
    //Vraca predmete ne kojima je nastavnik nastavnikNaRealizaciji
    @Query("select p from Predmet p join p.realizacijePredmeta rP join rP.nastavnikNaRealizaciji NNR join NNR.nastavnik n where ( :nnr is null or n = :nnr) ")
    public Iterable<Predmet> findbyNastavnik(Optional<Nastavnik> nnr);

    //ista metoda kao gore samo sa @Param jer iz nekog razloga trazi sada
    @Query("select p from Predmet p join p.realizacijePredmeta rP join rP.nastavnikNaRealizaciji NNR join NNR.nastavnik n where ( :nnr is null or n = :nnr) ")
    public Iterable<Predmet> findPredmeteForNastavnik(@Param("nnr") Optional<Nastavnik> nnr);
}

