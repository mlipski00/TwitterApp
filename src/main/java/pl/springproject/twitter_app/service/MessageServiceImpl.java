package pl.springproject.twitter_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.MessageRespository;
import pl.springproject.twitter_app.repository.UserRepository;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRespository messageRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public int numberOfUnreadMessages() {
        return messageRespository.findAllByReciverAndIsReadIsFalse(userService.getLoggedUser()).size();
    }

    @Override
    public List<User> getReciversList() {
        List<User> userList = userRepository.findAll();
        userList.remove(userService.getLoggedUser());
        return userList;
    }
}
