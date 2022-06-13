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
import rs.ac.singidunum.isa.app.dto.*;
import rs.ac.singidunum.isa.app.model.*;
import rs.ac.singidunum.isa.app.service.AdministratorService;
import rs.ac.singidunum.isa.app.service.NastavnikService;
import rs.ac.singidunum.isa.app.service.PermissionService;
import rs.ac.singidunum.isa.app.service.StudentService;
import rs.ac.singidunum.isa.app.utlis.TokenUtils;

import java.util.HashSet;

@Controller
@RequestMapping("/api")
public class LoginController { //TODO:RAspodeliti uloge prilikom register: ROLE_ADMIN, ROLE_STUDENT, ROLE_NASTAVNIK, ROLE_KORISNIK

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private NastavnikService nastavnikService;
    @Autowired
    private AdministratorService administratorService;

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

    @RequestMapping(path = "/registerStudent", method = RequestMethod.POST)
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO korisnik) {
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
        noviKorisnik.getUserPermissions()                                       //Id:3 jer je ROLE_STUDENT=3 (Student)
                .add(new UserPermission(null, noviKorisnik, permissionService.findOne(3l).get()));
        studentService.save(noviKorisnik);

        return new ResponseEntity<StudentDTO>(
                new StudentDTO(noviKorisnik.getId(), noviKorisnik.getKorisnickoIme(), null,
                        noviKorisnik.getJmbg(), noviKorisnik.getIme(),
                        new AdresaDTO(noviKorisnik.getAdresa().getId(),noviKorisnik.getAdresa().getUlica(),
                                noviKorisnik.getAdresa().getBroj(),null),
                        new PohadjanjePredmetaDTO(noviKorisnik.getPohadjanjePredmeta().getId(),
                                noviKorisnik.getPohadjanjePredmeta().getKonacnaOcena(),null),
                        new StudentNaGodiniDTO(noviKorisnik.getStudentNaGodini().getId(), noviKorisnik.getStudentNaGodini().getDatumUpisa(),
                                noviKorisnik.getStudentNaGodini().getBrojIndeksa(),null)), HttpStatus.OK);
    }

    @RequestMapping(path = "/registerNastavnik", method = RequestMethod.POST)
    public ResponseEntity<NastavnikDTO> registerNastavnik(@RequestBody NastavnikDTO korisnik) {
        // Novi korisnik se registruje kreiranjem instance korisnika
        // cija je lozinka enkodovana.
        Nastavnik noviKorisnik = new Nastavnik(null, korisnik.getKorisnickoIme(),
                passwordEncoder.encode(korisnik.getLozinka()), korisnik.getIme(),korisnik.getBiografija(),korisnik.getJmbg(),
                new Adresa(korisnik.getAdresa().getId(), korisnik.getAdresa().getUlica(), korisnik.getAdresa().getBroj(),null),
                new Zvanje(korisnik.getZvanje().getId(), korisnik.getZvanje().getDatumIzbora(),korisnik.getZvanje().getDatumPrestanka(),
                        null,null));
        noviKorisnik = nastavnikService.save(noviKorisnik);
        // Dodavanje prava pristupa.
        noviKorisnik.setUserPermissions(new HashSet<UserPermission>());
        noviKorisnik.getUserPermissions()                               //Id:2 jer je ROLE_NASTAVNIK=2 (Nastavnik=Korisnik)
                .add(new UserPermission(null, noviKorisnik, permissionService.findOne(2l).get()));
        nastavnikService.save(noviKorisnik);

        return new ResponseEntity<NastavnikDTO>(
                new NastavnikDTO(noviKorisnik.getId(), noviKorisnik.getKorisnickoIme(), null,
                        noviKorisnik.getIme(), noviKorisnik.getBiografija(),
                        noviKorisnik.getJmbg(),
                        new AdresaDTO(noviKorisnik.getAdresa().getId(),noviKorisnik.getAdresa().getUlica(),
                                noviKorisnik.getAdresa().getBroj(),null),
                        new ZvanjeDTO(noviKorisnik.getZvanje().getId(), noviKorisnik.getZvanje().getDatumIzbora(),
                                noviKorisnik.getZvanje().getDatumPrestanka(),null,null)), HttpStatus.OK);
    }

    @RequestMapping(path = "/registerAdministrator", method = RequestMethod.POST)
    public ResponseEntity<AdministratorDTO> registerAdministrator(@RequestBody AdministratorDTO administratorDTO) {
        // Novi korisnik se registruje kreiranjem instance korisnika
        // cija je lozinka enkodovana.
        Administrator noviAdministrator = new Administrator(null, administratorDTO.getKorisnickoIme(),
                passwordEncoder.encode(administratorDTO.getLozinka()), administratorDTO.getIme(), administratorDTO.getJmbg());
        noviAdministrator = administratorService.save(noviAdministrator);
        // Dodavanje prava pristupa.
        noviAdministrator.setUserPermissions(new HashSet<UserPermission>());
        noviAdministrator.getUserPermissions()                                   //Id:1 jer je ROLE_ADMIN=1 (Administrator=Admin)
                .add(new UserPermission(null, noviAdministrator, permissionService.findOne(1l).get()));
        administratorService.save(noviAdministrator);

        return new ResponseEntity<AdministratorDTO>(
                new AdministratorDTO(noviAdministrator.getId(), noviAdministrator.getKorisnickoIme(), noviAdministrator.getLozinka(), noviAdministrator.getIme(), noviAdministrator.getJmbg()), HttpStatus.OK);
    }
}
