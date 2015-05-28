package com.forum.webapp.web.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.forum.webapp.services.IMessageService;
import com.forum.webapp.web.models.Message;
import com.forum.webapp.web.models.User;

@Controller
@RequestMapping(value = "/message")
@SessionAttributes(NoSessionController.USER_SESSION_ATTRIBUTES)
public class MessageController extends AbstractController {

    private IMessageService _messageService;

    @Autowired(required = true)
    @Qualifier("messageService")
    public void setMessageService(IMessageService calendarService) {
        _messageService = calendarService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ModelAndView create(final Message message, final BindingResult result,
            @ModelAttribute(NoSessionController.USER_SESSION_ATTRIBUTES) final User user) throws Exception {
        checkSession(user);
        message.setOwnerId(user.getId());
        _messageService.create(message);
        return new ModelAndView("redirect:" + "/html/topic/" + message.getTopicId());
    }

}
