package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.NastavnikLog;
import rs.ac.singidunum.app.repository.NastavnikLogsRepository;

import java.util.List;

@Service
public class NastavnikLogsService {
    @Autowired
    private NastavnikLogsRepository nastavnikLogsRepository;

    public List<NastavnikLog> getAll() { return this.nastavnikLogsRepository.findAll(); }

    public NastavnikLog save(NastavnikLog nastavnikLog){
        return this.nastavnikLogsRepository.insert(nastavnikLog);
    }

}
