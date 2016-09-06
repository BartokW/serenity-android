package us.nineworlds.plex.rest.model.impl;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by jonw on 03/10/15.
 */
@Root(name="User")
public class User {
	@Attribute(required=true)
	private String id;

	@Attribute(required=false)
	private int admin;

	@Attribute(required=false)
	private int guest;

	@Attribute(required=false)
	private int restricted;

	@Attribute(required=false, name = "protected")
	private int isProtected;

	@Attribute(required=false)
	private String title;

	@Attribute(required=false)
	private String username;

	@Attribute(required=false)
	private String email;

	@Attribute(required=false)
	private String thumb;

	public String getThumb() {
		return thumb;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getTitle() {
		return title;
	}

	public int getIsProtected() {
		return isProtected;
	}

	public int getRestricted() {
		return restricted;
	}

	public int getGuest() {
		return guest;
	}

	public int getAdmin() {
		return admin;
	}

	public String getId() {
		return id;
	}
}
