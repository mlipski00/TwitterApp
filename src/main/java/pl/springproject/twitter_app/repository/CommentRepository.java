package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.springproject.twitter_app.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
