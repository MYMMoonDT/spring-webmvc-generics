package com.googlecode.webmvc.web.servlet.generics.mvc;

import org.springframework.validation.Errors;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.webmvc.web.servlet.generics.util.GenericsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  Generic implementation of spring's
 *  {@link org.springframework.web.servlet.mvc.SimpleFormController}.
 * </p>
 * 
 * <p>
 *  Features added to this controller not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>errorView configuration parameter</li>
 *      <li>
 *          {@link #showForm(HttpServletRequest, HttpServletResponse, BindException, Object)}
 *          and
 *          {@link #showForm(HttpServletRequest, HttpServletResponse, Map, BindException, Object)}
 *          provide an argument for the command argument.
 *      </li>
 *      <li>
 *          {@link #referenceData(Object, Errors, HttpServletRequest, Map)} is void and
 *          is passed a model {@link Map} rather than returning one.
 *      </li>
 *      <li>
 *          {@link #referenceData(HttpServletRequest, Map)} is void and
 *          is passed a model {@link Map} rather than returning one.
 *      </li>
 *      <li>The commandClass is automatically determined and therefore doesn't need to be set</li>
 *  </ul>
 * </p>
 * 
 * @param <T>
 */
public abstract class SimpleFormControllerEx<T>
    extends org.springframework.web.servlet.mvc.SimpleFormController {

    private String errorView;
    
    /**
     * Creates the BaseCommandController.
     */
    public SimpleFormControllerEx() {
        super.setCommandClass(GenericsUtil.getTypeVariableClassByName(
            this.getClass(), SimpleFormControllerEx.class, "T", true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(
        HttpServletRequest request, Object command, Errors errors) 
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        referenceData(request, (T)command, errors, model);
        return (model.size()>0) ? model : null;
    }

    /**
     * Generic version of 
     * {@link #referenceData(HttpServletRequest, Object, Errors)}
     * except that the model Map is passed in to be populated.
     * @param command the command
     * @param errors the errors
     * @param request the request
     * @param model the model to be populated
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected void referenceData(
        HttpServletRequest request, T command, 
        Errors errors, Map<String, Object> model)
        throws Exception {
        Map superModel = super.referenceData(request, command, errors);
        if (superModel!=null) {
            model.putAll(superModel);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(HttpServletRequest request) 
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        referenceData(request, model);
        return (model.size()>0) ? model : null;
    }
    
    /**
     * Generic version of 
     * {@link #referenceData(HttpServletRequest)}
     * except that the model Map is passed in to be populated.
     * @param request the request
     * @param model the model to be populated
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected void referenceData(HttpServletRequest request, Map<String, Object> model)
        throws Exception {
        Map superModel = super.referenceData(request);
        if (superModel!=null) {
            model.putAll(superModel);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, BindException errors)
        throws Exception {
        T command = (errors!=null && errors.getTarget()!=null) 
            ? (T)errors.getTarget() : null;
        return showForm(request, response, errors, command);
    }
    
    /**
     * Generic version of
     * {@link #showForm(HttpServletRequest, HttpServletResponse, BindException)}.
     * @param request the request
     * @param response the response
     * @param errors the errors
     * @param command the command
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, 
        BindException errors, T command)
        throws Exception {
        return super.showForm(request, response, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, 
        BindException errors, Map controlModel)
        throws Exception {
        T command = (errors!=null && errors.getTarget()!=null) 
            ? (T)errors.getTarget() : null;
        return showForm(request, response, controlModel, errors, (T)command);
    }

    /**
     * Generic version of 
     * {@link #showForm(HttpServletRequest, HttpServletResponse, BindException, Map).
     * @param request the request
     * @param response the response
     * @param controlModel the control model
     * @param errors errors
     * @param command the command object
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, 
        Map<String, Object> controlModel, BindException errors, T command)
        throws Exception {
        return super.showForm(request, response, errors, controlModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final ModelAndView processFormSubmission(
        HttpServletRequest request, HttpServletResponse response, 
        Object command, BindException errors)
        throws Exception {
        
        if (errors.hasErrors()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Data binding errors: " + errors.getErrorCount());
            }
            return showForm(request, errors, errorView);
            
        } else if (isFormChangeRequest(request, command)) {
            logger.debug("Detected form change request -> routing request to onFormChange");
            onFormChange(request, response, command, errors);
            return showForm(request, response, errors);
            
        } else {
            logger.debug("No errors -> processing submit");
            return onSubmit(request, response, command, errors);
        }
    }

    /**
     * Generic version of
     * {@link #processFormSubmission(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * @param command the command object
     * @param errors errors
     * @param request the request
     * @param response the response
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView processFormSubmission(
        T command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        return super.processFormSubmission(request, response, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean isFormChangeRequest(
        HttpServletRequest request, Object command) {
        return isFormChangeRequest((T)command, request);
    }

    /**
     * Generic version if {@link #isFormChangeRequest(HttpServletRequest, Object)}.
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
    protected final void onFormChange(
        HttpServletRequest request, HttpServletResponse response,
        Object command, BindException errors) 
        throws Exception {
        onFormChange(response, request, errors, (T)command);
    }

    /**
     * Generic version of 
     * {@link #onFormChange(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * @param response the response
     * @param command the command
     * @param errors the BindException 
     * @param request the request
     * @throws Exception on error
     */
    protected void onFormChange(
        HttpServletResponse response,  HttpServletRequest request, 
        BindException errors, T command) 
        throws Exception {
        super.onFormChange(request, response, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onFormChange(
        HttpServletRequest request, HttpServletResponse response, Object command) 
        throws Exception {
        onFormChange((T)command, response, request);
    }

    /**
     * Generic version of 
     * {@link #onFormChange(HttpServletRequest, HttpServletResponse, Object)}.
     * @param response the response
     * @param command the command
     * @param request the request
     * @throws Exception on error
     */
    protected void onFormChange(T command, HttpServletResponse response, HttpServletRequest request)
        throws Exception {
        super.onFormChange(request, response, command);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(
        HttpServletRequest request, HttpServletResponse response,
        Object command, BindException errors) 
        throws Exception {
        return onSubmit(response, request, errors, (T)command);
    }

    /**
     * Generic version of 
     * {@link #onSubmit(HttpServletResponse, Object, BindException, HttpServletRequest)}.
     * @param response the response
     * @param command the command
     * @param errors the bindException
     * @param request the request
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView onSubmit(
        HttpServletResponse response, HttpServletRequest request,
        BindException errors, T command) 
        throws Exception {
        return super.onSubmit(request, response, command, errors);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(
        Object command, BindException errors) 
        throws Exception {
        return onSubmit(errors, (T)command);
    }

    /**
     * Generic version of {@link #onSubmit(BindException, Object)}.
     * @param errors the BindException
     * @param command the command
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView onSubmit(BindException errors, T command) 
        throws Exception {
        return super.onSubmit(command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView onSubmit(Object command) 
        throws Exception {
        return doOnSubmit((T)command);
    }

    /**
     * Generic version of {@link #onSubmit(Object)}.
     * @param command the command
     * @return ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView doOnSubmit(T command) 
        throws Exception {
        return super.onSubmit(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void doSubmitAction(Object command) 
        throws Exception {
        doSubmitActionGeneric((T)command);
    }

    /**
     * Generic version of {@link #doSubmitAction(Object)}.
     * @param command the command
     * @throws Exception on error
     */
    protected void doSubmitActionGeneric(T command) 
        throws Exception {
        super.doSubmitAction(command);    
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Object currentFormObject(
        HttpServletRequest request, Object sessionFormObject) 
        throws Exception {
        return currentFormObject((T)sessionFormObject, request);
    }
    
    /**
     * Generic version of
     * {@link #currentFormObject(HttpServletRequest, Object)}.
     * @param sessionFormObject the form object
     * @param request the request
     * @return the form object
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected T currentFormObject(
        T sessionFormObject, HttpServletRequest request) 
        throws Exception {
        return (T)super.currentFormObject(request, sessionFormObject);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected final Object formBackingObject(HttpServletRequest request)
        throws Exception {
        return (T)getFormBackingObject(request);
    }

    /**
     * Generic version of
     * {@link #formBackingObject(HttpServletRequest)}.
     * @param request the request
     * @return the form backing object
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected T getFormBackingObject(HttpServletRequest request)
        throws Exception {
        return (T)super.formBackingObject(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindOnNewForm(
        HttpServletRequest request, 
        Object command, BindException errors) 
        throws Exception {
        onBindOnNewForm((T)command, errors, request);
    }

    /**
     * Generic version of
     * {@link #onBindOnNewForm(HttpServletRequest, Object, BindException)}.
     * @param command the command
     * @param request the request
     * @param errors errors
     * @throws Exception on error
     */
    protected void onBindOnNewForm(
        T command, BindException errors, HttpServletRequest request) 
        throws Exception {
        super.onBindOnNewForm(request, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindOnNewForm(
        HttpServletRequest request, Object command)
        throws Exception {
        onBindOnNewForm((T)command, request);
    }

    /**
     * Generic version of
     * {@link #onBindOnNewForm(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @throws Exception on error
     */
    protected void onBindOnNewForm(
        T command, HttpServletRequest request)
        throws Exception {
        super.onBindOnNewForm(request, command);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ServletRequestDataBinder createBinder(
        HttpServletRequest request, Object command) 
        throws Exception {
        return createBinder((T)command, request);
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
        T command, HttpServletRequest request) 
        throws Exception {
        return super.createBinder(request, command);
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
    protected final void onBind(
        HttpServletRequest request, Object command, BindException errors) 
        throws Exception {
        onBind((T)command, errors, request);
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
        T command, BindException errors, HttpServletRequest request) 
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
        onBind((T)command, request);
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
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean suppressValidation(
        HttpServletRequest request, Object command, BindException errors) {
        return suppressValidation((T)command, request, errors);
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
        T command, HttpServletRequest request, BindException errors) {
        return super.suppressValidation(request, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean suppressValidation(
        HttpServletRequest request, Object command) {
        return suppressValidation((T)command, request);
    }

    /**
     * Generic implementation of
     * {@see #suppressValidation(Object, HttpServletRequest)}.
     * @param command the command
     * @param request the request
     * @return true or false ?
     */
    protected boolean suppressValidation(
        T command, HttpServletRequest request) {
        return super.suppressValidation(request, command);
    }

    /**
     * @param errorView the errorView to set
     */
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }
    
}
