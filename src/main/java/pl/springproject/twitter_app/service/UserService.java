package pl.springproject.twitter_app.service;

import pl.springproject.twitter_app.domain.User;

import java.util.List;

public interface UserService {

    User getLoggedUser();

    User getUserById(long id);

    public List<User> getAllUsers();

    public boolean validUserEmail(String email);
}
