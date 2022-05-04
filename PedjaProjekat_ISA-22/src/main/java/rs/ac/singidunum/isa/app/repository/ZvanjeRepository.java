package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Zvanje;

@Repository
public interface ZvanjeRepository extends CrudRepository<Zvanje, Long> {
}
