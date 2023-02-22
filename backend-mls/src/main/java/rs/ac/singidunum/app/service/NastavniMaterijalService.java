package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.NastavniMaterijal;
import rs.ac.singidunum.app.repository.NastavniMaterijalRepository;

import java.util.Optional;

@Service
public class NastavniMaterijalService {
    @Autowired
    private NastavniMaterijalRepository nastavniMaterijalRepository;

    public Iterable<NastavniMaterijal> findAll() {
        return nastavniMaterijalRepository.findAll();
    }

    public Page<NastavniMaterijal> findAll(Pageable pageable) {
        return nastavniMaterijalRepository.findAll(pageable);
    }

    public Optional<NastavniMaterijal> findOne(Long id) {
        return nastavniMaterijalRepository.findById(id);
    }

    public NastavniMaterijal save(NastavniMaterijal nastavniMaterijal) {
        return nastavniMaterijalRepository.save(nastavniMaterijal);
    }

    public void delete(Long id) {
        nastavniMaterijalRepository.deleteById(id);
    }

    public void delete(NastavniMaterijal nastavniMaterijal) {
        nastavniMaterijalRepository.delete(nastavniMaterijal);
    }

}
