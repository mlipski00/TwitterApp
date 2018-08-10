package pl.springproject.twitter_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.springproject.twitter_app.domain.Role;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.CommentRepository;
import pl.springproject.twitter_app.repository.RoleRepository;
import pl.springproject.twitter_app.repository.TweetRepository;
import pl.springproject.twitter_app.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class InitDataService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = new Role("USER");
        User user = new User("Michał", "user123", true, "m@wp.pl");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        User user2 = new User("Tomasz", "user123", true, "tomek@wp.pl");
        user2.setRoles(roles);
        userRepository.save(user2);
    }
}
