package com.forum.webapp.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.forum.webapp.services.IUserService;
import org.springframework.ui.ModelMap;

@Controller
@SessionAttributes(NoSessionController.USER_SESSION_ATTRIBUTES)
public class UserController extends AbstractController {

    private IUserService _userService;

    @Autowired(required = true)
    @Qualifier("userService")
    public void setUserService(final IUserService userService) {
        _userService = userService;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(final HttpSession session, final ModelMap modelMap) throws Exception {
        modelMap.clear();
        session.removeAttribute(NoSessionController.USER_SESSION_ATTRIBUTES);
        session.invalidate();
        return "index";
    }

}
