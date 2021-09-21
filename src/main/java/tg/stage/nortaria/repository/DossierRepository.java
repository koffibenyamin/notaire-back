package tg.stage.nortaria.repository;



import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import tg.stage.nortaria.model.Dossier;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data SQL repository for the Dossier entity.
 */
@SuppressWarnings("unused")

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {


    List<Dossier> findByClientId(Long clientId);
    Optional<Dossier> findByIdAndClientId(Long id, Long clientId);


}
