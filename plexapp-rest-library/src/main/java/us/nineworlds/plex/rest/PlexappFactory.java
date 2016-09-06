/**
 * The MIT License (MIT)
 * Copyright (c) 2012 David Carver
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
 * OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package us.nineworlds.plex.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import us.nineworlds.plex.rest.config.IConfiguration;
import us.nineworlds.plex.rest.model.impl.*;


/**
 * This class acts as a factory for retrieving items from Plex.
 * 
 * This is a singleton so only one of these will ever exist currently.
 * 
 * @author dcarver
 * 
 */
public class PlexappFactory {

	private static PlexappFactory instance = null;

	private ResourcePaths resourcePath = null;
	private Serializer serializer = null;
	private IConfiguration config = null;

	private HashMap<Integer, String> authTokens;
	private boolean areTokensLoaded = false;

	private PlexappFactory(IConfiguration config) {
		authTokens = new HashMap<Integer, String>();
		resourcePath = new ResourcePaths(config);
		serializer = new Persister();
		this.config = config;
	}

	public static PlexappFactory getInstance(IConfiguration config) {
		if (instance == null) {
			instance = new PlexappFactory(config);
		}
		return instance;
	}

	public static void freeInstance(){
		if (instance != null)
			instance = null;
	}

	/**
	 * Retrieve the root metadata from the Plex Media Server.
	 * 
	 * @return 
	 * @throws Exception
	 */
	public MediaContainer retrieveRootData() throws Exception {
		String rootURL = resourcePath.getRoot();
		MediaContainer mediaContainer = serializeResource(rootURL);

		return mediaContainer;
	}

	/**
	 * This retrieves the available libraries.  This can include such
	 * things as Movies, and TV shows.
	 * 
	 * @return MediaContainer the media container for the library
	 * @throws Exception
	 */
	public MediaContainer retrieveLibrary() throws Exception {
		String libraryURL = resourcePath.getLibraryURL();
		MediaContainer mediaContainer = serializeResource(libraryURL);

		return mediaContainer;
	}
	
	/**
	 * This retrieves the available libraries.  This can include such
	 * things as Movies, and TV shows.
	 * 
	 * @return MediaContainer the media container for the library
	 * @throws Exception
	 */
	public MediaContainer retrieveSections() throws Exception {
		String sectionsURL = resourcePath.getSectionsURL();
		MediaContainer mediaContainer = serializeResource(sectionsURL);

		return mediaContainer;
	}
	
	/**
	 * This retrieves the available libraries.  This can include such
	 * things as Movies, and TV shows.
	 * 
	 * @return MediaContainer the media container for the library
	 * @param key the section key
	 * @throws Exception
	 */
	public MediaContainer retrieveSections(String key) throws Exception {
		String sectionsURL = resourcePath.getSectionsURL(key);
		MediaContainer mediaContainer = serializeResource(sectionsURL);

		return mediaContainer;
	}
	
	/**
	 * For Movies this will return a MediaContainer with Videos.  For
	 * TV Shows this will return a MediaContainer with Directories.
	 * 
	 * @param key
	 * @param category
	 * @return MediaContainer
	 * @throws Exception
	 */
	public MediaContainer retrieveSections(String key, String category) throws Exception {
		String moviesURL = resourcePath.getSectionsURL(key, category);
		MediaContainer mediaContainer = serializeResource(moviesURL);
		return mediaContainer;
	}
	
	public MediaContainer retrieveSections(String key, String category, String secondaryCategory) throws Exception {
		String moviesURL = resourcePath.getSectionsURL(key, category, secondaryCategory);
		MediaContainer mediaContainer = serializeResource(moviesURL);
		return mediaContainer;
	}
	
	
	public MediaContainer retrieveSeasons(String key) throws Exception {
		String seasonsURL = resourcePath.getSeasonsURL(key);
		MediaContainer mediaContainer = serializeResource(seasonsURL);
		return mediaContainer;
	}
	
	public MediaContainer retrieveMusicMetaData(String key) throws Exception {
		String seasonsURL = resourcePath.getSeasonsURL(key);
		MediaContainer mediaContainer = serializeResource(seasonsURL);
		return mediaContainer;
	}
	
	
	public MediaContainer retrieveEpisodes(String key) throws Exception {
		String episodesURL = resourcePath.getEpisodesURL(key);
		MediaContainer mediaContainer = serializeResource(episodesURL);
		return mediaContainer;
	}
	
	public MediaContainer retrieveMovieMetaData(String key) throws Exception {
		String episodesURL = resourcePath.getMovieMetaDataURL(key);
		MediaContainer mediaContainer = serializeResource(episodesURL);
		return mediaContainer;
	}
		
	public MediaContainer searchMovies(String key, String query) throws Exception {
		String searchURL = resourcePath.getMovieSearchURL(key, query);
		MediaContainer mediaContainer = serializeResource(searchURL);
		return mediaContainer;
	}
	
	public MediaContainer searchEpisodes(String key, String query) throws Exception {
		String searchURL = resourcePath.getEpisodeSearchURL(key, query);
		MediaContainer mediaContainer = serializeResource(searchURL);
		return mediaContainer;
	}
	
	public String baseURL() {
		return resourcePath.getRoot();
	}

	/**
	 * Sets a video as watched. viewCount will be 1.
	 * @param key
	 * @return
	 */
	public boolean setWatched(String key) {
		String resourceURL = resourcePath.getWatchedUrl(key);
		return requestSuccessful(resourceURL);
	}
	
	/**
	 * Sets a vide as unwatched. viewCount will not be present.
	 * 
	 * @param key
	 * @return
	 */
	public boolean setUnWatched(String key) {
		String resourceURL = resourcePath.getUnwatchedUrl(key);
		return requestSuccessful(resourceURL);
	}
	
	public boolean setProgress(String key, String offset) {
		String resourceURL = resourcePath.getProgressUrl(key, offset);
		return requestSuccessful(resourceURL);
	}
	

	/**
	 * @param resourceURL
	 * @param con
	 * @return
	 */
	protected boolean requestSuccessful(String resourceURL) {
		HttpURLConnection con = null;
		try {
			URL url = new URL(resourceURL);
			con = (HttpURLConnection) url.openConnection();
			con.setDefaultUseCaches(false);
			int responseCode = con.getResponseCode();
			if (responseCode == 200) {
				return true;
			}
		} catch (Exception ex) {
			return false;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return false;
	}
	
	public String getProgressURL(String key, String offset) {
		return resourcePath.getProgressUrl(key, offset);
	}
	
	public String getMovieSearchURL(String key, String query) {
		return resourcePath.getMovieSearchURL(key, query);
	}
	
	public String getTVShowSearchURL(String key, String query) {
		return resourcePath.getMovieSearchURL(key, query);
	}

	public String getEpisodeSearchURL(String key, String query) {
		return resourcePath.getMovieSearchURL(key, query);
	}
	
	public String getMediaTagURL(String resourceType, String resourceName, String identifier) {
		return resourcePath.getMediaTagURL(resourceType, resourceName, identifier);
	}
	
	public String getSectionsURL(String key, String category) {
		return resourcePath.getSectionsURL(key, category);
	}
	
	public String getSectionsURL() {
		return resourcePath.getSectionsURL();
	}
	
	public String getSectionsUrl(String key) {
		return resourcePath.getSectionsURL(key);
	}
	
	public String getMovieMetadataURL(String key) {
		return resourcePath.getMovieMetaDataURL(key);
	}
	
	public String getEpisodesURL(String key) {
		return resourcePath.getEpisodesURL(key);
	}
	
	public String getSeasonsURL(String key) {
		return resourcePath.getSeasonsURL(key);
	}

    public String getImageURL(String url, int width, int height) {
        return resourcePath.getImageURL(url, width, height);
    }

    /**
	 * Given a resource's URL, read and return the serialized MediaContainer
	 * @param resourceURL
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws Exception
	 */
    protected Request generateRequest(String url, boolean get){
	    Request r = null;
	    if (get)
		    r = Request.Get(url);
	    else
		    r = Request.Post(url);
	    return r
			    .addHeader("X-Plex-Client-Identifier", config.getClientIdentifier())
			    .addHeader("X-Plex-Product", config.getProduct())
			    .addHeader("X-Plex-Version", config.getProductVersion())
			    .addHeader("X-Plex-Device", config.getDevice())
			    .addHeader("X-Plex-Device-Name", config.getDeviceName())
			    .addHeader("Cache-Control", "max-age=0");
    }

	protected Request generateRequest(String url) {
		return generateRequest(url, true);
	}

	public String getPlexMasterAuthenticationToken(String username, String password){
		try {
			InputStream stream = generateRequest(resourcePath.getPlexPassLoginURL(), false)
					.bodyForm(Form.form().add("user[login]", username).add("user[password]", password).build())
					.execute().returnContent().asStream();
			//Get the auth token
			Serializer serializer = new Persister();
			PlexUser user = serializer.read(PlexUser.class, stream, false);
			//Add the auth token for the user id to a list.
			config.setPlexAuthenticationToken(user.getAuthenticationToken());
			this.authTokens.put(user.getId(), user.getAuthenticationToken());
			return config.getPlexAuthenticationToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getPlexMasterAuthenticationToken() {
		return getPlexMasterAuthenticationToken(config.getUsername(), config.getPassword());
	}

	public MediaContainer getPlexHomeUsers() throws Exception{
		String userListURL = resourcePath.getPlexHomeUserListURL();
		//System.out.println(userListURL);
		MediaContainer mediaContainer = serializeResource(userListURL);
		return mediaContainer;
	}

	public PlexUser switchPlexHomeUser(String id) throws Exception{
		return switchPlexHomeUser(id, "");
	}

	public PlexUser switchPlexHomeUser(String id, String pin) throws Exception{
		try {
			//TODO this appears to validate the pin, returning a 401 Unauthorized for invalid pin
			//TODO The new Auth Token returned, doesn't appear to work for connecting to the server.
			//TODO Need to figure out how to get the correct auth token.
			//
			InputStream stream = generateRequest(resourcePath.getPlexHomeSwitchUserURL(id, pin), false)
					.addHeader("X-Plex-Token", config.getPlexAuthenticationToken())
					.execute().returnContent().asStream();
			Serializer serializer = new Persister();
			PlexUser user = serializer.read(PlexUser.class, stream, false);

			getPlexHomeUserAuthTokens();

			user.setAuthenticationToken(this.authTokens.get(user.getId()));
			config.setPlexAuthenticationToken(user.getAuthenticationToken());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PlexUser switchPlexHomeUser(User user) throws Exception{
		return switchPlexHomeUser(user.getId());
	}

	public PlexUser switchPlexHomeUser(User user, String pin) throws Exception{
		return switchPlexHomeUser(user.getId(), pin);
	}

	protected void getPlexHomeUserAuthTokens(){
		if (areTokensLoaded)
			return;
		String url = resourcePath.getPlexHomeUserTokensURL(getMachineId(), config.getPlexAuthenticationToken());
		try {
			InputStream stream = generateRequest(url)
					.execute().returnContent().asStream();
			Serializer serializer = new Persister();
			AccessTokens accesstokens = serializer.read(AccessTokens.class, stream, false);
			List<AccessToken> tokens = accesstokens.getTokens();
			for (AccessToken token:	tokens) {
				if (token.getId() != 1){
					this.authTokens.put(token.getId(), token.getToken());
				}
			}
			areTokensLoaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getMachineId(){
		try{
			MediaContainer mc = serializeResource(resourcePath.getMachineIdentityURL());
			if (mc != null){
				return mc.getMachineIdentifier();
			}
		}catch (Exception e){

		}
		return "";
	}

	/**
	 * Given a resource's URL, read and return the serialized MediaContainer
	 * @param resourceURL
	 * @param get Is this a get request or post
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws Exception
	 */
	private MediaContainer serializeResource(String resourceURL, boolean get)
			throws MalformedURLException, IOException, Exception {
		System.out.println(resourceURL);
		Request request = generateRequest(resourceURL, get);
		if (!config.getPlexAuthenticationToken().isEmpty()){
			//If we have an auth token, make sure to use it
			request = request.addHeader("X-Plex-Token", config.getPlexAuthenticationToken());
			System.out.println("Token: " + config.getPlexAuthenticationToken());
		}

		MediaContainer mediaContainer;
		mediaContainer = serializer.read(MediaContainer.class,
				request.execute().returnContent().asStream(), false);

		return mediaContainer;
	}
	private MediaContainer serializeResource(String resourceURL)
			throws MalformedURLException, IOException, Exception{
		return serializeResource(resourceURL, true);
	}

	public MediaContainer serializeResourceFromString(String xmlString) throws Exception {
		MediaContainer container = serializer.read(MediaContainer.class, xmlString, false);
		return container;
	}


}
