package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.PohadjanjePredmeta;

@Repository
public interface PohadjanjePredmetaRepository extends PagingAndSortingRepository<PohadjanjePredmeta, Long> {
}
