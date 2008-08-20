package org.springframework.web.servlet.view.generics;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * A {@link ViewResolver} that resolves 
 * {@link TokenReplacementRedirectView}s.
 */
public class TokenReplacementRedirectViewResolver 
    implements ViewResolver {

    private String prefix           = "sendRedirect:";
    private int httpStatusCode      = HttpServletResponse.SC_SEE_OTHER;
    private boolean contextRelative = true;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
        throws Exception {
        
        // bail early if possible
        if (!viewName.startsWith(prefix)) {
            return null;
        }
        
        // create the view
        TokenReplacementRedirectView view = new TokenReplacementRedirectView();
        view.setContextRelative(contextRelative);
        view.setHttpStatusCode(httpStatusCode);
        view.setUrl(viewName.substring(prefix.length()));
        
        // return it
        return view;
    }

    /**
     * Sets the prefix that this {@link ViewResolver}
     * uses to determine whether or not a given view
     * is supposed to be a redirect.  By default the
     * prefix is <code>sendRedirect:</code>, ie:
     * <pre>
     *  sendRedirect:/some/other/controller.html
     * </pre>
     * or: 
     * <pre>
     *  sendRedirect:http://www.example.com/
     * </pre>
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * {@see TokenReplacementRedirectView#setHttpStatusCode(int)}.
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * {@see TokenReplacementRedirectView#setContextRelative(boolean)}.
     * @param contextRelative the contextRelative to set
     */
    public void setContextRelative(boolean contextRelative) {
        this.contextRelative = contextRelative;
    }

}
