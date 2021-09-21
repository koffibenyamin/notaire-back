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
 * A TypeDossier.
 */
@Entity
@Table(name = "type_dossier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeDossier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_type_dossier", nullable = false)
    private String nomTypeDossier;

    @OneToMany(mappedBy = "typeDossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"documents", "client", "typeDossier"}, allowSetters = true)
    private Set<Dossier> dossiers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeDossier() {
        return nomTypeDossier;
    }

    public void setNomTypeDossier(String nomTypeDossier) {
        this.nomTypeDossier = nomTypeDossier;
    }

    public Set<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
    }
}


