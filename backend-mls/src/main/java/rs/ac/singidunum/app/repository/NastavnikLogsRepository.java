package rs.ac.singidunum.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.NastavnikLog;
@Repository
public interface NastavnikLogsRepository extends MongoRepository<NastavnikLog, String> {
}
