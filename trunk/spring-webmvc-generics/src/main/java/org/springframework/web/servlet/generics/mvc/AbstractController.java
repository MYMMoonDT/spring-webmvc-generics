package org.springframework.web.servlet.generics.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  Implementation of spring's
 *  {@link org.springframework.web.servlet.mvc.AbstractController}.
 * </p>
 *
 * <p>
 *  Features added to this controller not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>defaultView configuration parameter</li>
 *      <li>
 *          {@link #handleRequestInternal(HttpServletRequest, HttpServletResponse, Map)
 *          passes a model map as an added convenience.
 *      </li>
 *  </ul>
 * </p>
 */
public abstract class AbstractController 
    extends org.springframework.web.servlet.mvc.AbstractController {

    private String defaultView;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected final ModelAndView handleRequestInternal(
        HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        return handleRequestInternal(
            request, response, new HashMap<String, Object>());
    }
    
    /**
     * Alternate version of
     * {@link #handleRequestInternal(HttpServletRequest, HttpServletResponse)}
     * that provides a model map as an added convenience.
     * @param request the request
     * @param response the response
     * @param model the model
     * @return the ModelAndView
     * @throws Exception on error
     */
    protected abstract ModelAndView handleRequestInternal(
        HttpServletRequest request, HttpServletResponse response, 
        Map<String, Object> model) 
        throws Exception;

    /**
     * Returns the configured default view.
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

}
