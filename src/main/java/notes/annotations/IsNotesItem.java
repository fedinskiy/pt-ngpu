package notes.annotations;

import notes.base.ItemDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by owlowl on 22.11.16.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface IsNotesItem {
	String name() default "";
	ItemDataType type() default ItemDataType.TEXT;
}
