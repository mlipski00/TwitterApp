package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.springproject.twitter_app.domain.CustomUserDetails;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.TweetRepository;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;
import pl.springproject.twitter_app.service.MessageService;

import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/tweets", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllTweets(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("tweets", tweetRepository.findAllByOrderByIdDesc());
        model.addAttribute("pageMessage", "All tweets");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetList";
    }

    @RequestMapping(value = "/newtweets", method = {RequestMethod.GET, RequestMethod.POST})
    public String getNewTweets(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("tweets", tweetRepository.findFirst5ByOrderByIdDesc());
        model.addAttribute("pageMessage", "Last 5 tweets");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetList";
    }

    @RequestMapping(value = "/tweets/user/{id}", method = {RequestMethod.GET})
    public String getUserTweets(@PathVariable("id") long id, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("tweets", tweetRepository.findAllByUser(user));
        model.addAttribute("pageMessage", user.getUsername() + " tweets");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetList";
    }

    @RequestMapping(value = "/addTweet", method = RequestMethod.POST)
    public String addTweet(@Valid Tweet tweet, BindingResult result, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (result.hasErrors()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("pageMessage", "Add new tweet.");
            model.addAttribute("formMessage", "Add tweet");
            model.addAttribute("formAction", "addTweet");
            model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
            return "tweetForm";
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

    @RequestMapping(value = "tweet/edit/{id}", method = RequestMethod.GET)
    public String tweetEditForm(@PathVariable("id") long id, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        Tweet tweet = optionalTweet.get();
        model.addAttribute("tweet", tweet);
        System.out.println(tweet);
        model.addAttribute("pageMessage", "Edit your tweet.");
        model.addAttribute("formMessage", "Edit tweet");
        model.addAttribute("formAction", "editTweet");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetForm";
    }

    @RequestMapping(value = "/editTweet", method = RequestMethod.POST)
    public String tweetEditProcess(@Valid Tweet tweet, BindingResult result, Model model) {
        System.out.println(tweet);
        Authentication authentication = authenticationFacade.getAuthentication();
        if (result.hasErrors()) {
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("tweet", tweet);
            model.addAttribute("pageMessage", "Edit your tweet.");
            model.addAttribute("formMessage", "Add tweet");
            model.addAttribute("formAction", "editTweet");
            model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
            return "tweetForm";
        }
        model.addAttribute("user", authentication.getPrincipal());
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweet.getId());
        Tweet tweet2 = optionalTweet.get();
        tweet2.setText(tweet.getText());
        tweetRepository.save(tweet2);
        return "redirect:tweets";
    }

    @RequestMapping(value = "tweet/delete/{id}", method = RequestMethod.GET)
    public ModelAndView tweetEditForm(@PathVariable("id") long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        Tweet tweet = optionalTweet.get();
        tweetRepository.delete(tweet);
        return new ModelAndView("redirect:/tweets");
    }
}
