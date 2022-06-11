package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.EvaluacijaZnanja;
import rs.ac.singidunum.isa.app.repository.EvaluacijaZnanjaRepository;

import java.util.Optional;

@Service
public class EvaluacijaZnanjaService {
    @Autowired
    private EvaluacijaZnanjaRepository evaluacijaZnanjaRepository;

    public Iterable<EvaluacijaZnanja> findAll() {
        return evaluacijaZnanjaRepository.findAll();
    }

    public Page<EvaluacijaZnanja> findAll(Pageable pageable) {
        return evaluacijaZnanjaRepository.findAll(pageable);
    }

    public Optional<EvaluacijaZnanja> findOne(Long id) {
        return evaluacijaZnanjaRepository.findById(id);
    }

    public EvaluacijaZnanja save(EvaluacijaZnanja evaluacijaZnanja) {
        return evaluacijaZnanjaRepository.save(evaluacijaZnanja);
    }

    public void delete(Long id) {
        evaluacijaZnanjaRepository.deleteById(id);
    }

    public void delete(EvaluacijaZnanja evaluacijaZnanja) {
        evaluacijaZnanjaRepository.delete(evaluacijaZnanja);
    }

}
