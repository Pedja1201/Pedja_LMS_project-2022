package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Drzava;
import rs.ac.singidunum.isa.app.repository.DrzavaRepository;

import java.util.Optional;

@Service
public class DrzavaService {
    @Autowired
    private DrzavaRepository drzavaRepository;

    public DrzavaService() {
        super();
    }

    public DrzavaService(DrzavaRepository drzavaRepository) {
        super();
        this.drzavaRepository = drzavaRepository;
    }

    public DrzavaRepository getDrzavaRepository() {
        return drzavaRepository;
    }

    public void setDrzavaRepository(DrzavaRepository drzavaRepository) {
        this.drzavaRepository = drzavaRepository;
    }

    public Iterable<Drzava> findAll() {
        return drzavaRepository.findAll();
    }

    public Page<Drzava> findAll(Pageable pageable) {
        return drzavaRepository.findAll(pageable);
    }

    public Optional<Drzava> findOne(Long id) {
        return drzavaRepository.findById(id);
    }


    public Drzava save(Drzava drzava) {
        return drzavaRepository.save(drzava);
    }

    public void delete(Long id) {
        drzavaRepository.deleteById(id);
    }

    public void delete(Drzava drzava) {
        drzavaRepository.delete(drzava);
    }
}
