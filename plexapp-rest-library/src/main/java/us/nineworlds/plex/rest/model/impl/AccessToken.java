package us.nineworlds.plex.rest.model.impl;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by jonw on 2015-12-13.
 */
@Root(name="access_token")
public class AccessToken {
    @Attribute(required=true)
    private int id;

    @Attribute(required=false)
    private String device;

    @Attribute(required = true)
    private String token;

    @Attribute(required = true)
    private int owned;

    @Attribute(required = true)
    private String username;

    @Attribute(required = true)
    private String title;

    public int getId() {
        return id;
    }

    public String getDevice() {
        return device;
    }

    public String getToken() {
        return token;
    }

    public int getOwned() {
        return owned;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }
}
