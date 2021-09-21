package tg.stage.nortaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TypeDocument.
 */
@Entity
@Table(name = "type_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_type_dossier", nullable = false)
    private String nomTypeDocument;

    @OneToMany(mappedBy = "typedocument")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "suivis", "suiviFinanciers", "dossier", "typedocument" }, allowSetters = true)
    private Set<Document> documents = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeDocument() {
        return nomTypeDocument;
    }

    public void setNomTypeDocument(String nomTypeDocument) {
        this.nomTypeDocument = nomTypeDocument;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}

