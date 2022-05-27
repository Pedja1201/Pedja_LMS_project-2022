package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.UniverzitetLog;
import rs.ac.singidunum.isa.app.repository.UniverzitetLogRepository;

import java.util.Optional;

@Service
public class UniverzitetLogService {
    @Autowired
    private UniverzitetLogRepository univerzitetLogRepository;

    public Iterable<UniverzitetLog> findAll() {
        return univerzitetLogRepository.findAll();
    }

    public Optional<UniverzitetLog> findOne(String id) {
        return univerzitetLogRepository.findById(id);
    } //String je id !!!!

    public UniverzitetLog save(UniverzitetLog univerzitetLog) {
        return univerzitetLogRepository.insert(univerzitetLog);
    } ///Insert umesto save zbog mongoDB

    public void delete(String id) {
        univerzitetLogRepository.deleteById(id);
    }

    public void delete(UniverzitetLog univerzitetLog) {
        univerzitetLogRepository.delete(univerzitetLog);
    }
}
