package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.TipZvanja;
import rs.ac.singidunum.isa.app.repository.TipZvanjaRepository;

import java.util.Optional;

@Service
public class TipZvanjaService {
    @Autowired
    private TipZvanjaRepository tipZvanjaRepository;

    public Iterable<TipZvanja> findAll() {
        return tipZvanjaRepository.findAll();
    }

    public Optional<TipZvanja> findOne(Long id) {
        return tipZvanjaRepository.findById(id);
    }

    public TipZvanja save(TipZvanja tipZvanja) {
        return tipZvanjaRepository.save(tipZvanja);
    }

    public void delete(Long id) {
        tipZvanjaRepository.deleteById(id);
    }

    public void delete(TipZvanja tipZvanja) {
        tipZvanjaRepository.delete(tipZvanja);
    }
}
