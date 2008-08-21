package org.springframework.web.servlet.generics.bind;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;

/**
 * <p>
 *  A {@link MutablePropertyValues} that uses a url pattern
 *  to parse parameter name and value pairs from a url.
 *  For instance, take the following URL:
 * 
 *  <pre>
 *      /person/123/Charles
 *  </pre>
 * 
 *  and the following command object:
 * 
 *  <pre>
 *      class PersonCommand {
 *          private Long id;
 *          private String name;
 *          public void setId(Long id) { this.id = id; }
 *          public Long getId() { return this.id; }
 *          public void setName(String name) { this.name = name; }
 *          public Long getName() { return this.name; }
 *      }
 *  </pre>
 * 
 *  one could use the following url pattern to bind
 *  values to the PersonCommand:
 * 
 *  <pre>
 *      /notused/id/name
 *  </pre>
 * </p>
 * 
 * <p>
 *  Upon accessing the url mentioned earlier, the PersonCommand
 *  object would have an id of <code>123L</code> and a name
 *  of <code>"name"</code>.  For parts of a url that shouldn't
 *  be mapped to a command object any value can be used, it is
 *  probably a good idea to use "notused" so that it's obvious
 *  to other people looking at the code.
 * </p>
 * 
 * <p>
 *  The following sample configuration shows how to use a
 *  subclass of {@link AbstractUrlCommandController} to
 *  bind values from the url metnioned earlier to a 
 *  command class:
 *  
 *  <pre>
 *      <code>
 *      &lt;!-- redirector --&gt;
 *      &lt;bean id="personViewController" 
 *          class="com.example.controller.PersonViewController"&gt;
 *          &lt;property name="urlPattern"      value="/notused/id/name" /&gt;
 *          &lt;property name="commandClass"    value="com.example.form.PersonCommand" /&gt;
 *      &lt;/bean&gt;
 *      </code>
 *  </pre>
 *  
 * </p>
 */
@SuppressWarnings("serial")
public class UrlPropertyValues 
    extends MutablePropertyValues {
    
    /**
     * Create new UrlPropertyValues using no prefix
     * (and hence, no prefix separator).
     * @param urlPattern the url pattern
     * @param url the url
     */
    public UrlPropertyValues(String urlPattern, String url) {
        this(urlPattern,  url, null);
    }

    /**
     * Create new UrlPropertyValues using the given prefix and
     * the default prefix separator (the underscore character "_").
     * @param urlPattern the url pattern
     * @param url the url
     * @param prefix the prefix for parameters (the full prefix will
     * consist of this plus the separator)
     * @see UrlPropertyValues#DEFAULT_PREFIX_SEPARATOR
     */
    public UrlPropertyValues(
        String urlPattern, String url, String prefix) {
        this(urlPattern, url, prefix,
            ServletRequestParameterPropertyValues.DEFAULT_PREFIX_SEPARATOR);
    }

    /**
     * Create new UrlPropertyValues supplying both prefix and
     * prefix separator.
     * @param urlPattern the url pattern
     * @param url the url
     * @param prefix the prefix for parameters (the full prefix will
     * consist of this plus the separator)
     * @param prefixSeparator separator delimiting prefix (e.g. "spring")
     * and the rest of the parameter name ("param1", "param2")
     */
    public UrlPropertyValues(
        String urlPattern, String url, String prefix, String prefixSeparator) {
        super(parseParameters(urlPattern, url, prefix, prefixSeparator));
    }
    
    /**
     * Parses the given url using the given pattern removing
     * the given prefix and prefix separator from the begining
     * of any paramter names returning a map of name->value pairs.
     * @param urlPattern the url pattern
     * @param url the url to parse
     * @param prefix the param name prefix
     * @param prefixSeparator the prefix separator
     * @return a map of parameters
     */
    private static Map<String, String> parseParameters(
        String urlPattern, String url, String prefix, String prefixSeparator) {
        
        // get the parameter names and values
        String[] paramNames     = urlPattern.split("/");
        String[] paramValues    = url.split("/");
        
        // get the prefix
        prefix = (prefix != null) ? prefix + prefixSeparator : "";
        
        // build a map of the values
        Map<String, String> params = new HashMap<String, String>();
        for (int i=0; i<paramNames.length && i<paramValues.length; i++) {
            if (prefix.equals("") || paramNames[i].startsWith(prefix)) {
                params.put(paramNames[i].substring(prefix.length()), paramValues[i]);
            }
        }
        
        // return map
        return params;
    }
    
}
