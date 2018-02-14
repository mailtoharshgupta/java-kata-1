package org.echocat.kata.java.part1.core.util;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

/**
 * @author Harsh Gupta on {2/14/18}
 */
public class KataUtil {

    /**
     * Warning : This method throws a Throwable object in case of an exception.
     * Sneaky throw avoid the explicit catching of exception at the callee.
     * Callee should be aware that both the classes should have a no args constructor.
     * Wrapper around BeanUtils class. Eradicates the need of creating an object from the callee.
     *
     * @param source
     * @param toClazz
     * @param ignoreProperties : List of properties to be ignore during conversion
     * @return {@link Object} of toClass
     */
    @SneakyThrows
    public static Object convert(Object source, Class toClazz, String... ignoreProperties) {
        Object toObject = toClazz.newInstance();
        BeanUtils.copyProperties(source, toObject, ignoreProperties);
        return toObject;
    }
}
