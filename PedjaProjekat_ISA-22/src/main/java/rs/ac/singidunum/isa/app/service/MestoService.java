package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Mesto> findAll(Pageable pageable) {
        return mestoRepository.findAll(pageable);
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

    //Metoda za pronalazenje svih karata koje je putnik kupio (Zadatak sa klk)
    public Iterable<Mesto> findDrzava(String naziv) {
        return mestoRepository.findDrzava(naziv);
    }
}
