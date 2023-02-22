package rs.ac.singidunum.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.app.model.ZvanjeLog;
import rs.ac.singidunum.app.service.ZvanjeLogService;

import javax.jms.Queue;
import java.time.LocalDateTime;

@Aspect
@Component
public class ZvanjeLogger {
    @Autowired
    private Queue logQueue;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ZvanjeLogService zvanjeLogService;

    @Before("@annotation(LoggedZvanje)")  //Jednostavniji nacin sa anotacijom '@logged'
    public void logPocetakIzvrsavanjaAnotacija(JoinPoint jp){
        System.out.println("Pre izvršavanja metode. [LOGGED]. ");
        System.out.println(jp.getSignature());
        //ZvanjeLog za NoSQL mongoDB bazu
        zvanjeLogService.save(new ZvanjeLog(null, jp.getSignature().toLongString(),
                "Pre izvršavanja metode. [LOGGED]. ", LocalDateTime.now(), "INFO"));
        //Saljemo tu poruku u ActiveMQ TODO:Pokrenuti Artemis ukoliko koristimo izvrsavanje metode
//        jmsTemplate.convertAndSend(logQueue, new ZvanjeLogDTO(null,jp.getSignature().toLongString(), "Pre izvršavanja metode. [LOGGED]. ", LocalDateTime.now(), "INFO"));

        //Ispis argumenata u konzoli sa vrednostima
        for(Object o : jp.getArgs()){
            System.out.println(o);
        }

    }

    @Around("execution(* rs.ac.singidunum.app.controller.ZvanjeController.*(Long))")
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
    @After("execution(* rs.ac.singidunum.app.controller.ZvanjeController.*(Long)) && args(id,..)")
    public void logKrajIzvrsavanja(Long id){
        System.out.println("Nakon izvršavanja metode.");
        System.out.println(id);


    }
}