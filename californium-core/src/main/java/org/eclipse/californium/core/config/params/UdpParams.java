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
import org.eclipse.californium.core.config.midtracker.GroupedMidTrackerConfig;
import org.eclipse.californium.core.config.midtracker.MidTrackerConfig;

/**
 * Configuration of which it is not clear whether it is really used.
 *
 */
public class UdpParams implements VisitableConfig {

	/**
	 * Receiver thread pool size. Default value is 1, or equal to the number of
	 * cores on Windows.
	 */
	private Integer networkStageReceiverThreadCount = null;

	/**
	 * Sender thread pool size. Default value is 1, or equal to the number of
	 * cores on Windows.
	 */
	private Integer networkStageSenderThreadCount = null;

	/**
	 * UDP datagram size [bytes].
	 */
	private Integer udpConnectorDatagramSize = null;

	/**
	 * UDP receive buffer size [bytes].
	 */
	private Integer udpConnectorReceiveBuffer = null;

	/**
	 * UDP send buffer size [bytes].
	 */
	private Integer udpConnectorSendBuffer = null;

	/**
	 * When {@code true} the message IDs will start at a random index. Otherwise
	 * the first message ID returned will be {@code 0}.
	 */
	private Boolean useRandomMidStart = null;

	/**
	 * The message identity tracker used. The tracker maintains the
	 * administration of message id's uses in the CoAP exchanges. These use
	 * different strategies like maintaining a map of individual entries or use
	 * groups where id's get cleaned by group. Supported values are
	 * {@code NULL}, {@code GROUPED}, or {@code MAPBASED}.
	 */
	private MidTrackerConfig midTrackerConfig = null;

	/**
	 * Deafult constructor.
	 */
	public UdpParams() {
		super();
		this.midTrackerConfig = new GroupedMidTrackerConfig();

	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
		midTrackerConfig.accept(visitor);
	}

	/**
	 * @return the networkStageReceiverThreadCount
	 */
	public Integer getNetworkStageReceiverThreadCount() {
		return networkStageReceiverThreadCount;
	}

	/**
	 * @param networkStageReceiverThreadCount the
	 *            networkStageReceiverThreadCount to set
	 */
	public void setNetworkStageReceiverThreadCount(Integer networkStageReceiverThreadCount) {
		this.networkStageReceiverThreadCount = networkStageReceiverThreadCount;
	}

	/**
	 * @return the networkStageSenderThreadCount
	 */
	public Integer getNetworkStageSenderThreadCount() {
		return networkStageSenderThreadCount;
	}

	/**
	 * @param networkStageSenderThreadCount the networkStageSenderThreadCount to
	 *            set
	 */
	public void setNetworkStageSenderThreadCount(Integer networkStageSenderThreadCount) {
		this.networkStageSenderThreadCount = networkStageSenderThreadCount;
	}

	/**
	 * @return the udpConnectorDatagramSize
	 */
	public Integer getUdpConnectorDatagramSize() {
		return udpConnectorDatagramSize;
	}

	/**
	 * @param udpConnectorDatagramSize the udpConnectorDatagramSize to set
	 */
	public void setUdpConnectorDatagramSize(Integer udpConnectorDatagramSize) {
		this.udpConnectorDatagramSize = udpConnectorDatagramSize;
	}

	/**
	 * @return the udpConnectorReceiveBuffer
	 */
	public Integer getUdpConnectorReceiveBuffer() {
		return udpConnectorReceiveBuffer;
	}

	/**
	 * @param udpConnectorReceiveBuffer the udpConnectorReceiveBuffer to set
	 */
	public void setUdpConnectorReceiveBuffer(Integer udpConnectorReceiveBuffer) {
		this.udpConnectorReceiveBuffer = udpConnectorReceiveBuffer;
	}

	/**
	 * @return the udpConnectorSendBuffer
	 */
	public Integer getUdpConnectorSendBuffer() {
		return udpConnectorSendBuffer;
	}

	/**
	 * @param udpConnectorSendBuffer the udpConnectorSendBuffer to set
	 */
	public void setUdpConnectorSendBuffer(Integer udpConnectorSendBuffer) {
		this.udpConnectorSendBuffer = udpConnectorSendBuffer;
	}

	/**
	 * @return the useRandomMidStart
	 */
	public Boolean getUseRandomMidStart() {
		return useRandomMidStart;
	}

	/**
	 * @param useRandomMidStart the useRandomMidStart to set
	 */
	public void setUseRandomMidStart(Boolean useRandomMidStart) {
		this.useRandomMidStart = useRandomMidStart;
	}

	/**
	 * @return the midTracker
	 */
	public MidTrackerConfig getMidTracker() {
		return midTrackerConfig;
	}

	/**
	 * @param midTrackerConfig the midTracker to set
	 */
	public void setMidTracker(MidTrackerConfig midTrackerConfig) {
		this.midTrackerConfig = midTrackerConfig;
	}

}
