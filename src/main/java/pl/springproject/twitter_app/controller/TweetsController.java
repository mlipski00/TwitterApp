package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.CustomUserDetails;
import pl.springproject.twitter_app.domain.Role;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.TweetRepository;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@Transactional
public class TweetsController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/tweets", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllTweets(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("tweets", tweetRepository.findAllByOrderByIdDesc());
        model.addAttribute("pageMessage", "All tweets");
        return "alltweets";
    }

    @RequestMapping(value = "/addTweet", method = RequestMethod.POST)
    public String addTweet(@Valid Tweet tweet, BindingResult result, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (result.hasErrors()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("tweets", tweetRepository.findAllByOrderByIdDesc());
            model.addAttribute("pageMessage", "Welcome on Tweeter App index page.");
            model.addAttribute("formMessage", "Add tweet");
            return "tweetsIndex";
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(customUserDetails.getEmail());
        if (!userOptional.isPresent()) {
            return "500"; //todo
        }
        User user = userOptional.get();
        Tweet tweet1 = new Tweet();
        tweet1.setText(tweet.getText());
        tweet1.setUser(user);
        Set<Tweet> userTweets = user.getTweets();
        userTweets.add(tweet);
        userRepository.save(user);
        return "redirect:tweets";
    }
}
