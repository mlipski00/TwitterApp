package pl.springproject.twitter_app.service;

import pl.springproject.twitter_app.domain.User;

import java.util.List;

public interface MessageService {

    public int numberOfUnreadMessages();

    public List<User> getReciversList();
}
