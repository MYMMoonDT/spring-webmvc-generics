package org.springframework.web.servlet.generics.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.generics.bind.UrlHttpServletRequestDataBinder;

/**
 * <p>
 *  A command controller that uses a url pattern to
 *  bind values to a command object.  For more information
 *  on how to configure a url pattern for binding
 *  see {@link UrlPropertyValues}.
 * </p>
 *
 * @param <CMD>
 */
public abstract class AbstractUrlCommandController<CMD> 
    extends AbstractCommandController<CMD> {

    private String urlPattern;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ServletRequestDataBinder createBinder(
        CMD command, HttpServletRequest request) 
        throws Exception {
        ServletRequestDataBinder binder = new UrlHttpServletRequestDataBinder(
            command, urlPattern, getCommandName());
        prepareBinder(binder);
        initBinder(request, binder);
        return binder;
    }

    /**
     * {@see UrlPropertyValues}.
     * @param urlPattern the urlPattern to set
     */
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
    
}
