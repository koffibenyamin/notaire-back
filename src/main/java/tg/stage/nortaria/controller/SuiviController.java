package tg.stage.nortaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Dossier;
import tg.stage.nortaria.model.Suivi;
import tg.stage.nortaria.repository.DossierRepository;
import tg.stage.nortaria.repository.SuiviRepository;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client/dossiers")
public class SuiviController {

    @Autowired
    SuiviRepository suiviRepository;

    @Autowired
    DossierRepository dossierRepository;

    @PostMapping("/{dossierId}/suivi")
    public Suivi createSuivi(@PathVariable(value = "dossierId") Long dossierId,
                             @Valid @RequestBody Suivi suivi) {
        return dossierRepository.findById(dossierId).map(dossier -> {
            suivi.setDossier(dossier);
            return suiviRepository.save(suivi);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + dossierId + " not found"));
    }

    @PutMapping("/{dossierId}/suivi/{suiviId}")
    public Suivi updateSuivi(@PathVariable (value = "dossierId") Long dossierId,
                                 @PathVariable (value = "suiviId") Long suiviId,
                                 @Valid @RequestBody Suivi suiviRequest) {
        if(!dossierRepository.existsById(dossierId)) {
            throw new ResourceNotFoundException("dossierId " + dossierId + " not found");
        }

        return suiviRepository.findById(suiviId).map(suivi -> {
            suivi.setDateSuivi(suiviRequest.getDateSuivi());
            suivi.setLibelle(suiviRequest.getLibelle());

            return suiviRepository.save(suivi);
        }).orElseThrow(() -> new ResourceNotFoundException("SuiviId " + suiviId + "not found"));
    }

    @DeleteMapping("/{dossierId}/suivi/{suiviId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "dossierId") Long dossierId,
                                           @PathVariable (value = "suiviId") Long suiviId) {
        return suiviRepository.findByIdAndDossierId(suiviId, dossierId).map(suivi -> {
            suiviRepository.delete(suivi);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("suivi not found with id " + suiviId + " and dossierId " + dossierId));
    }


}
