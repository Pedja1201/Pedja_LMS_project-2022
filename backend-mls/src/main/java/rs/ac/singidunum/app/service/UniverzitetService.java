package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.Univerzitet;
import rs.ac.singidunum.app.repository.UniverzitetRepository;

import java.util.Optional;

@Service
public class UniverzitetService {
    @Autowired
    private UniverzitetRepository univerzitetRepository;

    public UniverzitetService() {
        super();
    }

    public UniverzitetService(UniverzitetRepository univerzitetRepository) {
        super();
        this.univerzitetRepository = univerzitetRepository;
    }

    public UniverzitetRepository getUniverzitetRepository() {
        return univerzitetRepository;
    }

    public void setUniverzitetRepository(UniverzitetRepository univerzitetRepository) {
        this.univerzitetRepository = univerzitetRepository;
    }

    public Iterable<Univerzitet> findAll() {
        return univerzitetRepository.findAll();
    }

    public Page<Univerzitet> findAll(Pageable pageable) {
        return univerzitetRepository.findAll(pageable);
    }

    public Optional<Univerzitet> findOne(Long id) {
        return univerzitetRepository.findById(id);
    }


    public Univerzitet save(Univerzitet univerzitet) {
        return univerzitetRepository.save(univerzitet);
    }

    public void delete(Long id) {
        univerzitetRepository.deleteById(id);
    }

    public void delete(Univerzitet univerzitet) {
        univerzitetRepository.delete(univerzitet);
    }

    //Metoda za pronalazenje svih nastavnika na Univerzitetu po imenu
    public Iterable<Univerzitet> findNastavnikUniverziteta(String ime) {
        return univerzitetRepository.findNastavnikUniverziteta(ime);
    }

}
