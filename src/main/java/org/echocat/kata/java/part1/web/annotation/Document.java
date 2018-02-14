package org.echocat.kata.java.part1.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Harsh Gupta on {2/14/18}
 */

@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface Document {
}
