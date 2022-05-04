package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Ishod;
import rs.ac.singidunum.isa.app.repository.IshodRepository;

import java.util.Optional;

@Service
public class IshodService {
    @Autowired
    private IshodRepository ishodRepository;

    public IshodService() {
        super();
    }

    public IshodService(IshodRepository ishodRepository) {
        super();
        this.ishodRepository = ishodRepository;
    }

    public IshodRepository getIshodRepository() {
        return ishodRepository;
    }

    public void setIshodRepository(IshodRepository ishodRepository) {
        this.ishodRepository = ishodRepository;
    }

    public Iterable<Ishod> findAll() {
        return ishodRepository.findAll();
    }

    public Optional<Ishod> findOne(Long id) {
        return ishodRepository.findById(id);
    }


    public Ishod save(Ishod ishod) {
        return ishodRepository.save(ishod);
    }

    public void delete(Long id) {
        ishodRepository.deleteById(id);
    }

    public void delete(Ishod ishod) {
        ishodRepository.delete(ishod);
    }
}
