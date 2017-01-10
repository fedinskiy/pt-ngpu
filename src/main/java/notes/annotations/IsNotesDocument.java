package notes.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by owlowl on 22.11.16.
 * аннотация для класса, который будет транслирован в NotesDocument
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IsNotesDocument {
  /**
   * использовать ли все поля из документа или только те, которые указаны явно
   */
	public boolean useAllFields() default false;
}
