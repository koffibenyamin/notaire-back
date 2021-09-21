package tg.stage.nortaria.repository;



import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.Document;
import tg.stage.nortaria.model.Dossier;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Dossier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByDossierId(Long dossierId);
    Optional<Document> findByIdAndDossierId(Long id, Long dossierId);

}
