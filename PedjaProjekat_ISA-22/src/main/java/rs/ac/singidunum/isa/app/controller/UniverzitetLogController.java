package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.UniverzitetLogDTO;
import rs.ac.singidunum.isa.app.model.UniverzitetLog;
import rs.ac.singidunum.isa.app.service.UniverzitetLogService;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/api/logs/univerzitet")
public class UniverzitetLogController {
    @Autowired
    private UniverzitetLogService univerzitetLogService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UniverzitetLogDTO>> getAll() {
        ArrayList<UniverzitetLogDTO> karteLog = new ArrayList<UniverzitetLogDTO>();
        for (UniverzitetLog univerzitetLog : univerzitetLogService.findAll()) {
            karteLog.add(new UniverzitetLogDTO(univerzitetLog.getId(),univerzitetLog.getSignature(),univerzitetLog.getMessage(),
                    univerzitetLog.getDateTime(), univerzitetLog.getType()));
        }

        return new ResponseEntity<Iterable<UniverzitetLogDTO>>(karteLog, HttpStatus.OK);
    }
}
