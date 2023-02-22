package rs.ac.singidunum.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.app.model.NastavnikLog;
import rs.ac.singidunum.app.service.NastavnikLogsService;

import java.time.LocalDateTime;

@Aspect
@Component
public class NastavnikLogger {

    @Autowired
    private NastavnikLogsService nastavnikLogsService;

    @After("@annotation(LoggedNastavnik)")
    public void logujAkcijeNastavnika(JoinPoint jp){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        nastavnikLogsService.save(new NastavnikLog(null, username, jp.getSignature().toLongString(), "Nastavicke akcije", LocalDateTime.now()));
    }
}

