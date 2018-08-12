package pl.springproject.twitter_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springproject.twitter_app.domain.Message;
import pl.springproject.twitter_app.domain.User;

import java.util.List;

public interface MessageRespository extends JpaRepository<Message, Long> {

    public List<Message> findAllByReciverAndIsReadIsFalse(User reciver);
    public List<Message> findAllByReciver(User reciver);
}
