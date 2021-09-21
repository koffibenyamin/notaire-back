package tg.stage.nortaria.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.TypeDocument;

/**
 * Spring Data SQL repository for the TypeDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {}
