package tg.stage.nortaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.User;
import tg.stage.nortaria.repository.UserRepository;
import tg.stage.nortaria.security.service.UserDetailsServiceImpl;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Espace libre";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "Espace utilisateur.";
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Espace administrateur.";
    }


    @GetMapping("/count")
    public long list() {
        return userDetailsService.count();
    }



    @GetMapping("/listUserbyrole")
    public List<User> getUser(){
        return userDetailsService.getUserByRole();
    }

    /*@DeleteMapping("/listUserbyrole/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @DeleteMapping("/listUserbyrole/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("Utilisateur avec id: "+ id + " a été supprimer", HttpStatus.OK);
    }

}
