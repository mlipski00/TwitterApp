package pl.springproject.twitter_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.User;

import javax.validation.Valid;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/login"},  method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = {"/", "/login"},  method = RequestMethod.POST)
    public String processLogin(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        model.addAttribute("message", "User logged");
        return "success";
    }

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
        model.addAttribute("message", "User added");
        return "success";
    }
}
