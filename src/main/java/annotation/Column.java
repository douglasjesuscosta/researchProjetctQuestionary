package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import sun.swing.DefaultLayoutStyle;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
	boolean pk() default false;
	boolean fk() default false;
	boolean obrigatory() default false;
	boolean searchable() default false;
	boolean autenticable() default false;
	String nameOnView();
	
}
