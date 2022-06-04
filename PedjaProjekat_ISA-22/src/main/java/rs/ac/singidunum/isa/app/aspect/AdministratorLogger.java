package rs.ac.singidunum.isa.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.model.AdministratorLog;
import rs.ac.singidunum.isa.app.service.AdministratorLogsService;

import java.time.LocalDateTime;

@Aspect
@Component
public class AdministratorLogger {
//potrebno je jos ubaciti anotacije na metode koje treba da se loguju
    //potrebno je i pronaci administratora koji poziva metodu

    @Autowired
    private AdministratorLogsService administratorLogService;

    @After("@annotation(LoggedAdministrator)")
    public void logujAkcijeAdministratora(JoinPoint jp){
        administratorLogService.save(new AdministratorLog(null, null, jp.getSignature().toLongString(), "Administratorska akcija", LocalDateTime.now()));
    }

}
