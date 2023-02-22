package rs.ac.singidunum.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import rs.ac.singidunum.app.service.EmailSenderService;

import javax.mail.MessagingException;

@SpringBootApplication
public class SpringEmailClientApplication {
    @Autowired
    private EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailClientApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

        service.sendEmailWithAttachment("spring.email.to@gmail.com",
                "This is Email Body with Attachment...",
                "This email has attachment",
                "C:\\Users\\pc\\Pictures\\Saved Pictures\\Pedja.jpeg");

    }
}
