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

package us.nineworlds.plex.rest.config.impl;

import us.nineworlds.plex.rest.config.IConfiguration;

import java.util.UUID;

/**
 * @author dcarver
 *
 */
public class Configuration implements IConfiguration {
	
	private String host;
	
	private String port;

	private String username;
	private String password;
	private String clientIdentifier;
	private String product;
	private String productVersion;
	private String deviceName;
	private String device;
	private String plexRemoteServer;

	private String plexAuthenticationToken;

	public Configuration(){
		host = "localhost";
		port = "32400";
		username = "";
		password = "";
		clientIdentifier = UUID.randomUUID().toString();
		product = "";
		productVersion = "";
		plexRemoteServer = "https://plex.tv";
		plexAuthenticationToken = "";
	}

	/* (non-Javadoc)
	 * @see com.github.kingargyle.plexapp.config.IConfiguration#getHost()
	 */
	public String getHost() {
		return host;
	}

	/* (non-Javadoc)
	 * @see com.github.kingargyle.plexapp.config.IConfiguration#setHost(java.lang.String)
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see com.github.kingargyle.plexapp.config.IConfiguration#getPort()
	 */
	public String getPort() {
		return port;
	}

	/* (non-Javadoc)
	 * @see com.github.kingargyle.plexapp.config.IConfiguration#setPort(java.lang.String)
	 */
	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientIdentifier() {
		return clientIdentifier;
	}

	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String version) {
		this.productVersion = version;
	}

	public String getPlexRemoteServer() {
		return plexRemoteServer;
	}

	public void setPlexRemoteServer(String remoteHost) {
		this.plexRemoteServer = remoteHost;
	}

	public String getPlexAuthenticationToken() {
		return plexAuthenticationToken;
	}

	public void setPlexAuthenticationToken(String token) {
		this.plexAuthenticationToken = token;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
}
