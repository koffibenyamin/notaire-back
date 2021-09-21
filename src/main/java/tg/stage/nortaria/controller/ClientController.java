package tg.stage.nortaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Client;
import tg.stage.nortaria.repository.ClientRepository;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/all")
    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getEmployeeById(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(client);
    }

    @PostMapping("/creer")
    public Client createCliant(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client updateclient(@PathVariable Long id, @Valid @RequestBody Client clientmodif) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(clientmodif.getNom());
            client.setPrenom(clientmodif.getPrenom());
            client.setSexeClient(clientmodif.getSexeClient());
            client.setDateNaissance(clientmodif.getDateNaissance());
            client.setNumTel(clientmodif.getNumTel());
            client.setAdresseMail(clientmodif.getAdresseMail());
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
    }

    @GetMapping("/search/{mot}")
    public List<Client> findClient(@PathVariable("mot") String mot){
            return clientRepository.findByNomContainingOrPrenomContaining(mot, mot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
    }


}
