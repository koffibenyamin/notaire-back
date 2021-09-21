package tg.stage.nortaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Client;
import tg.stage.nortaria.model.Suivi;
import tg.stage.nortaria.model.SuiviFinancier;
import tg.stage.nortaria.repository.DossierRepository;
import tg.stage.nortaria.repository.SuiviFinancierRepository;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client/dossiers")
public class SuiviFinancierController {


    @Autowired
    SuiviFinancierRepository suiviFinancierRepository;

    @Autowired
    DossierRepository dossierRepository;

    @GetMapping("/{id}/count")
    public Optional<Double> getdepenseCabinet(@PathVariable Long id) {
        return suiviFinancierRepository.montantCabinet(id);
    }

    @GetMapping("/{id}/count1")
    public Optional<Double> getdepenseClient(@PathVariable Long id) {
        return suiviFinancierRepository.montantClient(id);
    }
    @GetMapping("/{id}/count2")
    public Optional<Double> getdepense(@PathVariable Long id) {
        return suiviFinancierRepository.montant(id);
    }

    @PostMapping("/{dossierId}/suiviFinancier")
    public SuiviFinancier createSuiviFinancier(@PathVariable(value = "dossierId") Long dossierId,
                             @Valid @RequestBody SuiviFinancier suiviFinancier) {
        return dossierRepository.findById(dossierId).map(dossier -> {
            suiviFinancier.setDossier(dossier);
            return suiviFinancierRepository.save(suiviFinancier);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + dossierId + " not found"));
    }

    @PutMapping("/{dossierId}/suiviFinancier/{suiviFinancierId}")
    public SuiviFinancier updateSuiviFinancier(@PathVariable (value = "dossierId") Long dossierId,
                             @PathVariable (value = "suiviFinancierId") Long suiviFinancierId,
                             @Valid @RequestBody SuiviFinancier suiviFinancierRequest) {
        if(!dossierRepository.existsById(dossierId)) {
            throw new ResourceNotFoundException("dossierId " + dossierId + " not found");
        }

        return suiviFinancierRepository.findById(suiviFinancierId).map(suiviFinancier -> {
            suiviFinancier.setDateSuivi(suiviFinancierRequest.getDateSuivi());
            suiviFinancier.setLibelle(suiviFinancierRequest.getLibelle());

            return suiviFinancierRepository.save(suiviFinancier);
        }).orElseThrow(() -> new ResourceNotFoundException("SuiviFinancierId " + suiviFinancierId + "not found"));
    }

    @DeleteMapping("/{dossierId}/suiviFinancier/{suiviFinancierId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "dossierId") Long dossierId,
                                           @PathVariable (value = "suiviFinancierId") Long suiviFinancierId) {
        return suiviFinancierRepository.findByIdAndDossierId(suiviFinancierId, dossierId).map(suiviFinancier -> {
            suiviFinancierRepository.delete(suiviFinancier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("suiviFinancier not found with id " + suiviFinancierId + " and dossierId " + dossierId));
    }



}
