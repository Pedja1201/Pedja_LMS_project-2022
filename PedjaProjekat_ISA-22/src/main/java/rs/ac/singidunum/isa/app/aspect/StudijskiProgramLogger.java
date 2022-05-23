package rs.ac.singidunum.isa.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.isa.app.model.StudijskiProgramLog;
import rs.ac.singidunum.isa.app.service.StudijskiProgramLogService;

import java.time.LocalDateTime;

@Aspect
@Component
public class StudijskiProgramLogger {
    @Autowired
    private StudijskiProgramLogService studijskiProgramLogService;

    @Before("@annotation(Logged)")  //Jednostavniji nacin sa anotacijom '@logged'
    public void logPocetakIzvrsavanjaAnotacija(JoinPoint jp){
        System.out.println("Pre izvršavanja metode. [LOGGED]. ");
        System.out.println(jp.getSignature());
        //StudisjkiProgramLog za NoSQL mongoDB bazu
        studijskiProgramLogService.save(new StudijskiProgramLog(null, jp.getSignature().toLongString(), "Pre izvršavanja metode. [LOGGED]. ", LocalDateTime.now(), "INFO"));

        //Ispis argumenata u konzoli sa vrednostima
        for(Object o : jp.getArgs()){
            System.out.println(o);
        }

    }

    @Around("execution(* rs.ac.singidunum.isa.app.controller.StudijskiProgramController.*(Long))")
    public ResponseEntity<Object> logOkoIzvrsavanja(ProceedingJoinPoint jp){
        System.out.println("Pre izvršavanja metode. [AROUND].");
        System.out.println(jp.getSignature());
        try {
            Object [] args = jp.getArgs();
            if((Long)args[0] <= 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Object result = jp.proceed(args);
            return (ResponseEntity<Object>)result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Nakon izvršavanja metode. [AROUND].");
        return null;
    }

    //Nakon izvrsavanja ispisuje metode u konzoli
    @After("execution(* rs.ac.singidunum.isa.app.controller.StudijskiProgramController.*(Long)) && args(id,..)")
    public void logKrajIzvrsavanja(Long id){
        System.out.println("Nakon izvršavanja metode.");
        System.out.println(id);


    }
}
