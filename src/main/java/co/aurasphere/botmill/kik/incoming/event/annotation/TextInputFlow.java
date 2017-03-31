package co.aurasphere.botmill.kik.incoming.event.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TextInputFlow {
	
	String groupId() default "";
	String flowId() default "";
	String from() default "";
	String to() default "";
	String response() default "";
	
	boolean isStart() default false;
	boolean isEnd() default false;
	
}
