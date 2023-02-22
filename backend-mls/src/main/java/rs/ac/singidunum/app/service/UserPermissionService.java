package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.UserPermission;
import rs.ac.singidunum.app.repository.UserPermissionRepository;

import java.util.Optional;

@Service
public class UserPermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public Iterable<UserPermission> findAll() {
        return userPermissionRepository.findAll();
    }

    public Optional<UserPermission> findOne(Long id) {
        return userPermissionRepository.findById(id);
    }

    public UserPermission save(UserPermission userPermission) {
        return userPermissionRepository.save(userPermission);
    }

    public void delete(Long id) {
        userPermissionRepository.deleteById(id);
    }

    public void delete(UserPermission userPermission) {
        userPermissionRepository.delete(userPermission);
    }
}
