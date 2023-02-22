package rs.ac.singidunum.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.StudentLog;

@Repository
public interface StudentLogsRepository extends MongoRepository<StudentLog, String> {
}
