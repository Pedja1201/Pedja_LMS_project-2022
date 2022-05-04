package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.repository.ZvanjeRepository;

import java.util.Optional;

@Service
public class ZvanjeService {
    @Autowired
    private ZvanjeRepository zvanjeRepository;

    public ZvanjeService() {
        super();
    }

    public Iterable<Zvanje> findAll() {
        return zvanjeRepository.findAll();
    }

    public Optional<Zvanje> findOne(Long id) {
        return zvanjeRepository.findById(id);
    }


    public Zvanje save(Zvanje zvanje) {
        return zvanjeRepository.save(zvanje);
    }

    public void delete(Long id) {
        zvanjeRepository.deleteById(id);
    }

    public void delete(Zvanje zvanje) {
        zvanjeRepository.delete(zvanje);
    }

}
