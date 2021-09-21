package tg.stage.nortaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.stage.nortaria.model.Client;
import tg.stage.nortaria.model.Document;
import tg.stage.nortaria.model.Dossier;
import tg.stage.nortaria.repository.DocumentRepository;
import tg.stage.nortaria.repository.DossierRepository;
import tg.stage.nortaria.repository.TypeDocumentRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client/dossiers")
public class DocumentController {

    @Autowired
    DossierRepository dossierRepository;

    @Autowired
    TypeDocumentRepository typeDocumentRepository;

    @Autowired
    DocumentRepository documentRepository;

    @GetMapping("/{dossierId}/dcument")
    public List<Document> getAlldossiersByClientId(@PathVariable(value = "dossierId") Long dossierId) {
        return documentRepository.findByDossierId(dossierId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("document not exist with id :" + id));
        return ResponseEntity.ok(document);
    }

    @PostMapping("/{dossierId}/{typeDocumentId}/document")
    public Optional<Document> createDocument(@PathVariable (value = "dossierId") Long dossierId,
                                           @PathVariable (value = "typeDocumentId") Long typeDocumentId,
                                           @Valid @RequestBody Document document) {

        return dossierRepository.findById(dossierId).map(dossier -> {
            document.setDossier(dossier);
            return typeDocumentRepository.findById(typeDocumentId).map( typedocument -> {
                document.setTypedocument(typedocument);
                return documentRepository.save(document);
            });
        }).orElseThrow(() -> new ResourceNotFoundException("dossier " + dossierId + " not found"));
    }

    @PutMapping("/{dossierId}/documents/{documentId}")
    public Document updateDocumnt(@PathVariable (value = "dossierId") Long dossierId,
                                 @PathVariable (value = "documentId") Long documentId,
                                 @Valid @RequestBody Document documentRequest) {
        if(!dossierRepository.existsById(dossierId)) {
            throw new ResourceNotFoundException("dossierId " + dossierId + " not found");
        }

        return documentRepository.findById(documentId).map(document -> {
            document.setDescription(documentRequest.getDescription());
            document.setDatecreation(documentRequest.getDatecreation());
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + documentId + "not found"));
    }

    @DeleteMapping("/{dossierId}/documents/{documentId}")
    public ResponseEntity<?> deleteDocument(@PathVariable (value = "dossierId") Long dossierId,
                                           @PathVariable (value = "documentId") Long documentId) {
        return documentRepository.findByIdAndDossierId(documentId, dossierId).map(document -> {
            documentRepository.delete(document);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + documentId + " and dossierId " + dossierId));
    }


}
