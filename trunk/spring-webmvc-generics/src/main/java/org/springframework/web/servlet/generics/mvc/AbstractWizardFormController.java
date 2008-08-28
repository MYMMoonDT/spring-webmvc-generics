package org.springframework.web.servlet.generics.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.generics.util.GenericsUtil;

/**
 * <p>
 *  Generic implementation of spring's
 *  {@link org.springframework.web.servlet.mvc.AbstractWizardFormController}.
 * </p>
 * 
 * <p>
 *  Features added to this controller not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>The commandClass is automatically determined and therefore doesn't need to be set</li>
 *  </ul>
 * </p>
 * @param <CMD>
 */
public abstract class AbstractWizardFormController<CMD>
    extends org.springframework.web.servlet.mvc.AbstractWizardFormController {
    
    /**
     * Creates the BaseCommandController.
     */
    public AbstractWizardFormController() {
        super.setCommandClass(GenericsUtil.getTypeVariableClassByName(
            this.getClass(), "CMD", true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final int getInitialPage(HttpServletRequest request, Object command) {
        return getInitialPage((CMD)command, request);
    }
    
    /**
     * Generic version of
     * {@link #getInitialPage(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @return the initial page
     */
    protected int getInitialPage(CMD command, HttpServletRequest request) {
        return super.getInitialPage(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final int getPageCount(HttpServletRequest request, Object command) {
        return getPageCount((CMD)command, request);
    }
    
    /**
     * Generic version of
     * {@link #getPageCount(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @return the page count
     */
    protected int getPageCount(CMD command, HttpServletRequest request) {
        return super.getPageCount(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final int getTargetPage(
        HttpServletRequest request, Object command,
        Errors errors, int currentPage) {
        return super.getTargetPage(request, command, errors, currentPage);
    }
    
    /**
     * Generic version of
     * {@link #getTargetPage(HttpServletRequest, Object, Errors, int)}.
     * @param command the command
     * @param request the request
     * @param errors the errors
     * @param currentPage the current page
     * @return the target page
     */
    protected int getTargetPage(
        CMD command, HttpServletRequest request,
        Errors errors, int currentPage) {
        return super.getTargetPage(request, command, errors, currentPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final String getViewName(
        HttpServletRequest request, Object command, int page) {
        return getViewName((CMD)command, request, page);
    }
    
    /**
     * Generic version of
     * {@link #getViewName(HttpServletRequest, Object, int)}.
     * @param command the command
     * @param request the request
     * @param page the page number
     * @return the view name
     */
    protected String getViewName(
        CMD command, HttpServletRequest request, int page) {
        return super.getViewName(request, command, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindAndValidate(
        HttpServletRequest request, Object command, 
        BindException errors, int page) 
        throws Exception {
        onBindAndValidate((CMD)command, request, errors, page);
    }
    
    /**
     * Generic version of
     * {@link #onBindAndValidate(HttpServletRequest, Object, BindException, int)}.
     * @param command the command
     * @param request the request
     * @param errors the errors
     * @param page the page number
     * @throws Exception on error
     */
    protected void onBindAndValidate(
        CMD command, HttpServletRequest request, 
        BindException errors, int page) 
        throws Exception {
        super.onBindAndValidate(request, command, errors, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void postProcessPage(
        HttpServletRequest request, Object command,
        Errors errors, int page) 
        throws Exception {
        postProcessPage((CMD)command, request, errors, page);
    }
    
    /**
     * Generic version of
     * {@link #postProcessPage(HttpServletRequest, Object, Errors, int)}.
     * @param command the command
     * @param request the request
     * @param errors the errors
     * @param page the page number
     * @throws Exception on error
     */
    protected void postProcessPage(
        CMD command, HttpServletRequest request,
        Errors errors, int page) 
        throws Exception {
        super.postProcessPage(request, command, errors, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView processCancel(
        HttpServletRequest request, HttpServletResponse response, 
        Object command, BindException errors)
        throws Exception {
        return processCancel((CMD)command, errors, request, response);
    }
    
    /**
     * Generic version of
     * {@link #processCancel(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * @param command the command
     * @param errors the errors
     * @param request the request
     * @param response the response
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected ModelAndView processCancel(
        CMD command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        return super.processCancel(request, response, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final ModelAndView processFinish(
        HttpServletRequest request, HttpServletResponse response, 
        Object command, BindException errors)
        throws Exception {
        return processFinish((CMD)command, errors, request, response);
    }
    
    /**
     * Generic version of
     * {@link #processFinish(HttpServletRequest, HttpServletResponse, Object, BindException)}.
     * @param command the command
     * @param errors the errors
     * @param request the request
     * @param response the response
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected abstract ModelAndView processFinish(
        CMD command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(HttpServletRequest request, int page)
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        referenceData(request, model, page);
        return (model.size()>0) ? model : null;
    }
    
    /**
     * Generic version of 
     * {@link #referenceData(HttpServletRequest, int)}
     * except that the model Map is passed in to be populated.
     * @param request the request
     * @param model the model to populate
     * @param page the page number
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected void referenceData(
        HttpServletRequest request, Map<String, Object> model, int page) 
        throws Exception {
        Map superModel = super.referenceData(request, page);
        if (superModel!=null) {
            model.putAll(model);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Map referenceData(
        HttpServletRequest request, Object command,
        Errors errors, int page) 
        throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        referenceData((CMD)command, errors, request, model, page);
        return (model.size()>0) ? model : null;
    }
    
    /**
     * Generic version of 
     * {@link #referenceData(HttpServletRequest, Object, Errors, int)}
     * except that the model Map is passed in to be populated.
     * @param command the command
     * @param errors the errors
     * @param request the request
     * @param model the model
     * @param page the page number
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    public void referenceData(
        CMD command, Errors errors, HttpServletRequest request, Map<String, Object> model, int page)
        throws Exception {
        Map superModel = super.referenceData(request, command, errors, page);
        if (superModel!=null) {
            model.putAll(model);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void validatePage(
        Object command, Errors errors, int page, boolean finish) {
        validatePage(errors, (CMD)command, page, finish);
    }
    
    /**
     * Generic version of
     * {@link #validatePage(Object, Errors, int, boolean)}.
     * @param errors the errors
     * @param command the command
     * @param page the page
     * @param finish the finish
     */
    protected void validatePage(
        Errors errors, CMD command, int page, boolean finish) {
        super.validatePage(command, errors, page, finish);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void validatePage(Object command, Errors errors, int page) {
        validatePage(errors, (CMD)command, page);
    }
    
    /**
     * Generic version of
     * {@link #validatePage(Object, Errors, int)}.
     * @param errors the errors
     * @param command the command
     * @param page the page
     */
    protected void validatePage(Errors errors, CMD command, int page) {
        super.validatePage(command, errors, page);
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
        return getFormBackingObject(request);
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
    protected ModelAndView showForm(
        HttpServletRequest request, HttpServletResponse response, BindException errors)
        throws Exception {
        CMD command = (errors!=null && errors.getTarget()!=null) 
            ? (CMD)errors.getTarget() : null;
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
        BindException errors, CMD command)
        throws Exception {
        return super.showForm(request, response, errors);
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
}
