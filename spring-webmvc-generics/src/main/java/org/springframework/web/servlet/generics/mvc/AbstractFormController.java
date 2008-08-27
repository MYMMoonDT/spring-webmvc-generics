package org.springframework.web.servlet.generics.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.generics.util.GenericsUtil;

import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * <p>
 *  Generic implementation of spring's
 *  {@link org.springframework.web.servlet.mvc.AbstractFormController}.
 * </p>
 * 
 * <p>
 *  Features added to this controller not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>formView configuration parameter</li>
 *      <li>successView configuration parameter</li>
 *      <li>
 *          {@link #processFormSubmission(Object, BindException, HttpServletRequest, HttpServletResponse)}
 *          is only called when there are no errors (ie: validation).
 *          In the case of errors {@link #showForm(HttpServletRequest, HttpServletResponse, BindException, Object)}
 *          is called.
 *      </li>
 *      <li>
 *          {@link #showForm(HttpServletRequest, HttpServletResponse, BindException, Object)}
 *          provides an argument for the command argument.
 *      </li>
 *      <li>
 *          {@link #referenceData(Object, Errors, HttpServletRequest, Map)} is void and
 *          is passed a model {@link Map} rather than returning one.
 *      </li>
 *      <li>The commandClass is automatically determined and therefore doesn't need to be set</li>
 *  </ul>
 * </p>
 * 
 * @see org.springframework.web.servlet.mvc.AbstractFormController
 * @param <CMD>
 */
public abstract class AbstractFormController<CMD> 
    extends org.springframework.web.servlet.mvc.AbstractFormController {

    private String formView;
    private String successView;
    
    /**
     * Creates the AbstractFormController.
     */
    public AbstractFormController() {
        super.setCommandClass(GenericsUtil.getCommandClassFromController(this));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Object currentFormObject(
        HttpServletRequest request, Object sessionFormObject) 
        throws Exception {
        return currentFormObject((CMD)sessionFormObject, request);
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
    protected CMD currentFormObject(
        CMD sessionFormObject, HttpServletRequest request) 
        throws Exception {
        return (CMD)super.currentFormObject(request, sessionFormObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Object formBackingObject(HttpServletRequest request)
        throws Exception {
        return (CMD)getFormBackingObject(request);
    }

    /**
     * Generic version of
     * {@link #formBackingObject(HttpServletRequest)}.
     * @param request the request
     * @return the form backing object
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected CMD getFormBackingObject(HttpServletRequest request)
        throws Exception {
        return (CMD)super.formBackingObject(request);
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
        onBindOnNewForm((CMD)command, request, errors);
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
        CMD command, HttpServletRequest request, BindException errors) 
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
        onBindOnNewForm((CMD)command, request);
    }

    /**
     * Generic version of
     * {@link #onBindOnNewForm(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @throws Exception on error
     */
    protected void onBindOnNewForm(
        CMD command, HttpServletRequest request)
        throws Exception {
        super.onBindOnNewForm(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView processFormSubmission(
        HttpServletRequest request, HttpServletResponse response, 
        Object command, BindException errors)
        throws Exception {
        if (errors.hasErrors()) {
            return showForm(request, response, errors);
        }
        return processFormSubmission((CMD)command, errors, request, response);
    }

    /**
     * Generic version of
     * {@link #processFormSubmission(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * This method is only called if {@link BindException#hasErrors()} returns false, otherwise
     * {@link #showForm(HttpServletRequest, HttpServletResponse, BindException, Object)}
     * is called.
     * @param command the command object
     * @param errors errors
     * @param request the request
     * @param response the response
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected abstract ModelAndView processFormSubmission(
        CMD command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(
        HttpServletRequest request, Object command, Errors errors) 
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        referenceData(request, (CMD)command, errors, model);
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
        HttpServletRequest request, CMD command, 
        Errors errors, Map<String, Object> model)
        throws Exception {
        Map superModel = super.referenceData(request, command, errors);
        if (superModel!=null) {
            model.putAll(model);
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
        CMD command = (errors!=null && errors.getTarget()!=null) 
            ? (CMD)errors.getTarget() : null;
        return showForm(request, response, errors, command);
    }
    
    /**
     * Generic version of
     * {@link #showForm(HttpServletRequest, HttpServletResponse, BindException)}.
     * The default implementation of this method returns
     * a ModelAndView with the view name congfigured via {@link #setFormView(String)}
     * and a model containing the command object and model data
     * from the {@link BindException} parameter.
     * @param request the request
     * @param response the response
     * @param errors the errors
     * @param command the command
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, 
        BindException errors, CMD command)
        throws Exception {
        return super.showForm(request, errors, this.formView);
    }

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
     * @return the formView
     */
    public String getFormView() {
        return formView;
    }

    /**
     * Sets the view name used for the form.
     * @param formView the formView to set
     */
    public void setFormView(String formView) {
        this.formView = formView;
    }

    /**
     * @return the successView
     */
    public String getSuccessView() {
        return successView;
    }

    /**
     * Sets the view name used for success.
     * @param successView the successView to set
     */
    public void setSuccessView(String successView) {
        this.successView = successView;
    }

}
