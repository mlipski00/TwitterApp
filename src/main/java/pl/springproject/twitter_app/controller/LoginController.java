package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.Role;
import pl.springproject.twitter_app.domain.Tweet;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.RoleRepository;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;
import pl.springproject.twitter_app.service.MessageService;
import pl.springproject.twitter_app.validator.ValidationGroupUniqueEmail;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = {"/login"},  method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = {"/"},  method = RequestMethod.GET)
    public String mainPage(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("pageMessage", "Welcome on Tweeter App index page.");
        model.addAttribute("formMessage", "Add tweet");
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("formAction", "addTweet");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetForm";
    }

    @RequestMapping(value = {"/registration"},  method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@Validated({ValidationGroupUniqueEmail.class, Default.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        Set<Role> roles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByRole("USER");
        if (!optionalRole.isPresent()) {
            return "500"; //todo
        }
        roles.add(optionalRole.get());
        user.setActive(true);
        user.setRoles(roles);
        userRepository.save(user);
        model.addAttribute("registrationResult", 1);
        return "login";
    }
}
