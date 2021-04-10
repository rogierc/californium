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
public class TcpParams implements VisitableConfig {

	/**
	 * The number of TCP worker threads. Default value is 1.
	 */
	private Integer tcpWorkerThreads = null;

	/**
	 * The default tcp connect timeout in milliseconds [ms].
	 */
	private Integer tcpConnectTimeout = null;

	/**
	 * The default tcp connection idle timeout in seconds [s].
	 */
	private Integer tcpConnectionIdleTimeout = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the tcpWorkerThreads
	 */
	public Integer getTcpWorkerThreads() {
		return tcpWorkerThreads;
	}

	/**
	 * @param tcpWorkerThreads the tcpWorkerThreads to set
	 */
	public void setTcpWorkerThreads(Integer tcpWorkerThreads) {
		this.tcpWorkerThreads = tcpWorkerThreads;
	}

	/**
	 * @return the tcpConnectTimeout
	 */
	public Integer getTcpConnectTimeout() {
		return tcpConnectTimeout;
	}

	/**
	 * @param tcpConnectTimeout the tcpConnectTimeout to set
	 */
	public void setTcpConnectTimeout(Integer tcpConnectTimeout) {
		this.tcpConnectTimeout = tcpConnectTimeout;
	}

	/**
	 * @return the tcpConnectionIdleTimeout
	 */
	public Integer getTcpConnectionIdleTimeout() {
		return tcpConnectionIdleTimeout;
	}

	/**
	 * @param tcpConnectionIdleTimeout the tcpConnectionIdleTimeout to set
	 */
	public void setTcpConnectionIdleTimeout(Integer tcpConnectionIdleTimeout) {
		this.tcpConnectionIdleTimeout = tcpConnectionIdleTimeout;
	}

}
