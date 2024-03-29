package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.Administrator;
import rs.ac.singidunum.app.repository.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Iterable<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Page<Administrator> findAll(Pageable pageable) {
        return administratorRepository.findAll(pageable);
    }

    public Optional<Administrator> findOne(Long id) {
        return administratorRepository.findById(id);
    }

    public Administrator save(Administrator administrator) { return administratorRepository.save(administrator); }

    public void delete(Long id) {
        administratorRepository.deleteById(id);
    }

    public void delete(Administrator administrator) {
        administratorRepository.delete(administrator);
    }
}
