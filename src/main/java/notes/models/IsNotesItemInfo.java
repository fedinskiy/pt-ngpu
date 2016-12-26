package notes.models;

import notes.annotations.IsNotesItem;
import notes.base.ItemDataType;

import java.lang.annotation.Annotation;

/**
 * Created by owlowl on 22.11.16.
 */
public class IsNotesItemInfo implements Annotation,IsNotesItem {
	private String name;
	private ItemDataType type;
	
	public IsNotesItemInfo() {
		this.name = "";
		this.type = ItemDataType.TEXT;
	}
	
	public IsNotesItemInfo(IsNotesItem item) {
		this.name = item.name();
		this.type = item.type();
	}
	
	public IsNotesItemInfo(Annotation annotation) {
		this((IsNotesItem) annotation);
	}
	
	public IsNotesItemInfo withName(String newName){
		this.name = newName;
		return this;
	}
	@Override
	public String name() {
		return name;
	}
	
	@Override
	public ItemDataType type() {
		return type;
	}
	
	@Override
	public String toString() {
		return "NotesItemImp{" +
				"name='" + name + '\'' +
				", type=" + type +
				'}';
	}
	
	@Override
	public Class<? extends Annotation> annotationType() {
		return IsNotesItem.class;
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		IsNotesItemInfo that = (IsNotesItemInfo) o;
		
		if (!name.equals(that.name)) return false;
		return type == that.type;
		
	}
	
	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + type.hashCode();
		return result;
	}
}
