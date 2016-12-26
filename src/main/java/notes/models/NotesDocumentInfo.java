package notes.models;

import notes.annotations.IsNotesDocument;

import java.lang.annotation.Annotation;

/**
 * Created by owlowl on 24.11.16.
 */
public class NotesDocumentInfo implements Annotation,IsNotesDocument {
	private boolean useAllFiles;
	
	public NotesDocumentInfo() {
		this.useAllFiles = false;
	}
	public NotesDocumentInfo(IsNotesDocument annotation) {
		this.useAllFiles=annotation.useAllFields();
	}
	public NotesDocumentInfo(Annotation annotation) {
		this((IsNotesDocument) annotation);
	}
	@Override
	public boolean useAllFields() {
		return useAllFiles;
	}
	
	@Override
	public Class<? extends Annotation> annotationType() {
		return IsNotesDocument.class;
	}
	
	
}
