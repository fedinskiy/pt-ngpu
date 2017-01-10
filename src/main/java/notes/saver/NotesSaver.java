package notes.saver;

import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.NotesException;
import notes.annotations.IsNotesDocument;
import notes.annotations.IsNotesItem;
import notes.models.NotesDocumentInfo;
import notes.models.IsNotesItemInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by owlowl on 22.11.16.
 * класс выполняет сохранение данных из объектов классов, размеченных аннтоациями IsNotesDocument и IsNotesItem в документы в нотес
 */
public class NotesSaver {
  public void saveToNotesDocument(Document document, final Object toSave) throws NotesException, IllegalArgumentException, IllegalAccessException {
	NotesDocumentInfo docAnnotation = getDocumentAnnotation(toSave.getClass());
	boolean useSelectedFieldsOnly=!docAnnotation.useAllFields();

	for(Field field :toSave.getClass().getDeclaredFields()){
	  IsNotesItemInfo itemAnnotation;
	  try{
		field.get(toSave);
	  }catch(IllegalAccessException ex){
		/**
		 * пока что получаем данные только из публичных полей
		 * для непубличных надо использовать  setAccessible,
		 * для этого придется добавлять ReflectPermission в файл java.pol  на домино
		 * который дает доступ всему джава-коду в домене
		 * что, по-видиомому, не очень хоршая идея
		 */
		continue;
	  }
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
		Item item;
		switch (itemAnnotation.type())
		{
		  case DATE:
			DateTime dt= document.getParentDatabase().getParent().createDateTime((Date) field.get(toSave));
			item = document.replaceItemValue(itemAnnotation.name(),dt);
			break;
		  case NUMBER:
		  case TEXT:
		  default:
			item = document.replaceItemValue(itemAnnotation.name(), field.get(toSave));
			break;
		}
		item.recycle();
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
