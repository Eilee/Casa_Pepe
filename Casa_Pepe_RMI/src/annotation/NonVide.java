package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
// Annotation accessible �  l'execution

@Target(ElementType.FIELD)
// Annotation associée �  un champ

public @interface NonVide {

	String mess();
	
}
