package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.IshodNastave;
import rs.ac.singidunum.app.repository.IshodNastaveRepository;

import java.util.Optional;

@Service
public class IshodNastaveService {
    @Autowired
    private IshodNastaveRepository ishodNastaveRepository;

    public Iterable<IshodNastave> findAll() {
        return ishodNastaveRepository.findAll();
    }

    public Page<IshodNastave> findAll(Pageable pageable) {
        return ishodNastaveRepository.findAll(pageable);
    }

    public Optional<IshodNastave> findOne(Long id) {
        return ishodNastaveRepository.findById(id);
    }

    public IshodNastave save(IshodNastave ishodNastave) {
        return ishodNastaveRepository.save(ishodNastave);
    }

    public void delete(Long id) {
        ishodNastaveRepository.deleteById(id);
    }

    public void delete(IshodNastave ishodNastave) {
        ishodNastaveRepository.delete(ishodNastave);
    }

}
