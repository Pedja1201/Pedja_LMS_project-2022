package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.TipEvaluacije;

@Repository
public interface TipEvaluacijeRepository extends PagingAndSortingRepository<TipEvaluacije, Long> {
}