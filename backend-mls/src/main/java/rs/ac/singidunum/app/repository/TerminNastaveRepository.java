package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.TerminNastave;

@Repository
public interface TerminNastaveRepository extends PagingAndSortingRepository<TerminNastave, Long> {
}
