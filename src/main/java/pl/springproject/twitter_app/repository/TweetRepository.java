package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.springproject.twitter_app.domain.Tweet;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    public List<Tweet> findAllByOrderByIdDesc();


}
