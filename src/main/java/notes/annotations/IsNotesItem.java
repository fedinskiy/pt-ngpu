package notes.annotations;

import notes.base.ItemDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by owlowl on 22.11.16.
 * аннотация для поля или метода, значение которого будет помещено в поле NotesDocument'а
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD,ElementType.METHOD})
public @interface IsNotesItem {
	String name() default "";
	ItemDataType type() default ItemDataType.TEXT;
}
