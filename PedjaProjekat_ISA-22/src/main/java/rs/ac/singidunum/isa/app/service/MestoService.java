package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.repository.MestoRepository;

import java.util.Optional;

@Service
public class MestoService {
    @Autowired
    private MestoRepository mestoRepository;

    public MestoService() {
        super();
    }

    public MestoService(MestoRepository mestoRepository) {
        super();
        this.mestoRepository = mestoRepository;
    }

    public MestoRepository getMestoRepository() {
        return mestoRepository;
    }

    public void setMestoRepository(MestoRepository mestoRepository) {
        this.mestoRepository = mestoRepository;
    }

    public Iterable<Mesto> findAll() {
        return mestoRepository.findAll();
    }

    public Optional<Mesto> findOne(Long id) {
        return mestoRepository.findById(id);
    }


    public Mesto save(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    public void delete(Long id) {
        mestoRepository.deleteById(id);
    }

    public void delete(Mesto mesto) {
        mestoRepository.delete(mesto);
    }
}
