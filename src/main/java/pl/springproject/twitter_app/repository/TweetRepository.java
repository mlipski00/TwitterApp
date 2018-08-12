package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    public List<Tweet> findAllByOrderByIdDesc();

    public List<Tweet> findAllByUser(User user);

    public List<Tweet> findFirst5ByOrderByIdDesc();
}
