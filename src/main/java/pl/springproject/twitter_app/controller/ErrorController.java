package pl.springproject.twitter_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorPage");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//                        + "<div>Exception Message: <b>%s</b></div><body></html>",
//                statusCode, exception==null? "N/A": exception.getMessage());
        modelAndView.addObject("errorCode", statusCode);
        modelAndView.addObject("errorMsg", "");
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}