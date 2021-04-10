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
import org.eclipse.californium.core.config.deduplication.DeduplicatorConfig;

/**
 * Configuration of coap exchange parameters
 */
public class ExchangeParams implements VisitableConfig {

	/**
	 * The maximum number of active peers supported.
	 */
	private Integer maxActivePeers = null;

	/**
	 * The maximum number of seconds [s] a peer may be inactive for before it is
	 * considered stale and all state associated with it can be discarded.
	 */
	private Integer maxPeerInactivityPeriod = null;

	/**
	 * The minimum spacing (in milliseconds [ms]) before retransmission is
	 * tried.
	 */
	private Integer ackTimeout = null;

	/**
	 * Factor for spreading retransmission timing.
	 */
	private Float ackRandomFactor = null;

	/**
	 * The back-off factor for retransmissions. Every subsequent retransmission
	 * time spacing is enlarged using this factor.
	 */
	private Float ackTimeoutScale = null;

	/**
	 * The maximum number of retransmissions that are attempted when no
	 * acknowledgement is received.
	 */
	private Integer maxRetransmit = null;

	/**
	 * The time (in milliseconds [ms]) from starting to send a Confirmable
	 * message to the time when an acknowledgement is no longer expected.
	 * Default values is 247000 (247 seconds).
	 */
	private Long exchangeLifetime = null;

	/**
	 * The time (in milliseconds [ms]) from sending a Non-confirmable message to
	 * the time its Message ID can be safely reused. Default values is 145000
	 * (145 seconds).
	 */
	private Long nonLifetime = null;

	/**
	 * Maximum number of simultaneous outstanding interactions with a peer.
	 * (rfc7252 specifies default=1)
	 */
	private Integer nstart = null;

	/**
	 * The maximum token length (bytes).
	 */
	private Integer tokenSizeLimit = null;

	/**
	 * The deduplicator type used to deduplicate incoming messages. Available
	 * deduplicators are MARK_AND_SWEEP and CROP_ROTATION.
	 */
	private DeduplicatorConfig deduplicator = null;

	/**
	 * Default exchange params used by Mule. Containing mandatory and Nullsafe
	 * params are set by Mule.
	 */
	public ExchangeParams() {
	}

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
		if (deduplicator != null)
			deduplicator.accept(visitor);
	}

	/**
	 * @return the maxActivePeers
	 */
	public Integer getMaxActivePeers() {
		return maxActivePeers;
	}

	/**
	 * @param maxActivePeers the maxActivePeers to set
	 */
	public void setMaxActivePeers(Integer maxActivePeers) {
		this.maxActivePeers = maxActivePeers;
	}

	/**
	 * @return the maxPeerInactivityPeriod
	 */
	public Integer getMaxPeerInactivityPeriod() {
		return maxPeerInactivityPeriod;
	}

	/**
	 * @param maxPeerInactivityPeriod the maxPeerInactivityPeriod to set
	 */
	public void setMaxPeerInactivityPeriod(Integer maxPeerInactivityPeriod) {
		this.maxPeerInactivityPeriod = maxPeerInactivityPeriod;
	}

	/**
	 * @return the ackTimeout
	 */
	public Integer getAckTimeout() {
		return ackTimeout;
	}

	/**
	 * @param ackTimeout the ackTimeout to set
	 */
	public void setAckTimeout(Integer ackTimeout) {
		this.ackTimeout = ackTimeout;
	}

	/**
	 * @return the ackRandomFactor
	 */
	public Float getAckRandomFactor() {
		return ackRandomFactor;
	}

	/**
	 * @param ackRandomFactor the ackRandomFactor to set
	 */
	public void setAckRandomFactor(Float ackRandomFactor) {
		this.ackRandomFactor = ackRandomFactor;
	}

	/**
	 * @return the ackTimeoutScale
	 */
	public Float getAckTimeoutScale() {
		return ackTimeoutScale;
	}

	/**
	 * @param ackTimeoutScale the ackTimeoutScale to set
	 */
	public void setAckTimeoutScale(Float ackTimeoutScale) {
		this.ackTimeoutScale = ackTimeoutScale;
	}

	/**
	 * @return the maxRetransmit
	 */
	public Integer getMaxRetransmit() {
		return maxRetransmit;
	}

	/**
	 * @param maxRetransmit the maxRetransmit to set
	 */
	public void setMaxRetransmit(Integer maxRetransmit) {
		this.maxRetransmit = maxRetransmit;
	}

	/**
	 * @return the exchangeLifetime
	 */
	public Long getExchangeLifetime() {
		return exchangeLifetime;
	}

	/**
	 * @param exchangeLifetime the exchangeLifetime to set
	 */
	public void setExchangeLifetime(Long exchangeLifetime) {
		this.exchangeLifetime = exchangeLifetime;
	}

	/**
	 * @return the nonLifetime
	 */
	public Long getNonLifetime() {
		return nonLifetime;
	}

	/**
	 * @param nonLifetime the nonLifetime to set
	 */
	public void setNonLifetime(Long nonLifetime) {
		this.nonLifetime = nonLifetime;
	}

	/**
	 * @return the nstart
	 */
	public Integer getNstart() {
		return nstart;
	}

	/**
	 * @param nstart the nstart to set
	 */
	public void setNstart(Integer nstart) {
		this.nstart = nstart;
	}

	/**
	 * @return the tokenSizeLimit
	 */
	public Integer getTokenSizeLimit() {
		return tokenSizeLimit;
	}

	/**
	 * @param tokenSizeLimit the tokenSizeLimit to set
	 */
	public void setTokenSizeLimit(Integer tokenSizeLimit) {
		this.tokenSizeLimit = tokenSizeLimit;
	}

	/**
	 * @return the deduplicator
	 */
	public DeduplicatorConfig getDeduplicator() {
		return deduplicator;
	}

	/**
	 * @param deduplicator the deduplicator to set
	 */
	public void setDeduplicator(DeduplicatorConfig deduplicator) {
		this.deduplicator = deduplicator;
	}
}
