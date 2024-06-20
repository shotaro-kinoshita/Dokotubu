package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String text;
	
	public Mutter() {}
	
	public Mutter(int id, String userName,String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}
	public Mutter(String userName,String text) {
		this.userName = userName;
		this.text = text;
	}
	public String getuserName() { return userName; }
	public String getText() { return text; }

	public int getId() {
		return id;
	}
	
}