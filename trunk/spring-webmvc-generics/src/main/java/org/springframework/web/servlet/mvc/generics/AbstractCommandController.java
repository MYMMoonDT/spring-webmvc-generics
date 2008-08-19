package org.springframework.web.servlet.mvc.generics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@see org.springframework.web.servlet.mvc.AbstractCommandController}.
 *
 * @param <T>
 */
public abstract class AbstractCommandController<T>
    extends org.springframework.web.servlet.mvc.AbstractCommandController {

    /**
     * Generic version of 
     * {@link #handle(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * @param command the command object
     * @param errors the errors
     * @param request the request
     * @param response the response
     * @return the model and view
     * @throws Exception on error.
     */
    protected abstract ModelAndView handle(
        T command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception;
    
    /**
     * Generic version of 
     * {@link #onBind(HttpServletRequest, Object, BindException)}.
     * @param command the command object
     * @param errors the errors
     * @param request the request
     * @throws Exception on error
     */
    protected void onBind(
        T command, BindException errors, HttpServletRequest request) 
        throws Exception {
        onBind(request, command);
    }
    
    /**
     * Generic version of
     * {@link #onBind(HttpServletRequest, Object)}.
     * @param command the command object
     * @param request the request
     * @throws Exception on error
     */
    protected void onBind(T command, HttpServletRequest request)
        throws Exception {
        // no-op
    }
    
    /**
     * Generic version of
     * {@link #onBindAndValidate(HttpServletRequest, Object, BindException)}.
     * @param command the command object
     * @param request the request
     * @param errors the errors
     * @throws Exception on error
     */
    protected void onBindAndValidate(
        T command, HttpServletRequest request, BindException errors) 
        throws Exception {
        // no-op
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView handle(
        HttpServletRequest request, HttpServletResponse response, 
        Object command, BindException errors)
        throws Exception {
        return handle((T)command, errors, request, response);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBind(
        HttpServletRequest request, Object command, BindException errors) 
        throws Exception {
        onBind((T)command, errors, request);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBind(HttpServletRequest request, Object command)
        throws Exception {
        onBind((T)command, request);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindAndValidate(
        HttpServletRequest request, Object command, BindException errors) 
        throws Exception {
        onBindAndValidate((T)command, request, errors);
    }

}
