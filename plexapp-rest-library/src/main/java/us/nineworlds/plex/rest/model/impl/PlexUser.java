package us.nineworlds.plex.rest.model.impl;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="User")
public class PlexUser {
	@Attribute(required=true)
	private int id;

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

	@Element(name = "authentication-token")
	private String authenticationToken;

	@Element(name = "username", required = false)
	private String username2;

	@Element(name = "email", required = false)
	private String email2;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String token){
		this.authenticationToken = token;
	}

	public int getId(){
		return id;
	}
}

