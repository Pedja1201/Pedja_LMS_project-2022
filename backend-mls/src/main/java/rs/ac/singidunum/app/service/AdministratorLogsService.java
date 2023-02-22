package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.AdministratorLog;
import rs.ac.singidunum.app.repository.AdministratorLogsRepository;

import java.util.List;

@Service
public class AdministratorLogsService {

    @Autowired
    private AdministratorLogsRepository administratorLogsRepository;

    public List<AdministratorLog> getAll(){
        return this.administratorLogsRepository.findAll();
    }

    public AdministratorLog save(AdministratorLog administratorLog) {
        return this.administratorLogsRepository.insert(administratorLog);
    }
}

