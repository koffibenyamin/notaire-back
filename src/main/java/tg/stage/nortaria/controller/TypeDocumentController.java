package tg.stage.nortaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.TypeDocument;
import tg.stage.nortaria.repository.TypeDocumentRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client/typedocument")
public class TypeDocumentController {
    @Autowired
    TypeDocumentRepository typeDocumentRepository;

    @PostMapping("/creer")
    public TypeDocument createtypeDocument(@Valid @RequestBody TypeDocument typeDocument) {
        return typeDocumentRepository.save(typeDocument);
    }

    @GetMapping("/all")
    public List<TypeDocument> getAllTypeDocument(){
        return typeDocumentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDocument> getTypeDocumentById(@PathVariable Long id) {
        TypeDocument typeDocument = typeDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type document not exist with id :" + id));
        return ResponseEntity.ok(typeDocument);
    }
    @PutMapping("/{id}")
    public TypeDocument updateTypeDocument(@PathVariable Long id, @Valid @RequestBody TypeDocument typeDocumentrModif) {
        return typeDocumentRepository.findById(id).map(typeDocument -> {
            typeDocument.setNomTypeDocument(typeDocumentrModif.getNomTypeDocument());
            return typeDocumentRepository.save(typeDocument);
        }).orElseThrow(() -> new ResourceNotFoundException("TypeDocument" + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeDocument(@PathVariable Long id) {
        return typeDocumentRepository.findById(id).map(typeDocument -> {
            typeDocumentRepository.delete(typeDocument);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("typeDocument" + id + " not found"));
    }
}
