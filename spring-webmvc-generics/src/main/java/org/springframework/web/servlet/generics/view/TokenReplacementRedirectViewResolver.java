package org.springframework.web.servlet.generics.view;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * <p>
 *  A {@link ViewResolver} that resolves {@link TokenReplacementRedirectView}s.
 * </p>
 * 
 * <p>
 *  The folowing sample configuration would allow for views
 *  prefixed with "sendRedirect:" to be resolved to
 *  {@link TokenReplacementRedirectView}s using an HTTP
 *  status code of 303 and ensuring that the url being
 *  redirected to is context relative (if it starts with "/").
 *  <pre>
 *      <code>
 *      &lt;!-- redirector --&gt;
 *      &lt;bean id="tokenReplacementRedirectViewResolver" 
 *          class="org.springframework.web.servlet.generics.view.TokenReplacementRedirectViewResolver"&gt;
 *          &lt;property name="order"              value="1" /&gt;
 *          &lt;property name="prefix"             value="sendRedirect:" /&gt;
 *          &lt;property name="httpStatusCode"     value="303" /&gt;
 *          &lt;property name="contextRelative"    value="true" /&gt;
 *      &lt;/bean&gt;
 *      </code>
 *  </pre>
 * </p>
 * 
 */
public class TokenReplacementRedirectViewResolver 
    implements ViewResolver,
    Ordered {

    private String prefix           = "sendRedirect:";
    private int httpStatusCode      = HttpServletResponse.SC_SEE_OTHER;
    private boolean contextRelative = true;
    private int order               = 0;
    
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

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }
    
}
