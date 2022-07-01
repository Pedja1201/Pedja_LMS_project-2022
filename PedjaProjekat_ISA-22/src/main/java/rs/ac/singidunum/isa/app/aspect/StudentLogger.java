package rs.ac.singidunum.isa.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.model.StudentLog;
import rs.ac.singidunum.isa.app.service.StudentLogsService;

import java.time.LocalDateTime;

@Aspect
@Component
public class StudentLogger {

    @Autowired
    private StudentLogsService studentLogsService;

    @After("@annotation(LoggedStudent)")
    public void logujAkcijeStudenta(JoinPoint jp){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        studentLogsService.save(new StudentLog(null,username, jp.getSignature().toLongString(), "Studentska akcija", LocalDateTime.now()));
    }
}

