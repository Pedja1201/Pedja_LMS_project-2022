package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.TipNastave;
import rs.ac.singidunum.app.repository.TipNastaveRepository;

import java.util.Optional;

@Service
public class TipNastaveService {
    @Autowired
    private TipNastaveRepository tipNastaveRepository;

    public TipNastaveService() {
        super();
    }

    public TipNastaveService(TipNastaveRepository tipNastaveRepository) {
        super();
        this.tipNastaveRepository = tipNastaveRepository;
    }

    public TipNastaveRepository getTipNastaveRepository() {
        return tipNastaveRepository;
    }

    public void setTipNastaveRepository(TipNastaveRepository tipNastaveRepository) {
        this.tipNastaveRepository = tipNastaveRepository;
    }

    public Iterable<TipNastave> findAll() {
        return tipNastaveRepository.findAll();
    }

    public Page<TipNastave> findAll(Pageable pageable) {
        return tipNastaveRepository.findAll(pageable);
    }

    public Optional<TipNastave> findOne(Long id) {
        return tipNastaveRepository.findById(id);
    }


    public TipNastave save(TipNastave tipNastave) {
        return tipNastaveRepository.save(tipNastave);
    }

    public void delete(Long id) {
        tipNastaveRepository.deleteById(id);
    }

    public void delete(TipNastave tipNastave) {
        tipNastaveRepository.delete(tipNastave);
    }
}
