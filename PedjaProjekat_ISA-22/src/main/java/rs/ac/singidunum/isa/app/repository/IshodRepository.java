package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Ishod;

@Repository
public interface IshodRepository extends PagingAndSortingRepository<Ishod, Long> {
}
