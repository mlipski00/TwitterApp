package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;

import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Long> {


}
