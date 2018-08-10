package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springproject.twitter_app.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);


}
