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

/**
 * Excusive optional specification of the interface to use for outgoing
 * multicast.
 */

public class OutgoingMulticastConfig {

	/**
	 * Network interface of socket for outgoing multicast traffic. Alternative
	 * to {@link #outgoingAddress}.
	 */
	private String outgoingInterface = null;

	/**
	 * Address of network interface for outgoing multicast traffic. Alternative
	 * to {@link #outgoingInterface}.
	 */
	private String outgoingAddress = null;

	/**
	 * {@code true}, to disable loopback mode, {@code false}, otherwise.
	 */
	private Boolean disableLoopback = Boolean.FALSE;

	public OutgoingMulticastConfig() {
	}

	/**
	 * @return the outgoingInterface
	 */
	public String getOutgoingInterface() {
		return outgoingInterface;
	}

	/**
	 * @param outgoingInterface the outgoingInterface to set
	 */
	public void setOutgoingInterface(String outgoingInterface) {
		this.outgoingInterface = outgoingInterface;
	}

	/**
	 * @return the outgoingAddress
	 */
	public String getOutgoingAddress() {
		return outgoingAddress;
	}

	/**
	 * @param outgoingAddress the outgoingAddress to set
	 */
	public void setOutgoingAddress(String outgoingAddress) {
		this.outgoingAddress = outgoingAddress;
	}

	/**
	 * @return the disableLoopback
	 */
	public Boolean getDisableLoopback() {
		return disableLoopback;
	}

	/**
	 * @param disableLoopback the disableLoopback to set
	 */
	public void setDisableLoopback(Boolean disableLoopback) {
		this.disableLoopback = disableLoopback;
	}
}
