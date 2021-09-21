package tg.stage.nortaria.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.Suivi;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data SQL repository for the Suivi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviRepository extends JpaRepository<Suivi, Long> {

    List<Suivi> findByDossierId(Long DossierId);
    Optional<Suivi> findByIdAndDossierId(Long Id, Long DossierId);

}
