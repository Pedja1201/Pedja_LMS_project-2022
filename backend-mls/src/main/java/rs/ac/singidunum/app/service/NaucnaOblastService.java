package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.NaucnaOblast;
import rs.ac.singidunum.app.repository.NaucnaOblastRepository;

import java.util.Optional;

@Service
public class NaucnaOblastService {
    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    public Iterable<NaucnaOblast> findAll() {
        return naucnaOblastRepository.findAll();
    }

    public Page<NaucnaOblast> findAll(Pageable pageable) {
        return naucnaOblastRepository.findAll(pageable);
    }

    public Optional<NaucnaOblast> findOne(Long id) {
        return naucnaOblastRepository.findById(id);
    }

    public NaucnaOblast save(NaucnaOblast naucnaOblast) {
        return naucnaOblastRepository.save(naucnaOblast);
    }

    public void delete(Long id) {
        naucnaOblastRepository.deleteById(id);
    }

    public void delete(NaucnaOblast naucnaOblast) {
        naucnaOblastRepository.delete(naucnaOblast);
    }
}
