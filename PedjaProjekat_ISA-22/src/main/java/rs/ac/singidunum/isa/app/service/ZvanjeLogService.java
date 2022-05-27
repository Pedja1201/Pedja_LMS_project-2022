package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.ZvanjeLog;
import rs.ac.singidunum.isa.app.repository.ZvanjeLogRepository;

import java.util.Optional;

@Service
public class ZvanjeLogService {
    @Autowired
    private ZvanjeLogRepository zvanjeLogRepository;

    public Iterable<ZvanjeLog> findAll() {
        return zvanjeLogRepository.findAll();
    }

    public Optional<ZvanjeLog> findOne(String id) {
        return zvanjeLogRepository.findById(id);
    } //String je id !!!!

    public ZvanjeLog save(ZvanjeLog zvanjeLog) {
        return zvanjeLogRepository.insert(zvanjeLog);
    } ///Insert umesto save zbog mongoDB

    public void delete(String id) {
        zvanjeLogRepository.deleteById(id);
    }

    public void delete(ZvanjeLog zvanjeLog) {
        zvanjeLogRepository.delete(zvanjeLog);
    }
}
