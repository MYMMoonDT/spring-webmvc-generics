package org.springframework.web.servlet.generics.util;

import java.lang.reflect.TypeVariable;

import org.springframework.web.servlet.mvc.Controller;

/**
 * Utilities for working with generics in the spring framework.
 */
public abstract class GenericsUtil {
    
    /**
     * The type variable name for command classes.
     * Default is "CMD" and {@link #getCommandClassFromController(Controller)}
     * only works with controllers whos command class
     * type variable has this name.
     */
    public static final String COMMANDCLASS_TYPEVAR_NAME = "CMD";
    
    /**
     * Returns the command class for the given controller.
     * @param controller the controller
     * @return the command class
     */
    public static final Class<?> getCommandClassFromController(Controller controller) {
        
        // get the type parameters
        for (TypeVariable<?> typeVar : controller.getClass().getTypeParameters()) {
            if (typeVar.getName().equals(COMMANDCLASS_TYPEVAR_NAME)) {
                return typeVar.getClass();
            }
        }
        
        // none found
        return null;
    }
    
}
