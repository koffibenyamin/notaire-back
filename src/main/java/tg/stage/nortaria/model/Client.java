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
import tg.stage.nortaria.model.Sexe;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe_client")
    private Sexe sexeClient;

    @Column(name = "date_naissance")
    private String dateNaissance;

    @NotNull
    @Column(name = "num_tel", nullable = false)
    private String numTel;

    @NotNull
    @Column(name = "adresse_mail", nullable = false, unique = true)
    private String adresseMail;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "documents", "client" }, allowSetters = true)
    private Set<Dossier> dossiers = new HashSet<>();

    public Client() {
    }

    public Client(Long id, String nom, String prenom, Sexe sexeClient, String dateNaissance, String numTel, String adresseMail, Set<Dossier> dossiers) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexeClient = sexeClient;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.adresseMail = adresseMail;
        this.dossiers = dossiers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Sexe getSexeClient() {
        return sexeClient;
    }

    public void setSexeClient(Sexe sexeClient) {
        this.sexeClient = sexeClient;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public Set<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
    }
}
