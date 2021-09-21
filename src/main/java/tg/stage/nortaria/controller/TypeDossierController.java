package tg.stage.nortaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Client;
import tg.stage.nortaria.model.Dossier;
import tg.stage.nortaria.model.TypeDossier;
import tg.stage.nortaria.repository.TypeDocumentRepository;
import tg.stage.nortaria.repository.TypeDossierRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client/typedossier")
public class TypeDossierController {

    @Autowired
    TypeDossierRepository typeDossierRepository;

    @PostMapping("/creer")
    public TypeDossier createtypedossier(@Valid @RequestBody TypeDossier typeDossier) {
        return typeDossierRepository.save(typeDossier);
    }

    @GetMapping("/all")
    public List<TypeDossier> getAllTypeDossier(){
        return typeDossierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDossier> getTypeDossierById(@PathVariable Long id) {
        TypeDossier typeDossier = typeDossierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type dossier not exist with id :" + id));
        return ResponseEntity.ok(typeDossier);
    }

    @PutMapping("/{id}")
    public TypeDossier updateTypeDossier(@PathVariable Long id, @Valid @RequestBody TypeDossier typeDossierModif) {
        return typeDossierRepository.findById(id).map(typeDossier -> {
            typeDossier.setNomTypeDossier(typeDossierModif.getNomTypeDossier());
            return typeDossierRepository.save(typeDossier);
        }).orElseThrow(() -> new ResourceNotFoundException("TypeDossier" + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeDossier(@PathVariable Long id) {
        return typeDossierRepository.findById(id).map(typeDossier -> {
            typeDossierRepository.delete(typeDossier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("typeDossier" + id + " not found"));
    }

}
