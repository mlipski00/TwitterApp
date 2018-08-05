package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springproject.twitter_app.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
