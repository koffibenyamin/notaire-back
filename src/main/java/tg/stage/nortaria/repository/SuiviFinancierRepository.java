package tg.stage.nortaria.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.SuiviFinancier;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data SQL repository for the SuiviFinancier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviFinancierRepository extends JpaRepository<SuiviFinancier, Long> {

    List<SuiviFinancier> findByDossierId(Long DossierId);
    Optional<SuiviFinancier> findByIdAndDossierId(Long Id, Long DossierId);

    @Query(value = "SELECT sum(montant) FROM public.suivi_financier " +
            "where payer_par like 'Cabinet' and dossier_id=?1 ", nativeQuery = true)
    Optional<Double> montantCabinet(Long id);

    @Query(value = "SELECT sum(montant) FROM public.suivi_financier " +
            "where payer_par like 'Client' and dossier_id=?1 ", nativeQuery = true)
    Optional<Double> montantClient(Long id);

    @Query(value = "SELECT sum(montant) FROM public.suivi_financier " +
            "where dossier_id=?1 ", nativeQuery = true)
    Optional<Double> montant(Long id);
}

