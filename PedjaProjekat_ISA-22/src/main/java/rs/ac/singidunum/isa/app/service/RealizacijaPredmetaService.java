package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;
import rs.ac.singidunum.isa.app.repository.RealizacijaPredmetaRepository;

import java.util.Optional;

@Service
public class RealizacijaPredmetaService {
    @Autowired
    private RealizacijaPredmetaRepository realizacijaPredmetaRepository;

    public RealizacijaPredmetaService() {
        super();
    }

    public RealizacijaPredmetaService(RealizacijaPredmetaRepository realizacijaPredmetaRepository) {
        super();
        this.realizacijaPredmetaRepository = realizacijaPredmetaRepository;
    }

    public RealizacijaPredmetaRepository getRealizacijaPredmetaRepository() {
        return realizacijaPredmetaRepository;
    }

    public void setRealizacijaPredmetaRepository(RealizacijaPredmetaRepository realizacijaPredmetaRepository) {
        this.realizacijaPredmetaRepository = realizacijaPredmetaRepository;
    }

    public Iterable<RealizacijaPredmeta> findAll() {
        return realizacijaPredmetaRepository.findAll();
    }

    public Page<RealizacijaPredmeta> findAll(Pageable pageable) {
        return realizacijaPredmetaRepository.findAll(pageable);
    }

    public Optional<RealizacijaPredmeta> findOne(Long id) {
        return realizacijaPredmetaRepository.findById(id);
    }


    public RealizacijaPredmeta save(RealizacijaPredmeta realizacijaPredmeta) {
        return realizacijaPredmetaRepository.save(realizacijaPredmeta);
    }

    public void delete(Long id) {
        realizacijaPredmetaRepository.deleteById(id);
    }

    public void delete(RealizacijaPredmeta realizacijaPredmeta) {
        realizacijaPredmetaRepository.delete(realizacijaPredmeta);
    }

    //Metoda za pronalazenje Predmeta
    public Iterable<RealizacijaPredmeta> findPredmetURealizaciji(String naziv) {
        return realizacijaPredmetaRepository.findPredmetURealizaciji(naziv);
    }
}
