# Spring Web MVC Generics #

**Spring Web MVC Generics** is a generics friendly extension to the Spring WebMVC framework along with various other utilities found useful when working with spring web mvc.

# Latest Release #
I've given up on making new release jars every time something is changed.  Currently this library is in the beta stage (although we're using it in production and it's working great) and if you need a release you should checkout the source code and use maven to build a jar.

# Progress #
So far the following controllers have been made generic:
  * `BaseCommandController`
  * `AbstractCommandController`
  * `AbstractFormController`
  * `AbstractWizardFormController`
  * `SimpleFormController`

Remaining are:
  * `CancellableFormController`

# Controllers #
All of the generic controller names have the same exact class name as the controller that they are providing generics for with the exception that they exist in the `org.springframework.web.servlet.generics.mvc.*` package.  Method names are identical when possible with slight parameter reordering to allow for the generic methods to exist.  It should be completely painless and absolutely familiar to use the controllers in the generics package because they behave exactly like the controllers provided by the `org.springframework.web.servlet.mvc` package (in fact they inherit from them).

This allows for form controllers (or any of the `BaseCommandController` and descendants for that matter) to look something like this:

```

import org.springframework.web.servlet.mvc.generics.AbstractFormController;

public class AnInterestingFormController
    extends AbstractFormController<AnInterestingForm> {
    
    
    protected abstract ModelAndView processFormSubmission(
        AnInterestingForm command, BindException errors,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        // operate on command without casting it
        System.out.println(command.getSomethingInteresting());
        
    }
    
    
}

```

# Utilities #
TODO: document
We're looking for a place to host the maven site for spring-webmvc-generics, any volunteers?



---

| Springframework | [springframework.org](http://www.springframework.org/) |
|:----------------|:-------------------------------------------------------|
| Maven | [![](http://maven.apache.org/images/logos/maven-feather.png)](http://maven.apache.org/) |
