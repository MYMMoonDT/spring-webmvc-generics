package org.springframework.web.servlet.generics.view;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * <p>
 *  A view that does token replacement on a url
 *  and performs a redirect to said url. This view
 *  is useful in conjuction with controllers that
 *  want to perform POST-REDIRECT-GET functionality.
 *  URLS are preprocessed and string token replacement
 *  is performed on the url using the model keys as
 *  tokens and replacing the tokens with the model
 *  value for the given key.
 * </p>
 */
public class TokenReplacementRedirectView 
    extends AbstractUrlBasedView {

    private int httpStatusCode = HttpServletResponse.SC_SEE_OTHER;
    private boolean contextRelative = true;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(
        Map model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        // build the url
        StringBuilder buff = new StringBuilder();
        
        // append context path
        if (this.contextRelative 
            && getUrl().startsWith("/") 
            && !getUrl().startsWith(request.getContextPath())) {
            buff.append(request.getContextPath());
            
        // remove context path
        } else if (!this.contextRelative
            && getUrl().startsWith(request.getContextPath())) {
            buff.replace(0, request.getContextPath().length(), "");
        }
        
        // append the URL
        buff.append(getUrl());
        
        // the url we're redirecting to
        String url = buff.toString();
        
        // loop through and do token replacement
        for (Object key : model.keySet()) {
            String name = "\\{"+key.toString()+"\\}";
            String val = model.get(key).toString();
            url = url.replaceAll(name, URLEncoder.encode(val, "UTF-8"));
        }
        
        // send the redirect
        response.setStatus(httpStatusCode);
        response.setHeader("Location", url);
    }

    /**
     * Sets the HTTP status code to use for the redirect.  The
     * default is {@link HttpServletResponse#SC_SEE_OTHER}
     * (303).  One should be careful when setting this because
     * not all HTTP status codes are for redirecting.
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * Sets whether redirects are context relative or
     * not.  By default this is set to true and only
     * urls that start with a forward slash ("/") are
     * redirected in a context relative manner.
     * @param contextRelative the contextRelative to set
     */
    public void setContextRelative(boolean contextRelative) {
        this.contextRelative = contextRelative;
    }
    
}
