package com.googlecode.webmvc.web.servlet.generics.bind;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * <p>
 *  {@link ServletRequestDataBinder} that uses 
 *  a {@link UrlPropertyValues} to bind from.  For
 *  more information on the url patterns see {@link UrlPropertyValues}.
 * </p>
 * 
 * {@see UrlPropertyValues}
 */
public class UrlHttpServletRequestDataBinder 
    extends ServletRequestDataBinder {

    private String urlPattern;
    
    /**
     * Create a new UrlServletRequestDataBinder instance, with default object name.
     * @param target the target object to bind onto (or <code>null</code>
     * if the binder is just used to convert a plain parameter value)
     * @param urlPattern the urlPattern
     * @see ServletRequestDataBinder#DEFAULT_OBJECT_NAME
     */
    public UrlHttpServletRequestDataBinder(Object target, String urlPattern) {
        super(target);
        this.urlPattern = urlPattern;
    }

    /**
     * Create a new UrlServletRequestDataBinder instance.
     * @param target the target object to bind onto (or <code>null</code>
     * if the binder is just used to convert a plain parameter value)
     * @param urlPattern the urlPattern
     * @param objectName the name of the target object
     */
    public UrlHttpServletRequestDataBinder(Object target, String urlPattern, String objectName) {
        super(target, objectName);
        this.urlPattern = urlPattern;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(ServletRequest request) {
        
        // we're picky
        if (!(request instanceof HttpServletRequest)) {
            throw new IllegalArgumentException(
                "UrlServletRequestDataBinder requires an HttpServletRequest");
        }
        
        // cast as http 
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        // get the url  and remove the context
        String url = httpRequest.getRequestURI();
        if (url.startsWith(httpRequest.getContextPath())) {
            url = url.substring(httpRequest.getContextPath().length());
        }
        
        // bind
        doBind(new UrlPropertyValues(this.urlPattern, url));
    }
    
}
