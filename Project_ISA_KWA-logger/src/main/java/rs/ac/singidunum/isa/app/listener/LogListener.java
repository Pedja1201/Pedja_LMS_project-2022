package rs.ac.singidunum.isa.app.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.dto.LogDTO;
import rs.ac.singidunum.isa.app.model.Log;
import rs.ac.singidunum.isa.app.repository.LogRepository;

@Component
public class LogListener {
    @Autowired
    LogRepository logRepository;

    @JmsListener(destination = "logs")
    public void onLogEvent(LogDTO message) {
        logRepository.save(new Log(null, message.getType(), message.getContent(), message.getDatetime()));
        System.out.println(message);
    }
}
