package com.forum.webapp.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.webapp.web.models.User;

public class AbstractController {

    private final static Logger LOGGER = Logger.getLogger(NoSessionController.class);

    public void checkSession(final User user) throws HttpSessionRequiredException {
        if (null == user || null == user.getEmail()) {
            throw new HttpSessionRequiredException("");
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(final Exception ex, final HttpServletRequest request) {
        // TODO.
        ex.printStackTrace();
        LOGGER.error(ex);
        return ClassUtils.getShortName(ex.getClass());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public String handleDuplicateKeyException(final DuplicateKeyException ex, final HttpServletRequest request) {
        // TODO.
        LOGGER.error(ex);
        return ClassUtils.getShortName(ex.getClass());
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    public ModelAndView handleHttpSessionRequiredException(final HttpSessionRequiredException ex,
            final HttpServletRequest request) {
        LOGGER.error(ex);
        final Map<String, Object> model = new HashMap<String, Object>();
        request.getSession().setAttribute("messageType", "error");
        request.getSession().setAttribute("messageKey", "session.invalid");

        return new ModelAndView("redirect:/html/?error=session.invalid", model);
    }

}
