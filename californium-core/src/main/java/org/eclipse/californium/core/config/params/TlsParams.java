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
 * Configuration of which it is not clear whether it is really used.
 *
 */
public class TlsParams implements VisitableConfig {

	/**
	 * The tls handshake timeout in milliseconds [ms].
	 */
	private Integer tlsHandshakeTimeout = null;

	/**
	 * TLS session timeout in seconds [s]. Default value is 60 * 60 * 24 = 86400
	 * s (24 hours).
	 */
	private Integer secureSessionTimeout = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the tlsHandshakeTimeout
	 */
	public Integer getTlsHandshakeTimeout() {
		return tlsHandshakeTimeout;
	}

	/**
	 * @param tlsHandshakeTimeout the tlsHandshakeTimeout to set
	 */
	public void setTlsHandshakeTimeout(Integer tlsHandshakeTimeout) {
		this.tlsHandshakeTimeout = tlsHandshakeTimeout;
	}

	/**
	 * @return the secureSessionTimeout
	 */
	public Integer getSecureSessionTimeout() {
		return secureSessionTimeout;
	}

	/**
	 * @param secureSessionTimeout the secureSessionTimeout to set
	 */
	public void setSecureSessionTimeout(Integer secureSessionTimeout) {
		this.secureSessionTimeout = secureSessionTimeout;
	}

}
