package rs.ac.singidunum.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.StudijskiProgramLog;
import rs.ac.singidunum.app.repository.StudijskiProgramLogRepository;

import java.util.Optional;

@Service
public class StudijskiProgramLogService {
    @Autowired
    private StudijskiProgramLogRepository studijskiProgramLogRepository;

    public Iterable<StudijskiProgramLog> findAll() {
        return studijskiProgramLogRepository.findAll();
    }

    public Optional<StudijskiProgramLog> findOne(String id) {
        return studijskiProgramLogRepository.findById(id);
    } //String je id !!!!

    public StudijskiProgramLog save(StudijskiProgramLog studijskiProgramLog) {
        return studijskiProgramLogRepository.insert(studijskiProgramLog);
    } ///Insert umesto save zbog mongoDB

    public void delete(String id) {
        studijskiProgramLogRepository.deleteById(id);
    }

    public void delete(StudijskiProgramLog studijskiProgramLog) {
        studijskiProgramLogRepository.delete(studijskiProgramLog);
    }
}
