package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.Comment;
import pl.springproject.twitter_app.domain.CustomUserDetails;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.CommentRepository;
import pl.springproject.twitter_app.repository.TweetRepository;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Controller
@Transactional
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/addComment/{tweetId}", method = RequestMethod.GET)
    public String commentForm(@PathVariable("tweetId") long tweetId, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
        Tweet tweet = optionalTweet.get();
        Comment comment = new Comment();
        comment.setTweet(tweet);
        System.out.println(comment);
        model.addAttribute("comment", comment);
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("pageMessage", "Add new comment.");
        model.addAttribute("formMessage", "Add comment");
        model.addAttribute("formAction", "addComment");
        return "commentForm";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@Valid Comment comment, BindingResult result, Model model) {
        System.out.println(comment);
        Authentication authentication = authenticationFacade.getAuthentication();
        if (result.hasErrors()) {
            model.addAttribute("comment", comment);
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("pageMessage", "Add new comment.");
            model.addAttribute("formMessage", "Add comment");
            model.addAttribute("formAction", "addComment");
            return "commentForm";
        }
        Comment comment1 = new Comment();
        comment1.setTweet(comment.getTweet());
        comment1.setText(comment.getText());


        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(customUserDetails.getEmail());
        if (!userOptional.isPresent()) {
            return "500"; //todo
        }
        User user = userOptional.get();
        comment1.setUser(user);
        commentRepository.saveAndFlush(comment1);
        System.out.println(commentRepository.getOne(comment1.getId()));;

        return "redirect:tweets";
    }
}
