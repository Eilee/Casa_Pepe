package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// Annotation accessible Ã  l'execution

@Target(ElementType.TYPE)
// Annotation associÃ©e Ã  une classe



public @interface Table {

	String name();

}
