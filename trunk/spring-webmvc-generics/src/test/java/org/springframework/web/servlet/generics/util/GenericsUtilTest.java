package org.springframework.web.servlet.generics.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GenericsUtilTest {

    @Test
    public void testGetTypeVariableClassByName() {
        assertEquals(Long.class, testTVCBN(TestGenericInterfaceImpl.class, TestGenericInterface.class, "A", true));
        assertEquals(Long.class, testTVCBN(TestGenericInterfaceImpl.class, "A", true));
        assertNull(testTVCBN(TestGenericInterfaceImpl.class, null, "XX", true));
        
        assertEquals(Long.class, testTVCBN(TestGenericInterface2Impl.class, TestGenericInterface2.class, "B", true));
        assertEquals(Integer.class, testTVCBN(TestGenericInterface2Impl.class, TestGenericInterface2.class, "C", true));
        assertEquals(Long.class, testTVCBN(TestGenericInterface2Impl.class, "B", true));
        assertEquals(Integer.class, testTVCBN(TestGenericInterface2Impl.class, "C", true));
        assertNull(testTVCBN(TestGenericAbstractClass2Impl.class, null, "XX", true));
        
        
        assertEquals(Long.class, testTVCBN(TestGenericAbstractClassImpl.class, TestGenericAbstractClass.class, "D", true));
        assertEquals(Long.class, testTVCBN(TestGenericAbstractClassImpl.class, "D", true));
        assertNull(testTVCBN(TestGenericAbstractClassImpl.class, null, "XX", true));
        
        assertEquals(Long.class, testTVCBN(TestGenericAbstractClass2Impl.class, TestGenericAbstractClass2.class, "E", true));
        assertEquals(Integer.class, testTVCBN(TestGenericAbstractClass2Impl.class, TestGenericAbstractClass2.class, "F", true));
        assertEquals(Long.class, testTVCBN(TestGenericAbstractClass2Impl.class, "E", true));
        assertEquals(Integer.class, testTVCBN(TestGenericAbstractClass2Impl.class, "F", true));
        assertNull(testTVCBN(TestGenericAbstractClass2Impl.class, null, "XX", true));
        
        
        assertEquals(String.class, testTVCBN(MindFuck.class, TestGenericAbstractClass.class, "D", true));
        assertEquals(Integer.class, testTVCBN(MindFuck.class, TestGenericInterface.class, "A", true));
        

        assertEquals(List.class, testTVCBN(Balls.class, TestGenericInterface.class, "A", true));
        assertEquals(List.class, testTVCBN(Balls.class, "A", true));
    }

    private static Class<?> testTVCBN(Class<?> clazz, Class<?> gclazz, String name, Boolean recursive) {
        return GenericsUtil.getTypeVariableClassByName(clazz, gclazz, name, recursive);
    }

    private static Class<?> testTVCBN(Class<?> clazz, String name, Boolean recursive) {
        return GenericsUtil.getTypeVariableClassByName(clazz, name, recursive);
    }
    
    
    
    interface TestGenericInterface<A> {
        
    }
    
    interface TestGenericInterface2<B, C> {
        
    }
    
    abstract class TestGenericAbstractClass<D> {
        
    }
    
    abstract class TestGenericAbstractClass2<E, F> {
        
    }
    
    
    
    
    class TestGenericInterfaceImpl
        implements TestGenericInterface<Long> {
        
    }
    
    class TestGenericInterface2Impl
        implements TestGenericInterface2<Long, Integer> {
        
    }
    
    
    
    
    class TestGenericAbstractClassImpl
        extends TestGenericAbstractClass<Long> {
        
    }
    
    class TestGenericAbstractClass2Impl
        extends TestGenericAbstractClass2<Long, Integer> {
        
    }
    
    
    
    
    class MindFuck 
        extends TestGenericAbstractClass<String>
        implements TestGenericInterface<Integer> {
        
    }
    
    class SuperMindFuck extends MindFuck {
        
    }
    
    class Balls implements TestGenericInterface<List<String>> {
        
    }
    
    
    
}
