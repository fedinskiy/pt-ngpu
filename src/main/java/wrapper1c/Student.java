package wrapper1c;

import notes.annotations.IsNotesDocument;
import notes.annotations.IsNotesItem;
import notes.base.ItemDataType;

import java.lang.annotation.Annotation;
import java.util.Date;

/**
 * Created by owlowl on 22.11.16.
 */
@IsNotesDocument(useAllFields = true)
public class Student{
	@IsNotesItem(name = "FIO")
	private String name;
	@IsNotesItem(type = ItemDataType.DATE)
	private Date birthday;
	private String unimplemented;
	
	public Student(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
		unimplemented="wowwowwow";
	}
	
}
