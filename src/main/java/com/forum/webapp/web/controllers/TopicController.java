package com.forum.webapp.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.forum.webapp.services.IMessageService;
import com.forum.webapp.services.ITopicService;
import com.forum.webapp.services.IUserService;
import com.forum.webapp.web.models.Message;
import com.forum.webapp.web.models.Topic;
import com.forum.webapp.web.models.User;

@Controller
@RequestMapping(value = "/topic")
@SessionAttributes(NoSessionController.USER_SESSION_ATTRIBUTES)
public class TopicController extends AbstractController {

    private ITopicService _topicService;
    private IMessageService _messageService;
    private IUserService _userService;

    @Autowired(required = true)
    @Qualifier("topicService")
    public void setTopicService(final ITopicService eventService) {
        _topicService = eventService;
    }

    @Autowired(required = true)
    @Qualifier("messageService")
    public void setMessageService(final IMessageService messageService) {
        _messageService = messageService;
    }

    @Autowired(required = true)
    @Qualifier("userService")
    public void setUserService(final IUserService userService) {
        _userService = userService;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute(NoSessionController.USER_SESSION_ATTRIBUTES) final User user) throws Exception {
        checkSession(user);
        return "topic/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView add(final Topic topic,
            @ModelAttribute(NoSessionController.USER_SESSION_ATTRIBUTES) final User user) throws Exception {
        checkSession(user);
        final Long topicId = _topicService.create(topic);
        return get(topicId, user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@ModelAttribute(NoSessionController.USER_SESSION_ATTRIBUTES) final User user)
            throws Exception {
        checkSession(user);
        final List<Topic> topics = _topicService.list(user.getId());
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("topics", topics);
        return new ModelAndView("topic/list", model);
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("topicId") final long topicId,
            @ModelAttribute(NoSessionController.USER_SESSION_ATTRIBUTES) final User user) throws Exception {
        checkSession(user);
        final Topic topic = _topicService.get(topicId);
        if (null == topic) {
            final ModelAndView result = list(user);
            result.getModel().put("messageKey", "topic.list.invalid.topic");
            result.getModel().put("messageType", "error");
            return result;
        }
        final List<Message> messages = _messageService.list(topicId);

        final Map<Long, User> users = new HashMap<Long, User>();
        User owner;
        for (Message message : messages) {
            if (users.containsKey(message.getOwnerId())) {
                owner = users.get(message.getOwnerId());
            } else {
                owner = _userService.get(message.getOwnerId());
                users.put(owner.getId(), owner);
            }
            message.setOwner(owner);
        }

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("topic", topic);
        model.put("topicMessages", messages);

        return new ModelAndView("topic/view", model);
    }

}
