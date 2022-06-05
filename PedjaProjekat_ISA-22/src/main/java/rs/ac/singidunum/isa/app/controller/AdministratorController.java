package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.isa.app.dto.AdministratorDTO;
import rs.ac.singidunum.isa.app.model.Administrator;
import rs.ac.singidunum.isa.app.service.AdministratorService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/administratori")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.GET)
//        @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<AdministratorDTO>> getAllAdministrator() {
        ArrayList<AdministratorDTO> administratorDTOS = new ArrayList<AdministratorDTO>();

        for (Administrator administrator : administratorService.findAll()) {
            administratorDTOS.add(new AdministratorDTO(administrator.getId(),administrator.getKorisnickoIme(),administrator.getLozinka(), administrator.getIme(), administrator.getJmbg()));
        }
        return new ResponseEntity<Iterable<AdministratorDTO>>(administratorDTOS, HttpStatus.OK);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.GET)
//        @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdministratorDTO> getAdministrator(@PathVariable("administratorId") Long administratorId) {
        Optional<Administrator> administrator = administratorService.findOne(administratorId);
        if (administrator.isPresent()) {
            AdministratorDTO administratorDTO = new AdministratorDTO(administrator.get().getId(),administrator.get().getKorisnickoIme(),administrator.get().getLozinka(), administrator.get().getIme(), administrator.get().getJmbg());
            return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//        @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdministratorDTO> create(@RequestBody Administrator administrator) {
        try {
            administratorService.save(administrator);
            AdministratorDTO administratorDTO = new AdministratorDTO(administrator.getId(),administrator.getKorisnickoIme(),administrator.getLozinka(), administrator.getIme(), administrator.getJmbg());
            return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.PUT)
//        @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AdministratorDTO> update(@PathVariable("administratorId") Long administratorId,
                                                   @RequestBody Administrator izmenjenAdministrator) {
        Administrator administrator = administratorService.findOne(administratorId).orElse(null);
        if (administrator != null) {
            izmenjenAdministrator.setId(administratorId);
            administratorService.save(izmenjenAdministrator); //DONE: Sa ovim radi bez BUG-a (Beskonacna rekurzija!)
            AdministratorDTO administratorDTO = new AdministratorDTO(izmenjenAdministrator.getId(),izmenjenAdministrator.getKorisnickoIme(),izmenjenAdministrator.getLozinka(), izmenjenAdministrator.getIme(), izmenjenAdministrator.getJmbg());
            return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.DELETE)
//        @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Administrator> delete(@PathVariable("administratorId") Long administratorId) {
        if (administratorService.findOne(administratorId).isPresent()) {
            administratorService.delete(administratorId);
            return new ResponseEntity<Administrator>(HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }

}
