package notes.saver;

import notes.annotations.IsNotesDocument;
import notes.annotations.IsNotesItem;
import notes.models.NotesDocumentInfo;
import notes.models.IsNotesItemInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * Created by owlowl on 22.11.16.
 */
public class NotesSaver {
	public void saveToNotesDocument(final Object toSave) throws IllegalAccessException {
		NotesDocumentInfo docAnnotation = getDocumentAnnotation(toSave.getClass());
		boolean useSelectedFieldsOnly=!docAnnotation.useAllFields();
		
		for(Field field :toSave.getClass().getDeclaredFields()){
			IsNotesItemInfo itemAnnotation;
			field.setAccessible(true);
			try {
				itemAnnotation = getItemAnnotation(field, !useSelectedFieldsOnly);
				if (null!=itemAnnotation&&itemAnnotation.name().isEmpty()){
					itemAnnotation.withName(field.getName());
				}
			} catch(IllegalArgumentException e){
				itemAnnotation=null;
				//throw new IllegalArgumentException("Error in field "+field.getName()+"of class "+toSave.getClass().getName(),e);
				
			}
			if (!(null==itemAnnotation)) {
				System.out.println("Notes item " + itemAnnotation.name() + " of type " + itemAnnotation.type()
						+ " contains value " + field.get(toSave));
			}
		}
	}
	
	private IsNotesItemInfo getItemAnnotation(AnnotatedElement toSave, boolean createOnAbsence) throws IllegalArgumentException {
		Annotation[] declaredAnnotations = toSave.getDeclaredAnnotations();
		IsNotesItemInfo itemAnnotation=null;
		final Class AnnotationType = IsNotesItem.class;
		for(Annotation annotation:declaredAnnotations){
			if (annotation.annotationType().equals(AnnotationType)){
				itemAnnotation= new IsNotesItemInfo(annotation);
			}
		}
		if (null == itemAnnotation) {
			if (createOnAbsence) {
				itemAnnotation = new IsNotesItemInfo();
			}else {
				throw new IllegalArgumentException("Class "+toSave.getClass().getName()+" do not contains "+AnnotationType.getSimpleName()+" annotation");
			}
		}
		return itemAnnotation;
	}
	private NotesDocumentInfo getDocumentAnnotation(AnnotatedElement toSave) {
		Annotation[] declaredAnnotations = toSave.getDeclaredAnnotations();
		final Class AnnotationType = IsNotesDocument.class;
		for(Annotation annotation:declaredAnnotations){
			if (annotation.annotationType().equals(AnnotationType)){
				return new NotesDocumentInfo(annotation);
				
			}
		}
		throw new IllegalArgumentException("Class "+toSave.getClass().getName()+" do not contains "+AnnotationType.getSimpleName()+" annotation");
	}
}
