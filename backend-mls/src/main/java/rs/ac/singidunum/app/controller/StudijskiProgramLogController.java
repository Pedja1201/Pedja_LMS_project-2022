package rs.ac.singidunum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.app.dto.StudijskiProgramLogDTO;
import rs.ac.singidunum.app.model.StudijskiProgramLog;
import rs.ac.singidunum.app.service.StudijskiProgramLogService;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/api/projectLms/studijskiProgram")
public class StudijskiProgramLogController {
    @Autowired
    private StudijskiProgramLogService studijskiProgramLogService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StudijskiProgramLogDTO>> getAll() {
        ArrayList<StudijskiProgramLogDTO> studijskiProgramiLog = new ArrayList<StudijskiProgramLogDTO>();
        for (StudijskiProgramLog studijskiProgramLog : studijskiProgramLogService.findAll()) {
            studijskiProgramiLog.add(new StudijskiProgramLogDTO(studijskiProgramLog.getId(),studijskiProgramLog.getSignature(),
                    studijskiProgramLog.getMessage(),studijskiProgramLog.getDateTime(), studijskiProgramLog.getType()));
        }

        return new ResponseEntity<Iterable<StudijskiProgramLogDTO>>(studijskiProgramiLog, HttpStatus.OK);
    }
}
