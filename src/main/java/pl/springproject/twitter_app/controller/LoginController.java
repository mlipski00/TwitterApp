package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.Role;
import pl.springproject.twitter_app.domain.User;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;


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
        return "tweetsIndex";
    }

//    @RequestMapping(value = {"/tweets/index"},  method = RequestMethod.POST)
//    public String processLogin(@ModelAttribute User user, Model model) {
//        return "tweetsIndex";
//    }


    @RequestMapping(value = {"/registration"},  method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("MOCK"));
        user.setActive(true);
        user.setRoles(roles);
        userRepository.save(user);
        model.addAttribute("message", "User added");
        return "success";
    }
}
