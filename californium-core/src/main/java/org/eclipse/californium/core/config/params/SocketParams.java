/*******************************************************************************
 * Copyright (c) 2021> Rogier Cobben and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Rogier Cobben - initial creation
 ******************************************************************************/

package org.eclipse.californium.core.config.params;

import org.eclipse.californium.core.config.AbstractConfigVisitor;
import org.eclipse.californium.core.config.VisitableConfig;

/**
 * Socket configuration parameters
 *
 */
public class SocketParams implements VisitableConfig {

	/**
	 * The hostname or IP address the CoAP endpoint binds to. If none is given
	 * anyLocalAddress is used (typically 0.0.0.0 or ::0)
	 */
	private String bindToHost = null;

	/**
	 * The port the CoAP server will listen on. If empty the default coap of
	 * coaps port will be used.
	 */
	private Integer bindToPort = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the bindToHost
	 */
	public String getBindToHost() {
		return bindToHost;
	}

	/**
	 * @param bindToHost the bindToHost to set
	 */
	public void setBindToHost(String bindToHost) {
		this.bindToHost = bindToHost;
	}

	/**
	 * @return the bindToPort
	 */
	public Integer getBindToPort() {
		return bindToPort;
	}

	/**
	 * @param bindToPort the bindToPort to set
	 */
	public void setBindToPort(Integer bindToPort) {
		this.bindToPort = bindToPort;
	}
}
