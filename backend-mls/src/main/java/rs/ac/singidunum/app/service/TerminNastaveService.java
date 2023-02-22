package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.TerminNastave;
import rs.ac.singidunum.app.repository.TerminNastaveRepository;

import java.util.Optional;

@Service
public class TerminNastaveService {
    @Autowired
    private TerminNastaveRepository terminNastaveRepository;

    public Iterable<TerminNastave> findAll() {
        return terminNastaveRepository.findAll();
    }

    public Page<TerminNastave> findAll(Pageable pageable) {
        return terminNastaveRepository.findAll(pageable);
    }

    public Optional<TerminNastave> findOne(Long id) {
        return terminNastaveRepository.findById(id);
    }

    public TerminNastave save(TerminNastave terminNastave) {
        return terminNastaveRepository.save(terminNastave);
    }

    public void delete(Long id) {
        terminNastaveRepository.deleteById(id);
    }

    public void delete(TerminNastave terminNastave) {
        terminNastaveRepository.delete(terminNastave);
    }

}
