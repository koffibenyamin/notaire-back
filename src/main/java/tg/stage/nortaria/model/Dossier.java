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
 * A Dossier.
 */
@Entity
@Table(name = "dossier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dossier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 5)
    @Column(name = "matricule", length = 5, nullable = false, unique = true)
    private String matricule;

    @NotNull
    @Column(name = "datecreation", nullable = false)
    private String datecreation;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "suivis", "suiviFinanciers", "dossier" }, allowSetters = true)
    private Set<Document> documents = new HashSet<>();

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "dossier" }, allowSetters = true)
    private Set<Suivi> suivis = new HashSet<>();

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "dossier" }, allowSetters = true)
    private Set<SuiviFinancier> suiviFinanciers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "dossiers" }, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "dossiers" }, allowSetters = true)
    private TypeDossier typeDossier;

    public Dossier() {
    }

    public Dossier(Long id, String matricule, String datecreation, String description, Set<Document> documents, Set<Suivi> suivis, Set<SuiviFinancier> suiviFinanciers, Client client, TypeDossier typeDossier) {
        this.id = id;
        this.matricule = matricule;
        this.datecreation = datecreation;
        this.description = description;
        this.documents = documents;
        this.suivis = suivis;
        this.suiviFinanciers = suiviFinanciers;
        this.client = client;
        this.typeDossier = typeDossier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Suivi> getSuivis() {
        return suivis;
    }

    public void setSuivis(Set<Suivi> suivis) {
        this.suivis = suivis;
    }

    public Set<SuiviFinancier> getSuiviFinanciers() {
        return suiviFinanciers;
    }

    public void setSuiviFinanciers(Set<SuiviFinancier> suiviFinanciers) {
        this.suiviFinanciers = suiviFinanciers;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TypeDossier getTypeDossier() {
        return typeDossier;
    }

    public void setTypeDossier(TypeDossier typeDossier) {
        this.typeDossier = typeDossier;
    }
}