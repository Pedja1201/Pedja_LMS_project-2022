package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.TipEvaluacije;
import rs.ac.singidunum.app.repository.TipEvaluacijeRepository;

import java.util.Optional;

@Service
public class TipEvaluacijeService {
    @Autowired
    private TipEvaluacijeRepository tipEvaluacijeRepository;

    public Iterable<TipEvaluacije> findAll() {
        return tipEvaluacijeRepository.findAll();
    }

    public Page<TipEvaluacije> findAll(Pageable pageable) {
        return tipEvaluacijeRepository.findAll(pageable);
    }

    public Optional<TipEvaluacije> findOne(Long id) {
        return tipEvaluacijeRepository.findById(id);
    }

    public TipEvaluacije save(TipEvaluacije tipEvaluacije) {
        return tipEvaluacijeRepository.save(tipEvaluacije);
    }

    public void delete(Long id) {
        tipEvaluacijeRepository.deleteById(id);
    }

    public void delete(TipEvaluacije tipEvaluacije) {
        tipEvaluacijeRepository.delete(tipEvaluacije);
    }

}
