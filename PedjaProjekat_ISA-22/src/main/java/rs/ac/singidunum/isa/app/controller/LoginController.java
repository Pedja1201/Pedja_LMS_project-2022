package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.KorisnikDTO;
import rs.ac.singidunum.isa.app.dto.StudentDTO;
import rs.ac.singidunum.isa.app.dto.TokenDTO;
import rs.ac.singidunum.isa.app.model.*;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.PermissionService;
import rs.ac.singidunum.isa.app.service.StudentService;
import rs.ac.singidunum.isa.app.utlis.TokenUtils;

import java.util.HashSet;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> login(@RequestBody KorisnikDTO korisnik) {
        try {
            // Kreiranje tokena za login, token sadrzi korisnicko ime i lozinku.
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    korisnik.getKorisnickoIme(), korisnik.getLozinka());
            // Autentifikacija korisnika na osnovu korisnickog imena i lozinke.
            Authentication authentication = authenticationManager.authenticate(token);
            // Dodavanje uspesne autentifikacije u security context.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Ucitavanje podatka o korisniku i kreiranje jwt-a.
            UserDetails userDetails = userDetailsService.loadUserByUsername(korisnik.getKorisnickoIme());
            String jwt = tokenUtils.generateToken(userDetails);
            TokenDTO jwtDTO = new TokenDTO(jwt);

            return new ResponseEntity<TokenDTO>(jwtDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TokenDTO>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> register(@RequestBody StudentDTO korisnik) {
        // Novi korisnik se registruje kreiranjem instance korisnika
        // cija je lozinka enkodovana.
        Student noviKorisnik = new Student(null, korisnik.getKorisnickoIme(),
                passwordEncoder.encode(korisnik.getLozinka()), korisnik.getJmbg(), korisnik.getIme(),
                new Adresa(korisnik.getAdresa().getId(), korisnik.getAdresa().getUlica(), korisnik.getAdresa().getBroj(),null),
                new PohadjanjePredmeta(korisnik.getPohadjanjePredmeta().getId(), korisnik.getPohadjanjePredmeta().getKonacnaOcena(),null),
                new StudentNaGodini(korisnik.getStudentNaGodini().getId(), korisnik.getStudentNaGodini().getDatumUpisa(),
                            korisnik.getStudentNaGodini().getBrojIndeksa(), null));
        noviKorisnik = studentService.save(noviKorisnik);
        // Dodavanje prava pristupa.
        noviKorisnik.setUserPermissions(new HashSet<UserPermission>());
        noviKorisnik.getUserPermissions()
                .add(new UserPermission(null, noviKorisnik, permissionService.findOne(1l).get()));
        studentService.save(noviKorisnik);

        return new ResponseEntity<StudentDTO>(
                new StudentDTO(noviKorisnik.getId(), noviKorisnik.getKorisnickoIme(), null,
                        noviKorisnik.getJmbg(), noviKorisnik.getIme()), HttpStatus.OK);
    }
}
