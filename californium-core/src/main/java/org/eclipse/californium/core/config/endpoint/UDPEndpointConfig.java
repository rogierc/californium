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

package org.eclipse.californium.core.config.endpoint;

import org.eclipse.californium.core.config.AbstractConfigVisitor;
import org.eclipse.californium.core.config.params.UdpParams;

/**
 * This is UDP coap endpoint
 *
 */
public class UDPEndpointConfig extends EndpointConfig {

	/**
	 * UDP endpoint parameters.
	 */
	private UdpParams udpParams = null;

	/**
	 * Default Constructor used by Mule. Mandatory and Nullsafe params are set
	 * by Mule.
	 */
	public UDPEndpointConfig() {
		super();
	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
		udpParams.accept(visitor);
	}
}
