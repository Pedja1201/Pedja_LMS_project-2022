package rs.ac.singidunum.app.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.app.dto.StudijskiProgramLogDTO;
import rs.ac.singidunum.app.model.StudijskiProgramLog;
import rs.ac.singidunum.app.repository.StudijskiProgramLogRepository;

@Component
public class StudijskiProgramLogListener {
    @Autowired
    StudijskiProgramLogRepository studijskiProgramLogRepository;

    @JmsListener(destination = "logs")
    public void onLogEventStudijskiProgram(StudijskiProgramLogDTO message) {
        studijskiProgramLogRepository.save(new StudijskiProgramLog(null, message.getSignature(), message.getMessage(),
                message.getDateTime(), message.getType()));
        System.out.println(message);
    }
}
