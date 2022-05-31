package rs.ac.singidunum.isa.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.model.NastavnikLog;
import rs.ac.singidunum.isa.app.service.NastavnikLogsService;

import java.time.LocalDateTime;

@Aspect
@Component
public class NastavnikLogger {

    @Autowired
    private NastavnikLogsService nastavnikLogsService;

    @After("@annotation(LoggedNastavnik)")
    public void logujAkcijeNastavnika(JoinPoint jp){
        nastavnikLogsService.save(new NastavnikLog(null, null, jp.getSignature().toLongString(), "Nastavicke akcije", LocalDateTime.now()));
    }
}

