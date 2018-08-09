package pl.springproject.twitter_app.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    public Authentication getAuthentication();
}
