package org.springframework.web.servlet.generics.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.generics.util.GenericsUtil;

/**
 * <p>
 *  Generic implementation of spring's
 *  {@link org.springframework.web.servlet.mvc.AbstractCommandController}.
 * </p>
 *
 * <p>
 *  Features added to this controller not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>defaultView configuration parameter</li>
 *      <li>errorView configuration parameter</li>
 *      <li>
 *          {@link #handle(Object, BindException, HttpServletRequest, HttpServletResponse)}
 *          is only called when there are no errors (ie: validation).
 *          In the case of errors {@link #handleError(Object, BindException, HttpServletRequest, HttpServletResponse)}
 *          is called.
 *      </li>
 *      <li>The commandClass is automatically determined and therefore doesn't need to be set</li>
 *  </ul>
 * </p>
 *
 * @param <CMD>
 */
public abstract class AbstractCommandController<CMD>
    extends org.springframework.web.servlet.mvc.AbstractCommandController {
    
    private String defaultView;
    private String errorView;
    
    /**
     * Creates the AbstractCommandController.
     */
    public AbstractCommandController() {
        super.setCommandClass(GenericsUtil.getCommandClassFromController(this));
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
        return (errors.hasErrors())
            ? handleError((CMD)command, errors, request, response)
            : handle((CMD)command, errors, request, response);
    }

    /**
     * Called when there are errors in the {@link BindException}.
     * The default implementation of this method returns a 
     * {@link ModelAndView} with the configured {@link #errorView}
     * name and populates the model with the errors from the
     * {@link BindException}.
     * @param command the command object
     * @param errors the errors
     * @param request the request
     * @param response the response
     * @return the model and view
     * @throws Exception on error.
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView handleError(
        CMD command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(getCommandName(), command);
        model.putAll(errors.getModel());
        return new ModelAndView(this.errorView, model);
    }
    
    /**
     * Generic version of 
     * {@link #handle(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * This method is only called if {@link BindException#hasErrors()} 
     * returns false, otherwise a {@link ModelAndView} is returned with 
     * the configured {@link #errorView}.
     * @param command the command object
     * @param errors the errors
     * @param request the request
     * @param response the response
     * @return the model and view
     * @throws Exception on error.
     */
    protected abstract ModelAndView handle(
        CMD command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindAndValidate(
        HttpServletRequest request, Object command, BindException errors) 
        throws Exception {
        onBindAndValidate((CMD)command, request, errors);
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
        CMD command, HttpServletRequest request, BindException errors) 
        throws Exception {
        // no-op
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBind(
        HttpServletRequest request, Object command, BindException errors) 
        throws Exception {
        onBind((CMD)command, errors, request);
    }
    
    /**
     * Generic version of 
     * {@link #onBind(HttpServletRequest, Object, BindException)}.
     * @param command the command object
     * @param errors the errors
     * @param request the request
     * @throws Exception on error
     */
    protected void onBind(
        CMD command, BindException errors, HttpServletRequest request) 
        throws Exception {
        onBind(request, command);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBind(HttpServletRequest request, Object command)
        throws Exception {
        onBind((CMD)command, request);
    }
    
    /**
     * Generic version of
     * {@link #onBind(HttpServletRequest, Object)}.
     * @param command the command object
     * @param request the request
     * @throws Exception on error
     */
    protected void onBind(CMD command, HttpServletRequest request)
        throws Exception {
        // no-op
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ServletRequestDataBinder createBinder(
        HttpServletRequest request, Object command) 
        throws Exception {
        return createBinder((CMD)command, request);
    }

    /**
     * Generic implementation of
     * {@see #createBinder(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @return the ServletRequestDataBinder
     * @throws Exception on error
     */
    protected ServletRequestDataBinder createBinder(
        CMD command, HttpServletRequest request) 
        throws Exception {
        return super.createBinder(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean suppressValidation(
        HttpServletRequest request, Object command, BindException errors) {
        return suppressValidation((CMD)command, request, errors);
    }

    /**
     * Generic implementation of
     * {@see #suppressValidation(HttpServletRequest, Object, BindException)}.
     * @param command the command
     * @param request the request
     * @param errors the errors
     * @return true or false ?
     */
    protected boolean suppressValidation(
        CMD command, HttpServletRequest request, BindException errors) {
        return super.suppressValidation(request, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean suppressValidation(
        HttpServletRequest request, Object command) {
        return suppressValidation((CMD)command, request);
    }

    /**
     * Generic implementation of
     * {@see #suppressValidation(Object, HttpServletRequest)}.
     * @param command the command
     * @param request the request
     * @return true or false ?
     */
    protected boolean suppressValidation(
        CMD command, HttpServletRequest request) {
        return super.suppressValidation(request, command);
    }

    /**
     * @return the defaultView
     */
    public String getDefaultView() {
        return defaultView;
    }

    /**
     * @param defaultView the defaultView to set
     */
    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    /**
     * @return the errorView
     */
    public String getErrorView() {
        return errorView;
    }

    /**
     * @param errorView the errorView to set
     */
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

}
