package com.forum.webapp.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forum.webapp.services.IUserService;
import com.forum.webapp.web.models.User;

@Controller
public class NoSessionController extends AbstractController {

    public final static String USER_SESSION_ATTRIBUTES = "user";

    private IUserService userService;

    @Autowired(required = true)
    public void setUserService(final IUserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        // Date format.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loginForm(
            @RequestParam(value = "error", required = false) final String error,
            final HttpSession session) {
        session.invalidate();

        final Map<String, Object> model = new HashMap<String, Object>();
        if (null != error) {
            model.put("messageType", "error");
            model.put("messageKey", error);
        }
        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional
    public ModelAndView login(@RequestParam("login") final String login,
            @RequestParam("password") final String password, final HttpSession session) {
        final User user = userService.login(login, password);
        if (null == user) {
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("messageType", "error");
            model.put("messageKey", "login.error");

            return new ModelAndView("index", model);
        }
        session.setAttribute(USER_SESSION_ATTRIBUTES, user);
        return new ModelAndView("redirect:" + "/html/topic");
    }

    /**
     * Ouvre la page avec le formulaire d'inscription.
     * 
     * @return
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String suscribeForm() {
        return "subscribe";
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    @Transactional
    public ModelAndView subscribe(final User user, final BindingResult result,
            final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("messageType", "success");
        model.put("messageKey", "user.subscribe.success");

        return new ModelAndView("index", model);
    }
}
