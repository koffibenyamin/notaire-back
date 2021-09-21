package tg.stage.nortaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tg.stage.nortaria.model.Auteur;

/**
 * A SuiviFinancier.
 */
@Entity
@Table(name = "suivi_financier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SuiviFinancier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_suivi", nullable = false)
    private String dateSuivi;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant", nullable = false)
    private Double montant;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payer_par", nullable = false)
    private Auteur payerPar;

    @ManyToOne
    @JsonIgnoreProperties(value = {"suivis", "suiviFinanciers", "document", "typedocument"}, allowSetters = true)
    private Dossier dossier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateSuivi() {
        return dateSuivi;
    }

    public void setDateSuivi(String dateSuivi) {
        this.dateSuivi = dateSuivi;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Auteur getPayerPar() {
        return payerPar;
    }

    public void setPayerPar(Auteur payerPar) {
        this.payerPar = payerPar;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}