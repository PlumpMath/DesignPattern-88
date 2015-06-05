package com.forum.webapp.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(NoSessionController.USER_SESSION_ATTRIBUTES)
public class UserController extends AbstractController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(final HttpSession session, final ModelMap modelMap) throws Exception {
        modelMap.clear();
        session.removeAttribute(NoSessionController.USER_SESSION_ATTRIBUTES);
        session.invalidate();
        return "index";
    }

}
