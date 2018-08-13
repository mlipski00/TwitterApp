package pl.springproject.twitter_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.springproject.twitter_app.domain.Message;
import pl.springproject.twitter_app.repository.MessageRespository;
import pl.springproject.twitter_app.repository.UserRepository;
import pl.springproject.twitter_app.service.AuthenticationFacade;
import pl.springproject.twitter_app.service.MessageService;
import pl.springproject.twitter_app.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRespository messageRespository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/newmessage", method = RequestMethod.GET)
    public String getMessageForm(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("message", new Message());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("users", messageService.getReciversList());
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "messageForm";
    }

    @RequestMapping(value = "/tweets/message/reply/{id}", method = RequestMethod.GET)
    public String getMessageFormReply(@PathVariable("id") long id, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("message", new Message());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("users", messageService.getReciversList());
        model.addAttribute("reciver", userService.getUserById(id));
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "messageFormReply";
    }

    @RequestMapping(value = "/tweets/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@Valid Message message, BindingResult result, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (result.hasErrors()) {
            model.addAttribute("message", message);
            model.addAttribute("user", authentication.getPrincipal());
            model.addAttribute("users", messageService.getReciversList());
            model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
            return "messageForm";
        }
        message.setSender(userService.getLoggedUser());
        message.setRead(false);
        messageRespository.save(message);
        model.addAttribute("messages", messageRespository.findAllByReciver(userService.getLoggedUser()));
        model.addAttribute("users", messageService.getReciversList());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "messageInboxList";
    }

    @RequestMapping(value = "/tweets/inbox", method = RequestMethod.GET)
    public String openInbox(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("messages", messageRespository.findAllByReciver(userService.getLoggedUser()));
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        model.addAttribute("user", authentication.getPrincipal());
        return "messageInboxList";
    }

    @RequestMapping(value = "/tweets/message/{id}", method = RequestMethod.GET)
    public String openSingleMessage(@PathVariable("id") long id, Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<Message> messageOptional = messageRespository.findById(id);
        Message message = messageOptional.get();
        message.setRead(true);
        messageRespository.save(message);
        model.addAttribute("message", message);
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        model.addAttribute("user", authentication.getPrincipal());
        return "messageDetails";
    }
}
