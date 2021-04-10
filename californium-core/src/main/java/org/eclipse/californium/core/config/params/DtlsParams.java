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
 * DTLS configuration parameters.
 *
 */
public class DtlsParams implements VisitableConfig {

	/**
	 * The DTLS response matcher defines the algorithm used to correlate
	 * responses to requests.
	 */

	private DtlsResponseMatchingName responseMatching = DtlsResponseMatchingName.STRICT;

	/**
	 * DTLS auto resumption timeout in milliseconds [ms]. After that period
	 * without exchanged messages, the session is forced to resume.
	 */

	private Integer dtlsAutoResumeTimeout = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the responseMatching
	 */
	public DtlsResponseMatchingName getResponseMatching() {
		return responseMatching;
	}

	/**
	 * @param responseMatching the responseMatching to set
	 */
	public void setResponseMatching(DtlsResponseMatchingName responseMatching) {
		this.responseMatching = responseMatching;
	}

	/**
	 * @return the dtlsAutoResumeTimeout
	 */
	public Integer getDtlsAutoResumeTimeout() {
		return dtlsAutoResumeTimeout;
	}

	/**
	 * @param dtlsAutoResumeTimeout the dtlsAutoResumeTimeout to set
	 */
	public void setDtlsAutoResumeTimeout(Integer dtlsAutoResumeTimeout) {
		this.dtlsAutoResumeTimeout = dtlsAutoResumeTimeout;
	}

}
