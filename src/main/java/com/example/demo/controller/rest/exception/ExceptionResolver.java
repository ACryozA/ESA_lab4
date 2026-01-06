package com.example.demo.controller.rest.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message",
                "Something went wrong. Please try again later or contact the administrator.");
        modelAndView.addObject("exceptionMessage", ex.getMessage() == null ? "" : ex.getMessage());
        modelAndView.addObject("exceptionCause", ex.getCause() == null ? "" : ex.getCause());
        return modelAndView;
    }
}
