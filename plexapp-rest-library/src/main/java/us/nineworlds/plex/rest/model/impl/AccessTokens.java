package us.nineworlds.plex.rest.model.impl;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by jonw on 2015-12-13.
 */
@Root(name="access_tokens")
public class AccessTokens {
    @ElementList(inline=true, required = false)
    private List<AccessToken> tokens;

    public List<AccessToken> getTokens() {
        return tokens;
    }
}
