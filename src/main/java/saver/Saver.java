package saver;

import notes.saver.NotesSaver;
import wrapper1c.Student;

import java.util.Date;

/**
 * Created by owlowl on 22.11.16.
 */
public class Saver {
	public static void main(final String[] args) throws IllegalAccessException {
		Student student= new Student("Vasiliy Nim", new Date(System.currentTimeMillis()));
		NotesSaver saver = new NotesSaver();
		saver.saveToNotesDocument(student);
	}
}
