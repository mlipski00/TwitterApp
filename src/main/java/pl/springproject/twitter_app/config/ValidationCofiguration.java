package pl.springproject.twitter_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.springproject.twitter_app.service.UserService;
import pl.springproject.twitter_app.validator.UniqueEmailValidator;

@Configuration
public class ValidationCofiguration {

    @Autowired
    private UserService userService;

    @Bean
    public UniqueEmailValidator uniqueEmailValidator() {
        return  new UniqueEmailValidator(userService);
    }
}
