package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.StudentNaGodini;

@Repository
public interface StudentNaGodiniRepository extends PagingAndSortingRepository<StudentNaGodini, Long> {
}
