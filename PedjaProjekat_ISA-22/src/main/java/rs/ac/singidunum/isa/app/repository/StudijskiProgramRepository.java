package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.StudijskiProgram;

@Repository
public interface StudijskiProgramRepository extends CrudRepository<StudijskiProgram, Long> {
    //Metoda za pronalazenje svih Fakulteta studijskih programa
    @Query(value = "SELECT s FROM StudijskiProgram s WHERE s.fakultet.naziv = :naziv")
    public Iterable<StudijskiProgram> findFakultetStudijskogProgrmama(String naziv);
}
