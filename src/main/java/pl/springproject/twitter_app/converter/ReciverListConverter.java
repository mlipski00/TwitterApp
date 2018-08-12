package pl.springproject.twitter_app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.UserRepository;

@Component
public class ReciverListConverter  implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String s) {
        return userRepository.getOne(Long.parseLong(s));
    }
}
