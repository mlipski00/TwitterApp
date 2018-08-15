package pl.springproject.twitter_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.CustomUserDetailsService;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/tweets/**", "/").authenticated()
                //.anyRequest().permitAll()
                //.and().formLogin().permitAll();
                //anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/tweets/index")
                .permitAll();
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
//                Pattern p = Pattern.compile(s);
//                Matcher m = p.matcher(charSequence);
                boolean result;
                try {
                    result = BCrypt.checkpw(charSequence .toString(), s);
                } catch (Exception e) {
                    System.out.println("getPasswordEncoder: " + e.getMessage() + "/n" + Arrays.deepToString(e.getStackTrace()));
                    return false;
                }
                return result;
//                return m.matches();
            }
        };
    }


}
