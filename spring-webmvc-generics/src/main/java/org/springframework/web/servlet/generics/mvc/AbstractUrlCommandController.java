package org.springframework.web.servlet.generics.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.generics.bind.UrlHttpServletRequestDataBinder;
import org.springframework.web.servlet.generics.util.GenericsUtil;

/**
 * <p>
 *  A command controller that uses a url pattern to
 *  bind values to a command object.  For more information
 *  on how to configure a url pattern for binding
 *  see {@link UrlPropertyValues}.
 * </p>
 *
 * @param <T>
 */
public abstract class AbstractUrlCommandController<T> 
    extends AbstractCommandController<T> {

    private String urlPattern;
    
    /**
     * Creates the BaseCommandController.
     */
    public AbstractUrlCommandController() {
        super.setCommandClass(GenericsUtil.getTypeVariableClassByName(
            this.getClass(), AbstractUrlCommandController.class, "T", true));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ServletRequestDataBinder createBinder(
        T command, HttpServletRequest request) 
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
