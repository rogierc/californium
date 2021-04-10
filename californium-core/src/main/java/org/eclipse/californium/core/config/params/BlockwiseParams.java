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
 * Configuration of blockwise parameters
 *
 */
public class BlockwiseParams implements VisitableConfig {

	/**
	 * The block size [bytes] to use when doing a blockwise transfer. This value
	 * serves as the upper limit for block size in blockwise transfers.
	 */
	private Integer preferredBlockSize = null;

	/**
	 * The maximum payload size [bytes] that can be transferred in a single
	 * message, i.e. without requiring a blockwise transfer. This value cannot
	 * exceed the network's MTU.
	 */
	private Integer maxMessageSize = null;

	/**
	 * The maximum size [bytes] of a resource body that will be accepted as the
	 * payload of a POST/PUT or the response to a GET request in a
	 * <em>transparent</em> blockwise transfer. Note that this option does not
	 * prevent local clients or resource implementations from sending large
	 * bodies as part of a request or response to a peer.
	 */
	private Integer maxResourceBodySize = null;

	/**
	 * The maximum amount of time in milliseconds [ms], allowed between
	 * transfers of individual blocks in a blockwise transfer, before the
	 * blockwise transfer state is discarded.
	 */
	private Integer blockwiseStatusLifetime = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the preferredBlockSize
	 */
	public Integer getPreferredBlockSize() {
		return preferredBlockSize;
	}

	/**
	 * @param preferredBlockSize the preferredBlockSize to set
	 */
	public void setPreferredBlockSize(Integer preferredBlockSize) {
		this.preferredBlockSize = preferredBlockSize;
	}

	/**
	 * @return the maxMessageSize
	 */
	public Integer getMaxMessageSize() {
		return maxMessageSize;
	}

	/**
	 * @param maxMessageSize the maxMessageSize to set
	 */
	public void setMaxMessageSize(Integer maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	/**
	 * @return the maxResourceBodySize
	 */
	public Integer getMaxResourceBodySize() {
		return maxResourceBodySize;
	}

	/**
	 * @param maxResourceBodySize the maxResourceBodySize to set
	 */
	public void setMaxResourceBodySize(Integer maxResourceBodySize) {
		this.maxResourceBodySize = maxResourceBodySize;
	}

	/**
	 * @return the blockwiseStatusLifetime
	 */
	public Integer getBlockwiseStatusLifetime() {
		return blockwiseStatusLifetime;
	}

	/**
	 * @param blockwiseStatusLifetime the blockwiseStatusLifetime to set
	 */
	public void setBlockwiseStatusLifetime(Integer blockwiseStatusLifetime) {
		this.blockwiseStatusLifetime = blockwiseStatusLifetime;
	}
}
