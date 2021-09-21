package tg.stage.nortaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Client;
import tg.stage.nortaria.model.Dossier;
import tg.stage.nortaria.repository.ClientRepository;
import tg.stage.nortaria.repository.DossierRepository;
import tg.stage.nortaria.repository.TypeDossierRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
public class DossierController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DossierRepository dossierRepository;

    @Autowired
    TypeDossierRepository typeDossierRepository;

    @GetMapping("/dossiers")
    public List<Dossier> getAllDossier() {
        return dossierRepository.findAll();
    }

    @GetMapping("/{clientId}/dossiers")
    public List<Dossier> getAlldossiersByClientId(@PathVariable(value = "clientId") Long clientId) {
        return dossierRepository.findByClientId(clientId);
    }

    @GetMapping("/{id}/doss")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Dossier dossier = dossierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier not exist with id :" + id));
        return ResponseEntity.ok(dossier);
    }



     @PostMapping("/{clientId}/{typeDossierId}/dossiers")
    public Optional<Dossier> createDossier(@PathVariable (value = "clientId") Long clientId,
                                           @PathVariable (value = "typeDossierId") Long typeDossierId,
                                           @Valid @RequestBody Dossier dossier) {

        return clientRepository.findById(clientId).map(client -> {
            dossier.setClient(client);
            return typeDossierRepository.findById(typeDossierId).map( typeDossier -> {
                dossier.setTypeDossier(typeDossier);
                return dossierRepository.save(dossier);
            });
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientId + " not found"));
    }

    @PutMapping("/{clientId}/dossiers/{dossierId}")
    public Dossier updateDossier(@PathVariable (value = "clientId") Long clientId,
                                 @PathVariable (value = "dossierId") Long dossierId,
                                 @Valid @RequestBody Dossier dossierRequest) {
        if(!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("clientId " + clientId + " not found");
        }

        return dossierRepository.findById(dossierId).map(dossier -> {
            dossier.setMatricule(dossierRequest.getMatricule());
            dossier.setDescription(dossierRequest.getDescription());
            dossier.setTypeDossier(dossierRequest.getTypeDossier());
            dossier.setDatecreation(dossierRequest.getDatecreation());
            return dossierRepository.save(dossier);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + dossierId + "not found"));
    }

    @DeleteMapping("/{clientId}/dossiers/{dossierId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "clientId") Long clientId,
                                           @PathVariable (value = "dossierId") Long dossierId) {
        return dossierRepository.findByIdAndClientId(dossierId, clientId).map(dossier -> {
            dossierRepository.delete(dossier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + dossierId + " and clientId " + clientId));
    }


}
