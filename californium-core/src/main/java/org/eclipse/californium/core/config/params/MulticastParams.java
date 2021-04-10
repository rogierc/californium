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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.californium.core.config.AbstractConfigVisitor;
import org.eclipse.californium.core.config.VisitableConfig;
import org.eclipse.californium.core.config.endpoint.OutgoingMulticastConfig;

/**
 * Configuration of multi-cast.
 *
 */
public class MulticastParams implements VisitableConfig {

	/**
	 * Parameters for outgoing multicast traffic.
	 */

	private OutgoingMulticastConfig outgoingMulticastConfig;

	/**
	 * The list of multi-cast groups the endpint supports.
	 */

	private List<MulticastGroupConfig> join;

	/**
	 * Default Constructor used by Mule. Mandatory and Nullsafe params are set
	 * by Mule.
	 */
	public MulticastParams() {
		join = new CopyOnWriteArrayList<MulticastGroupConfig>();
		outgoingMulticastConfig = new OutgoingMulticastConfig();
	}

	/**
	 * Constructor for manually constructing the endpoint. (Mule uses default
	 * constructor and sets Nullsafe params.)
	 * 
	 * @param joinMulticastGroups List of groups to join.
	 */
	public MulticastParams(List<MulticastGroupConfig> joinMulticastGroups) {
		join = joinMulticastGroups;
		outgoingMulticastConfig = new OutgoingMulticastConfig();
	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the outgoingMulticastConfig
	 */
	public OutgoingMulticastConfig getOutgoingMulticastConfig() {
		return outgoingMulticastConfig;
	}

	/**
	 * @param outgoingMulticastConfig the outgoingMulticastConfig to set
	 */
	public void setOutgoingMulticastConfig(OutgoingMulticastConfig outgoingMulticastConfig) {
		this.outgoingMulticastConfig = outgoingMulticastConfig;
	}

	/**
	 * @return the join
	 */
	public List<MulticastGroupConfig> getJoin() {
		return join;
	}

	/**
	 * @param join the join to set
	 */
	public void setJoin(List<MulticastGroupConfig> join) {
		this.join = join;
	}
}
