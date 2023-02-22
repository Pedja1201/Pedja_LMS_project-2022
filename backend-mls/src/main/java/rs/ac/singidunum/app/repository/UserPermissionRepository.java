package rs.ac.singidunum.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.app.model.UserPermission;

@Repository
public interface UserPermissionRepository extends CrudRepository<UserPermission,Long> {
}
