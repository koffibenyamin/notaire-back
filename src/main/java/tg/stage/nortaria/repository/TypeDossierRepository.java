package tg.stage.nortaria.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.TypeDossier;


/**
 * Spring Data SQL repository for the TypeDossier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDossierRepository extends JpaRepository<TypeDossier, Long> {}
