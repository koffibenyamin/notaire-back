package tg.stage.nortaria.repository;

import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tg.stage.nortaria.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM utilisateur U WHERE U.username NOT IN " +
            "(SELECT U.username FROM utilisateur U JOIN user_roles UR ON UR.user_id= U.id " +
            "JOIN role R on UR.role_id= R.id WHERE R.name = 'ROLE_ADMIN')", nativeQuery = true)
    List<User> findByRoles();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
