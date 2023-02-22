package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.IshodNastave;

@Repository
public interface IshodNastaveRepository extends PagingAndSortingRepository<IshodNastave, Long> {
}
