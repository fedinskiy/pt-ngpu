package notes.models;

import notes.annotations.IsNotesDocument;

import java.lang.annotation.Annotation;

/**
 * Created by owlowl on 24.11.16.
 */
public class NotesDocumentInfo implements Annotation,IsNotesDocument {
	private boolean useAllFields;
	
	public NotesDocumentInfo() {
		this.useAllFields = false;
	}
	public NotesDocumentInfo(IsNotesDocument annotation) {
		this.useAllFields =annotation.useAllFields();
	}
	public NotesDocumentInfo(Annotation annotation) {
		this((IsNotesDocument) annotation);
	}
	@Override
	public boolean useAllFields() {
		return useAllFields;
	}
	
	@Override
	public Class<? extends Annotation> annotationType() {
		return IsNotesDocument.class;
	}
	
	
}
