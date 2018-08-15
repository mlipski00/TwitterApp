package pl.springproject.twitter_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.springproject.twitter_app.domain.*;
import pl.springproject.twitter_app.repository.*;

import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class InitDataService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MessageRespository messageRespository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = new Role("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User("Marek", "user123", true, "m@wp.pl");
        user.setRoles(roles);
        user.setUserDetails("Enim integre appellantur no vix. At per vero zril pertinax. Exerci utamur tritani id vel, ne tation officiis scripserit qui.");
        userRepository.save(user);

        Tweet tweet = new Tweet();
        tweet.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales auctor mi, eu pharetra est gravida eget. Proin libero");
        Set<Comment> comments = new HashSet<>();
        comments.add(new Comment("comment1", user));
        comments.add(new Comment("comment2", user));
        comments.add(new Comment("comment2", user));
        tweet.setComments(comments);
        tweet.setUser(user);
        tweetRepository.save(tweet);

        User user2 = new User("Tomasz", "user123", true, "tomek@wp.pl");
        user2.setRoles(roles);
        user2.setUserDetails("Enim integre appellantur no vix. At per vero zril pertinax. Exerci utamur tritani id vel, ne tation officiis scripserit qui.");
        userRepository.save(user2);

        Tweet tweet2 = new Tweet();
        tweet2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales auctor mi, eu pharetra est gravida eget. Proin libero");
        Set<Comment> comments2 = new HashSet<>();
        comments2.add(new Comment("comment1", user2));
        comments2.add(new Comment("comment2", user2));
        comments2.add(new Comment("comment3", user2));
        tweet2.setComments(comments2);
        tweet2.setUser(user2);
        tweetRepository.save(tweet2);

        Tweet tweet3 = new Tweet();
        tweet3.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales auctor mi, eu pharetra est gravida eget. Proin libero");
        Set<Comment> comments3 = new HashSet<>();
        comments3.add(new Comment("comment1",  user2));
        comments3.add(new Comment("comment2",  user2));
        comments3.add(new Comment("comment3",  user2));
        tweet3.setComments(comments3);
        tweet3.setUser(user2);
        tweetRepository.save(tweet3);

        Tweet tweet4 = new Tweet();
        tweet4.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales auctor mi, eu pharetra est gravida eget. Proin libero");
        Set<Comment> comments4 = new HashSet<>();
        comments4.add(new Comment("comment1",  user2));
        comments4.add(new Comment("comment2",  user2));
        comments4.add(new Comment("comment3",  user2));
        tweet4.setComments(comments4);
        tweet4.setUser(user2);
        tweetRepository.save(tweet4);

        Tweet tweet5= new Tweet();
        tweet5.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales auctor mi, eu pharetra est gravida eget. Proin libero");
        Set<Comment> comments5 = new HashSet<>();
        tweet5.setComments(comments5);
        tweet5.setUser(user2);
        tweetRepository.save(tweet5);

        User user3 = new User("Ada", "user123", true, "ada@wp.pl");
        user3.setRoles(roles);
        user3.setUserDetails("Enim integre appellantur no vix. At per vero zril pertinax. Exerci utamur tritani id vel, ne tation officiis scripserit qui.");
        userRepository.save(user3);


        Message message = new Message();
        message.setReciver(user);
        message.setText("Enim integre appellantur no vix. Exerci utamur tritani id vel, ne tation officiis scripserit qui. Exerci utamur tritani id vel, ne tation officiis scripserit qui.");
        message.setRead(false);
        message.setSender(user2);
        messageRespository.save(message);
    }
}
