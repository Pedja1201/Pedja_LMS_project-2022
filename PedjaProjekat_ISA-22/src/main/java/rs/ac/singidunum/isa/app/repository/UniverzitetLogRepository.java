package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.UniverzitetLog;

@Repository
public interface UniverzitetLogRepository extends MongoRepository<UniverzitetLog, String> {
}
