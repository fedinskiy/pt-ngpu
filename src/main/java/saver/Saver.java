package saver;

import lotus.domino.*;
import notes.saver.NotesSaver;
import wrapper1c.Student;


import java.util.Date;

/**
 * Created by owlowl on 22.11.16.
 * пример использования сериализатора
 */
public class Saver {

  private static lotus.domino.Session ns;
  private static Database db;

  public static void main(final String[] args) throws IllegalAccessException, NotesException {
	try {
	  Student student= new Student("Vasiliy Nim", new Date(System.currentTimeMillis()));
	  NotesSaver saver = new NotesSaver();
	  Document targetDoc = null;
	  targetDoc=createTestDocument(db);
	  saver.saveToNotesDocument(targetDoc, student);
	  targetDoc.save();
	} finally {
	  clear();
	}
  }
  public static void test(Session ns, Database db) throws IllegalAccessException, NotesException {
	try {
	  Student student= new Student("Vasiliy Nim", new Date(System.currentTimeMillis()));
	  NotesSaver saver = new NotesSaver();
	  Document targetDoc = null;
	  targetDoc=createTestDocument(db);
	  saver.saveToNotesDocument(targetDoc, student);
	  targetDoc.save();
	} finally {
	}
  }

  private static void clear() throws NotesException {
	if (null!=db) db.recycle();
	if (null!=ns) ns.recycle();
  }
  private static Document createTestDocument(Database db) throws NotesException {
	Document retval;
	System.out.println(db.getReplicaID());
	retval = db.createDocument();
	return retval;
  }
}

