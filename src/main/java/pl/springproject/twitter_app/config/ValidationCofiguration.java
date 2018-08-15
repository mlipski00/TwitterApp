package pl.springproject.twitter_app.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidationCofiguration {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    //    @Bean
//    public Validator validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {
//
//        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
//                .configure().constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        return validator;
//    }
    @Bean
    public SpringConstraintValidatorFactory springConstraintValidatorFactory() {

        return new SpringConstraintValidatorFactory(autowireCapableBeanFactory);

    }
}
