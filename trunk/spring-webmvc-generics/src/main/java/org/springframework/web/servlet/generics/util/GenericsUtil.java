package org.springframework.web.servlet.generics.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Utilities for working with generic types.
 */
public abstract class GenericsUtil {
    
    /**
     * Returns the class defined for the type variable
     * of the given name. 
     * @param clazz the class
     * @param name the name of the type variable
     * @param recursive whether or not to recurse up the
     * object's inheritance hierarchy.
     * @return the class
     */
    public static final Class<?> getTypeVariableClassByName(
        Class<?> clazz, String name, Boolean recursive) {
        
        // we hit the end of the line here :)
        if (clazz==null || clazz.equals(Object.class)) {
            return null;
        }
        
        // get superclass type
        Type type = clazz.getGenericSuperclass();
        
        // check to see if it is a ParameterizedType
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            
            // get super class type variables
            TypeVariable<?>[] typeVars = clazz.getSuperclass().getTypeParameters();
            for (int i=0; i<typeVars.length; i++) {
                if (typeVars[i].getName().equals(name)) {
                    return (Class<?>)pType.getActualTypeArguments()[i];
                }
            }
        }
        
        // none found
        return (recursive) 
            ? getTypeVariableClassByName(clazz.getSuperclass(), name, recursive) : null;
    }
    
}
