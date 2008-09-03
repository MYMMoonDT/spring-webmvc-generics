package org.springframework.web.servlet.generics.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.generics.util.GenericsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public abstract class SimpleFormController<T>
        extends org.springframework.web.servlet.mvc.SimpleFormController {

    /**
     * Creates the BaseCommandController.
     */
    public SimpleFormController() {
        super.setCommandClass(GenericsUtil.getTypeVariableClassByName(
            this.getClass(), SimpleFormController.class, "T", true));
    }

    /**
     * Generic version of {{@link #referenceData(HttpServletRequest, Object, Errors)}
     * @param command the command
     * @param errors the errors
     * @param httpServletRequest the request
     * @return a Map
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected Map referenceData(T command, Errors errors, HttpServletRequest httpServletRequest)
        throws Exception {
        return super.referenceData(httpServletRequest, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(HttpServletRequest httpServletRequest, Object command, Errors errors)
        throws Exception {
        return referenceData((T)command, errors, httpServletRequest);
    }

    /**
     * Generic version of {@link #processFormSubmission(HttpServletRequest, HttpServletResponse, Object, BindException)}
     * @param response the response
     * @param command the command
     * @param e the errors
     * @param request the request
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView processFormSubmission(HttpServletResponse response, T command, BindException e,
         HttpServletRequest request) throws Exception {
        return super.processFormSubmission(request, response, command, e);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView processFormSubmission(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object command, BindException e) throws Exception {
        if(e.hasErrors()) {
            return showForm(httpServletRequest, httpServletResponse, e);
        }
        return processFormSubmission(httpServletResponse, (T)command, e, httpServletRequest);
    }

    /**
     * Generic version of {@link #referenceData(HttpServletRequest, Object)} 
     * @param command the command
     * @param request the request
     * @return boolean
     */
    protected boolean suppressValidation(T command, HttpServletRequest request) {
        return super.suppressValidation(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean suppressValidation(HttpServletRequest httpServletRequest, Object command) {
        return suppressValidation((T)command, httpServletRequest);
    }

    /**
     * Generic version if {@link #isFormChangeRequest(HttpServletRequest, Object)}
     * @param command the command
     * @param request the request
     * @return boolean 
     */
    protected boolean isFormChangeRequest(T command, HttpServletRequest request) {
        return super.isFormChangeRequest(request, command);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean isFormChangeRequest(HttpServletRequest httpServletRequest, Object command) {
        return isFormChangeRequest((T)command, httpServletRequest);
    }

    /**
     * Generic version of {@link #onFormChange(HttpServletRequest, HttpServletResponse, Object, BindException)}
     * @param response the response
     * @param command the command
     * @param e the BindException 
     * @param request the request
     * @throws Exception on error
     */
    protected void onFormChange(HttpServletResponse response, T command, BindException e,
        HttpServletRequest request) throws Exception {
        super.onFormChange(request, response, command, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onFormChange(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object command, BindException e) throws Exception {
        onFormChange(httpServletResponse, (T)command, e, httpServletRequest);
    }

    /**
     * Generic version of {@link #onFormChange(HttpServletRequest, HttpServletResponse, Object)}
     * @param response the response
     * @param command the command
     * @param request the request
     * @throws Exception on error
     */
    protected void onFormChange(HttpServletResponse response, T command, HttpServletRequest request)
        throws Exception {
        super.onFormChange(request, response, command);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onFormChange(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object command) throws Exception {
        onFormChange(httpServletResponse, (T)command, httpServletRequest);
    }

    /**
     * Generic version of {@link #onSubmit(HttpServletResponse, Object, BindException, HttpServletRequest)}
     * @param response the response
     * @param command the command
     * @param e the bindException
     * @param request the request
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView onSubmit(HttpServletResponse response, T command, BindException e,
        HttpServletRequest request) throws Exception {
        return super.onSubmit(request, response, command, e);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object command, BindException e) throws Exception {
        return onSubmit(httpServletResponse, (T)command, e, httpServletRequest);
    }

    /**
     * Generic version of {@link #onSubmit(BindException, Object)}
     * @param e the BindException
     * @param command the command
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView onSubmit(BindException e, T command) throws Exception {
        return super.onSubmit(command, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(Object command, BindException e) throws Exception {
        return onSubmit(e, (T)command);
    }

    /**
     * Generic version of {@link #onSubmit(Object)}
     * @param command the command
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView doOnSubmit(T command) throws Exception {
        return super.onSubmit(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(Object command) throws Exception {
        return doOnSubmit((T)command);
    }

    /**
     * Generic version of {@link #doSubmitAction(Object)}
     * @param command the command
     * @throws Exception on error
     */
    protected void doSubmitActionGeneric(T command) throws Exception {
        super.doSubmitAction(command);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void doSubmitAction(Object command) throws Exception {
        doSubmitAction((T)command);
    }
}
