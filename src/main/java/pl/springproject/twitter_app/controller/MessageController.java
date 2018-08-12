package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.Message;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;
import pl.springproject.twitter_app.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/newmessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendMessage(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("message", new Message());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("users", messageService.getReciversList());
        return "messageForm";
    }
}
