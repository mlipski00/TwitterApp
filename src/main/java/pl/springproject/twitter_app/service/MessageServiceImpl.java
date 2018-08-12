package pl.springproject.twitter_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.springproject.twitter_app.domain.CustomUserDetails;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.MessageRespository;
import pl.springproject.twitter_app.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRespository messageRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    private User getLoggedUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findById(customUserDetails.getId());
        return userOptional.get();
    }

    @Override
    public int numberOfUnreadMessages() {
        return messageRespository.findAllByReciverAndIsReadIsFalse(getLoggedUser()).size();
    }

    @Override
    public List<User> getReciversList() {
        List<User> userList = userRepository.findAll();
        userList.remove(getLoggedUser());
        return userList;
    }
}
