package rs.ac.singidunum.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms   //Ukljucivanje anotacije za osluskivanje poruka(ActiveMQ)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }
}
