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
import org.eclipse.californium.core.config.params.DtlsParams;
import org.eclipse.californium.core.config.params.SecurityParams;

/**
 * DTLS coap endpoint
 *
 */
public class DTLSEndpointConfig extends UDPEndpointConfig {

	/**
	 * DTLS auto resumption timeout in milliseconds [ms]. After that period
	 * without exchanged messages, the session is forced to resume.
	 */
	private DtlsParams dtlsParams;

	/**
	 * The encryption parameters.
	 */
	private SecurityParams securityParams = null;

	/**
	 * Default Constructor used by Mule. Mandatory and Nullsafe params are set
	 * by Mule.
	 */
	public DTLSEndpointConfig() {
		super();
		dtlsParams = new DtlsParams();
		securityParams = new SecurityParams();
	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		super.accept(visitor);
		visitor.visit(this);
		dtlsParams.accept(visitor);
		securityParams.accept(visitor);
	}

	/**
	 * @return the dtlsParams
	 */
	public DtlsParams getDtlsParams() {
		return dtlsParams;
	}

	/**
	 * @param dtlsParams the dtlsParams to set
	 */
	public void setDtlsParams(DtlsParams dtlsParams) {
		this.dtlsParams = dtlsParams;
	}

	/**
	 * @return the encryptionParams
	 */
	public SecurityParams getEncryptionParams() {
		return securityParams;
	}

	/**
	 * @param securityParams the encryptionParams to set
	 */
	public void setEncryptionParams(SecurityParams securityParams) {
		this.securityParams = securityParams;
	}
}
