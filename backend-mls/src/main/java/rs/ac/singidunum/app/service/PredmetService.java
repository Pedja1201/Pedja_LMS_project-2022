package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.Nastavnik;
import rs.ac.singidunum.app.model.Predmet;
import rs.ac.singidunum.app.repository.PredmetRepository;

import java.util.Optional;

@Service
public class PredmetService {
    @Autowired
    private PredmetRepository predmetRepository;

    public PredmetService() {
        super();
    }

    public PredmetService(PredmetRepository predmetRepository) {
        super();
        this.predmetRepository = predmetRepository;
    }

    public PredmetRepository getPredmetRepository() {
        return predmetRepository;
    }

    public void setPredmetRepository(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }

    public Iterable<Predmet> findAll() {
        return predmetRepository.findAll();
    }

    public Page<Predmet> findAll(Pageable pageable) {
        return predmetRepository.findAll(pageable);
    }

    public Optional<Predmet> findOne(Long id) {
        return predmetRepository.findById(id);
    }


    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public void delete(Long id) {
        predmetRepository.deleteById(id);
    }

    public void delete(Predmet predmet) {
        predmetRepository.delete(predmet);
    }

    //TODO: nepotrebna metoda
    public Iterable<Predmet> findByNastavnik(Optional<Nastavnik> nastavnik){
        return predmetRepository.findbyNastavnik(nastavnik);
    }

    //metoda sa drugim imenom jer joj je falilo @param u repositoriju
    public Iterable<Predmet> findPredmetForNastavnik(Optional<Nastavnik> nastavnik){
        return this.predmetRepository.findPredmeteForNastavnik(nastavnik);
    }

}
