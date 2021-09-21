package tg.stage.nortaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type_document", nullable = false)
    private String typeDocument;

    @NotNull
    @Column(name = "datecreation", nullable = false)
    private String datecreation;

    @Column(name = "description")
    private String description;



    @ManyToOne
    @JsonIgnoreProperties(value = { "documents", "client", "typeDossier" }, allowSetters = true)
    private Dossier dossier;

    @ManyToOne
    @JsonIgnoreProperties(value = { "documents" }, allowSetters = true)
    private TypeDocument typedocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public TypeDocument getTypedocument() {
        return typedocument;
    }

    public void setTypedocument(TypeDocument typedocument) {
        this.typedocument = typedocument;
    }
}
