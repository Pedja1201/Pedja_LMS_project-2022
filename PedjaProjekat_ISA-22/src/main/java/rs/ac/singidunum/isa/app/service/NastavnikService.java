package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.repository.NastvanikRepository;

import java.util.Optional;

@Service
public class NastavnikService {
    @Autowired
    private NastvanikRepository nastvanikRepository;

    public Iterable<Nastavnik> findAll() {
        return nastvanikRepository.findAll();
    }

    public Page<Nastavnik> findAll(Pageable pageable) {
        return nastvanikRepository.findAll(pageable);
    }

    public Optional<Nastavnik> findOne(Long id) {
        return nastvanikRepository.findById(id);
    }

    public Nastavnik save(Nastavnik nastavnik) {
        return nastvanikRepository.save(nastavnik);
    }

    public void delete(Long id) {
        nastvanikRepository.deleteById(id);
    }

    public void delete(Nastavnik nastavnik) {
        nastvanikRepository.delete(nastavnik);
    }

    //Metoda za pronalazenje prethodnih zvanja nastavnika (priprema klk)
    public Iterable<Nastavnik> findZvanjeNastavnik(Long id) {
        return nastvanikRepository.findZvanjeNastavnik(id);
    }


}
