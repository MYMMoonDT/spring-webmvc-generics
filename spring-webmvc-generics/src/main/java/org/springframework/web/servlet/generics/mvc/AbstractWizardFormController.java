package org.springframework.web.servlet.generics.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@see org.springframework.web.servlet.mvc.AbstractWizardFormController}.
 *
 * @param <T>
 */
public abstract class AbstractWizardFormController<T>
    extends org.springframework.web.servlet.mvc.AbstractWizardFormController {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final int getInitialPage(HttpServletRequest request, Object command) {
        return getInitialPage((T)command, request);
    }
    
    /**
     * Generic version of
     * {@link #getInitialPage(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @return the initial page
     */
    protected int getInitialPage(T command, HttpServletRequest request) {
        return super.getInitialPage(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final int getPageCount(HttpServletRequest request, Object command) {
        return getPageCount((T)command, request);
    }
    
    /**
     * Generic version of
     * {@link #getPageCount(HttpServletRequest, Object)}.
     * @param command the command
     * @param request the request
     * @return the page count
     */
    protected int getPageCount(T command, HttpServletRequest request) {
        return super.getPageCount(request, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
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
        T command, HttpServletRequest request,
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
        return getViewName((T)command, request, page);
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
        T command, HttpServletRequest request, int page) {
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
        onBindAndValidate((T)command, request, errors, page);
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
        T command, HttpServletRequest request, 
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
        postProcessPage((T)command, request, errors, page);
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
        T command, HttpServletRequest request,
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
        return processCancel((T)command, errors, request, response);
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
        T command, BindException errors,
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
        return processFinish((T)command, errors, request, response);
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
        T command, BindException errors,
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
        referenceData((T)command, errors, request, model, page);
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
        T command, Errors errors, HttpServletRequest request, Map<String, Object> model, int page)
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
        validatePage(errors, (T)command, page, finish);
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
        Errors errors, T command, int page, boolean finish) {
        super.validatePage(command, errors, page, finish);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final void validatePage(Object command, Errors errors, int page) {
        validatePage(errors, (T)command, page);
    }
    
    /**
     * Generic version of
     * {@link #validatePage(Object, Errors, int)}.
     * @param errors the errors
     * @param command the command
     * @param page the page
     */
    protected void validatePage(Errors errors, T command, int page) {
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
    @SuppressWarnings("unchecked")
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
        onBindOnNewForm((T)command, request, errors);
    }

    /**
     * Generic version of
     * {@link #onBindOnNewForm(HttpServletRequest, Object, BindException)}.
     * @param command the command
     * @param request the request
     * @param errors errors
     * @throws Exception on error
     */
    @SuppressWarnings("unchecked")
    protected void onBindOnNewForm(
        T command, HttpServletRequest request, BindException errors) 
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
    @SuppressWarnings("unchecked")
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
    protected ModelAndView showForm(
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
    @SuppressWarnings("unchecked")
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
}
