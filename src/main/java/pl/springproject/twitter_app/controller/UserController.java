package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;
import pl.springproject.twitter_app.service.MessageService;
import pl.springproject.twitter_app.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/tweets/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "userList";
    }

    @RequestMapping(value = "/tweets/user", method = RequestMethod.GET)
    public String getUserDetails(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "userDetails";
    }

    @RequestMapping(value = "/tweets/user", method = RequestMethod.POST)
    public String editUserDetails(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
            return "userDetails";
        }
        User user2 = userService.getUserById(user.getId());
        user2.setUserDetails(user.getUserDetails());
        userRepository.save(user2);
        model.addAttribute("updated", 1);
        model.addAttribute("user", user2);
        System.out.println(user2);
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "userDetails";
    }
}
