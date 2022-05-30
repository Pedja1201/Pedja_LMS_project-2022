package rs.ac.singidunum.isa.app.listener;

import org.springframework.stereotype.Component;

@Component
public class ZvanjeLogListener{//TODO:Ne mogu da rade obe u isto vreme, radi svaki drugi poziv na dogadjaj!
//    @Autowired
//    ZvanjeLogRepository zvanjeLogRepository;
//
//    @JmsListener(destination = "logs")
//    public void onLogEventZvanje(ZvanjeLogDTO message) {
//        zvanjeLogRepository.save(new ZvanjeLog(null, message.getSignature(), message.getMessage(),
//                message.getDateTime(), message.getType()));
//        System.out.println(message);
//    }
}
