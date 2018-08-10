package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.repository.TweetRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;

@Controller
public class TweetsController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public String getAllTweets(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("tweets", tweetRepository.findAllByOrderByIdDesc());
        System.out.println(tweetRepository.findAllByOrderByIdDesc());
        return "alltweets";
    }

}
