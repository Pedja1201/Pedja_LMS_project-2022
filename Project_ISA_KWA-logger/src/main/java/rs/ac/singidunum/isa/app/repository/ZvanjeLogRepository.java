package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.ZvanjeLog;

@Repository
public interface ZvanjeLogRepository extends MongoRepository<ZvanjeLog, String> {
}
