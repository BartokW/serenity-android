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

package us.nineworlds.plex.rest.config;

/**
 * @author dcarver
 *
 */
public interface IConfiguration {

	/**
	 * @return the host
	 */
	public abstract String getHost();

	/**
	 * @param host the host to set
	 */
	public abstract void setHost(String host);

	/**
	 * @return the port
	 */
	public abstract String getPort();

	/**
	 * @param port the port to set
	 */
	public abstract void setPort(String port);

	public abstract String getUsername();
	public abstract void setUsername(String username);

	public abstract String getPassword();
	public abstract void setPassword(String password);

	public abstract String getClientIdentifier();
	public abstract void setClientIdentifier(String clientIdentifier);

	public abstract String getProduct();
	public abstract void setProduct(String product);

	public abstract String getProductVersion();
	public abstract void setProductVersion(String version);

	public abstract String getPlexRemoteServer();
	public abstract void setPlexRemoteServer(String remoteHost);

	public abstract String getPlexAuthenticationToken();
	public abstract void setPlexAuthenticationToken(String token);

	public abstract String getDeviceName();
	public abstract void setDeviceName(String name);

	public abstract String getDevice();
	public abstract void setDevice(String device);
}