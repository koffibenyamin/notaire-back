package tg.stage.nortaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    //@Query(value = "SELECT c FROM client c WHERE c.nom like %?1% ")
    List<Client> findByNomContainingOrPrenomContaining(String nom,String prenom);

    List<Client> findAll();
}
