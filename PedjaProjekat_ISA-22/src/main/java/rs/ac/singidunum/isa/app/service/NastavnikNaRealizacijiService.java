package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;
import rs.ac.singidunum.isa.app.repository.NastavnikNaRealizacijiRepository;

import java.util.Optional;

@Service
public class NastavnikNaRealizacijiService {
    @Autowired
    private NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository;

    public NastavnikNaRealizacijiService() {
        super();
    }

    public NastavnikNaRealizacijiService(NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository) {
        super();
        this.nastavnikNaRealizacijiRepository = nastavnikNaRealizacijiRepository;
    }

    public NastavnikNaRealizacijiRepository getNastavnikNaRealizacijiRepository() {
        return nastavnikNaRealizacijiRepository;
    }

    public void setNastavnikNaRealizacijiRepository(NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository) {
        this.nastavnikNaRealizacijiRepository = nastavnikNaRealizacijiRepository;
    }

    public Iterable<NastavnikNaRealizaciji> findAll() {
        return nastavnikNaRealizacijiRepository.findAll();
    }

    public Page<NastavnikNaRealizaciji> findAll(Pageable pageable) {
        return nastavnikNaRealizacijiRepository.findAll(pageable);
    }

    public Optional<NastavnikNaRealizaciji> findOne(Long id) {
        return nastavnikNaRealizacijiRepository.findById(id);
    }


    public NastavnikNaRealizaciji save(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        return nastavnikNaRealizacijiRepository.save(nastavnikNaRealizaciji);
    }

    public void delete(Long id) {
        nastavnikNaRealizacijiRepository.deleteById(id);
    }

    public void delete(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        nastavnikNaRealizacijiRepository.delete(nastavnikNaRealizaciji);
    }
}
