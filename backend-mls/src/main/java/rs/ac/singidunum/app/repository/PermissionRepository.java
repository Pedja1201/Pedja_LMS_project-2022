package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
