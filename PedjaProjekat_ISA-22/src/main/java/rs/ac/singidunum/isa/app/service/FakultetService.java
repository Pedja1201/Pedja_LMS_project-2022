package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Fakultet;
import rs.ac.singidunum.isa.app.repository.FakultetRepository;

import java.util.Optional;

@Service
public class FakultetService {
    @Autowired
    private FakultetRepository fakultetRepository;

    public FakultetService() {super();
    }

    public FakultetService(FakultetRepository fakultetRepository) {
        this.fakultetRepository = fakultetRepository;
    }

    public FakultetRepository getFakultetRepository() {
        return fakultetRepository;
    }

    public void setFakultetRepository(FakultetRepository fakultetRepository) {
        this.fakultetRepository = fakultetRepository;
    }

    public Iterable<Fakultet> findAll() {
        return fakultetRepository.findAll();
    }

    public Optional<Fakultet> findOne(Long id) {
        return fakultetRepository.findById(id);
    }

    public Fakultet save(Fakultet fakultet) {
        return fakultetRepository.save(fakultet);
    }

    public void delete(Long id) {
        fakultetRepository.deleteById(id);
    }

    public void delete(Fakultet fakultet) {
        fakultetRepository.delete(fakultet);
    }

    //Metoda za pronalazenje svih univerzitetu
    public Iterable<Fakultet> findUniverzitet(Long id) {
        return fakultetRepository.findUniverzitet(id);
    }
}
