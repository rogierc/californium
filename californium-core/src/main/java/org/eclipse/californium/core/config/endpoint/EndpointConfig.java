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
import org.eclipse.californium.core.config.VisitableConfig;
import org.eclipse.californium.core.config.congestion.CongestionControlConfig;
import org.eclipse.californium.core.config.params.BlockwiseParams;
import org.eclipse.californium.core.config.params.ExchangeParams;
import org.eclipse.californium.core.config.params.LogHealthStatus;
import org.eclipse.californium.core.config.params.NotificationParams;
import org.eclipse.californium.core.config.params.SocketParams;

/**
 * Endpoint configuration parameters
 *
 */
abstract public class EndpointConfig implements VisitableConfig {

	/**
	 * The socketParams to use
	 */
	private SocketParams socketParams;

	/**
	 * The coap exchange parameters.
	 */
	private ExchangeParams exchangeParams = null;

	/**
	 * The parameters for blockwise transfer.
	 */
	private BlockwiseParams blockwiseParams = null;

	/**
	 * The parameters for notification.
	 */
	private NotificationParams notificationParams = null;

	/**
	 * Configuration of the congestion control algorithm, if any.
	 */
	private CongestionControlConfig congestionControlConfig = null;

	/**
	 * When activated logHealthStatus is periodically logged.
	 */
	private LogHealthStatus logHealthStatus = null;

	/**
	 * When activated incoming and outgoing CoAP messages are logged.
	 */
	private boolean logCoapMessages = false;

	/**
	 * Default Constructor.
	 */
	public EndpointConfig() {
		socketParams = new SocketParams();
		exchangeParams = new ExchangeParams();
		blockwiseParams = new BlockwiseParams();
		notificationParams = new NotificationParams();
	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
		socketParams.accept(visitor);
		exchangeParams.accept(visitor);
		blockwiseParams.accept(visitor);
		notificationParams.accept(visitor);
		if (congestionControlConfig != null)
			congestionControlConfig.accept(visitor);
		if (logHealthStatus != null)
			logHealthStatus.accept(visitor);
	}

	/**
	 * @return the socketParams
	 */
	public SocketParams getSocketParams() {
		return socketParams;
	}

	/**
	 * @param socketParams the socketParams to set
	 */
	public void setSocketParams(SocketParams socketParams) {
		this.socketParams = socketParams;
	}

	/**
	 * @return the exchangeParams
	 */
	public ExchangeParams getExchangeParams() {
		return exchangeParams;
	}

	/**
	 * @param exchangeParams the exchangeParams to set
	 */
	public void setExchangeParams(ExchangeParams exchangeParams) {
		this.exchangeParams = exchangeParams;
	}

	/**
	 * @return the blockwiseParams
	 */
	public BlockwiseParams getBlockwiseParams() {
		return blockwiseParams;
	}

	/**
	 * @param blockwiseParams the blockwiseParams to set
	 */
	public void setBlockwiseParams(BlockwiseParams blockwiseParams) {
		this.blockwiseParams = blockwiseParams;
	}

	/**
	 * @return the notificationParams
	 */
	public NotificationParams getNotificationParams() {
		return notificationParams;
	}

	/**
	 * @param notificationParams the notificationParams to set
	 */
	public void setNotificationParams(NotificationParams notificationParams) {
		this.notificationParams = notificationParams;
	}

	/**
	 * @return the congestionControl
	 */
	public CongestionControlConfig getCongestionControl() {
		return congestionControlConfig;
	}

	/**
	 * @param congestionControlConfig the congestionControl to set
	 */
	public void setCongestionControl(CongestionControlConfig congestionControlConfig) {
		this.congestionControlConfig = congestionControlConfig;
	}

	/**
	 * @return the logHealthStatus
	 */
	public LogHealthStatus getLogHealthStatus() {
		return logHealthStatus;
	}

	/**
	 * @param logHealthStatus the logHealthStatus to set
	 */
	public void setLogHealthStatus(LogHealthStatus logHealthStatus) {
		this.logHealthStatus = logHealthStatus;
	}

	/**
	 * @return the logCoapMessages
	 */
	public boolean isLogCoapMessages() {
		return logCoapMessages;
	}

	/**
	 * @param logCoapMessages the logCoapMessages to set
	 */
	public void setLogCoapMessages(boolean logCoapMessages) {
		this.logCoapMessages = logCoapMessages;
	}
}
