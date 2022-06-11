package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Polaganje;
import rs.ac.singidunum.isa.app.repository.PolaganjeRepository;

import java.util.Optional;

@Service
public class PolaganjeService {
    @Autowired
    private PolaganjeRepository polaganjeRepository;

    public Iterable<Polaganje> findAll() {
        return polaganjeRepository.findAll();
    }

    public Page<Polaganje> findAll(Pageable pageable) {
        return polaganjeRepository.findAll(pageable);
    }

    public Optional<Polaganje> findOne(Long id) {
        return polaganjeRepository.findById(id);
    }

    public Polaganje save(Polaganje polaganje) {
        return polaganjeRepository.save(polaganje);
    }

    public void delete(Long id) {
        polaganjeRepository.deleteById(id);
    }

    public void delete(Polaganje polaganje) {
        polaganjeRepository.delete(polaganje);
    }

    //Metoda za pronalazenje  studenta na godini koji polaze
    public Iterable<Polaganje> findStudentNaGodini(String brojIndeksa) {
        return polaganjeRepository.findStudentNaGodini(brojIndeksa);
    }

}
