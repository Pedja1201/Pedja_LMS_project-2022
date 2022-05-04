package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.TipNastave;

@Repository
public interface TipNastaveRepository extends CrudRepository<TipNastave, Long> {
}
