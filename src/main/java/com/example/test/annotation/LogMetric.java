package com.example.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The java.lang.annotation.ElementType enum declares many constants to specify the type of element
 * where annotation is to be applied such as TYPE, METHOD, FIELD etc. Let's see the constants of ElementType enum:
 *
 * @Retention annotation is used to specify to what level annotation will be available.
 *
 * RetentionPolicy	Availability
 * RetentionPolicy.SOURCE	refers to the source code, discarded during compilation. It will not be available in the compiled class.
 * RetentionPolicy.CLASS	refers to the .class file, available to java compiler but not to JVM . It is included in the class file.
 * RetentionPolicy.RUNTIME	refers to the runtime, available to java compiler and JVM .
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMetric {
}
