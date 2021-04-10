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

package org.eclipse.californium.core.config.visitor;

import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.config.AbstractConfigVisitor;
import org.eclipse.californium.core.config.congestion.BasicRtoConfig;
import org.eclipse.californium.core.config.congestion.CocoaConfig;
import org.eclipse.californium.core.config.congestion.CocoaStrongConfig;
import org.eclipse.californium.core.config.congestion.LinuxRtoConfig;
import org.eclipse.californium.core.config.congestion.PeakhopperRtoConfig;
import org.eclipse.californium.core.config.deduplication.CropRotationConfig;
import org.eclipse.californium.core.config.deduplication.MarkAndSweepConfig;
import org.eclipse.californium.core.config.midtracker.GroupedMidTrackerConfig;
import org.eclipse.californium.core.config.midtracker.MapBasedMidTrackerConfig;
import org.eclipse.californium.core.config.midtracker.NullMidTrackerConfig;
import org.eclipse.californium.core.config.params.BlockwiseParams;
import org.eclipse.californium.core.config.params.DtlsParams;
import org.eclipse.californium.core.config.params.ExchangeParams;
import org.eclipse.californium.core.config.params.LogHealthStatus;
import org.eclipse.californium.core.config.params.NotificationParams;
import org.eclipse.californium.core.config.params.SocketParams;
import org.eclipse.californium.core.config.params.TcpParams;
import org.eclipse.californium.core.config.params.TlsParams;
import org.eclipse.californium.core.config.params.UdpParams;

/**
 * Configuration visitor that collects configuration parameters to produce a
 * NetworkConfig.
 *
 */
public class NetworkConfigGetVisitor extends AbstractConfigVisitor {

	private NetworkConfig networkConfig = NetworkConfig.createStandardWithoutFile();

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(BlockwiseParams toVisit) {
		if (toVisit.getPreferredBlockSize() != null)
			networkConfig.setInt(NetworkConfig.Keys.PREFERRED_BLOCK_SIZE, toVisit.getPreferredBlockSize());
		if (toVisit.getMaxMessageSize() != null)
			networkConfig.setInt(NetworkConfig.Keys.MAX_MESSAGE_SIZE, toVisit.getMaxMessageSize());
		// TODO: only transparent blockwise is supported: maxResourceBodySize >

		if (toVisit.getMaxResourceBodySize() != null && toVisit.getMaxResourceBodySize() > 0)
			networkConfig.setInt(NetworkConfig.Keys.MAX_RESOURCE_BODY_SIZE, toVisit.getMaxResourceBodySize());

		if (toVisit.getBlockwiseStatusLifetime() != null)
			networkConfig.setInt(NetworkConfig.Keys.BLOCKWISE_STATUS_LIFETIME, toVisit.getBlockwiseStatusLifetime());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(ExchangeParams toVisit) {
		if (toVisit.getMaxActivePeers() != null)
			networkConfig.setInt(NetworkConfig.Keys.MAX_ACTIVE_PEERS, toVisit.getMaxActivePeers());
		if (toVisit.getMaxPeerInactivityPeriod() != null)
			networkConfig.setInt(NetworkConfig.Keys.MAX_PEER_INACTIVITY_PERIOD, toVisit.getMaxPeerInactivityPeriod());
		if (toVisit.getAckTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.ACK_TIMEOUT, toVisit.getAckTimeout());
		if (toVisit.getAckRandomFactor() != null)
			networkConfig.setFloat(NetworkConfig.Keys.ACK_RANDOM_FACTOR, toVisit.getAckRandomFactor());

		if (toVisit.getAckTimeoutScale() != null)
			networkConfig.setFloat(NetworkConfig.Keys.ACK_TIMEOUT_SCALE, toVisit.getAckTimeoutScale());
		if (toVisit.getMaxRetransmit() != null)
			networkConfig.setInt(NetworkConfig.Keys.MAX_RETRANSMIT, toVisit.getMaxRetransmit());
		if (toVisit.getExchangeLifetime() != null)
			networkConfig.setLong(NetworkConfig.Keys.EXCHANGE_LIFETIME, toVisit.getExchangeLifetime());

		if (toVisit.getNonLifetime() != null)
			networkConfig.setLong(NetworkConfig.Keys.NON_LIFETIME, toVisit.getNonLifetime());

		if (toVisit.getNstart() != null)
			networkConfig.setInt(NetworkConfig.Keys.NSTART, toVisit.getNstart());
		if (toVisit.getTokenSizeLimit() != null)
			networkConfig.setInt(NetworkConfig.Keys.TOKEN_SIZE_LIMIT, toVisit.getTokenSizeLimit());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(GroupedMidTrackerConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.MID_TRACKER, "GROUPED");
		if (toVisit.getMidTrackerGroups() != null)
			networkConfig.setInt(NetworkConfig.Keys.MID_TRACKER_GROUPS, toVisit.getMidTrackerGroups());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(MapBasedMidTrackerConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.MID_TRACKER, "MAPBASED");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(NullMidTrackerConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.MID_TRACKER, "NULL");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(CropRotationConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.DEDUPLICATOR, NetworkConfig.Keys.DEDUPLICATOR_CROP_ROTATION);
		if (toVisit.getCropRotationPeriod() != null)
			networkConfig.setLong(NetworkConfig.Keys.CROP_ROTATION_PERIOD, toVisit.getCropRotationPeriod());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(MarkAndSweepConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.DEDUPLICATOR, NetworkConfig.Keys.DEDUPLICATOR_MARK_AND_SWEEP);
		if (toVisit.getMarkAndSweepInterval() != null)
			networkConfig.setLong(NetworkConfig.Keys.MARK_AND_SWEEP_INTERVAL, toVisit.getMarkAndSweepInterval());

	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(LogHealthStatus toVisit) {
		if (toVisit.getHealthStatusInterval() != null) {
			networkConfig.setInt(NetworkConfig.Keys.HEALTH_STATUS_INTERVAL, toVisit.getHealthStatusInterval());
		} else {
			networkConfig.setInt(NetworkConfig.Keys.HEALTH_STATUS_INTERVAL, 600);
		}
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(BasicRtoConfig basicRtoConfig) {
		networkConfig.setBoolean(NetworkConfig.Keys.USE_CONGESTION_CONTROL, true);
		networkConfig.setString(NetworkConfig.Keys.CONGESTION_CONTROL_ALGORITHM, "BasicRto");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(CocoaConfig cocoaConfig) {
		networkConfig.setBoolean(NetworkConfig.Keys.USE_CONGESTION_CONTROL, true);
		networkConfig.setString(NetworkConfig.Keys.CONGESTION_CONTROL_ALGORITHM, "Cocoa");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(CocoaStrongConfig cocoaStrongConfig) {
		networkConfig.setBoolean(NetworkConfig.Keys.USE_CONGESTION_CONTROL, true);
		networkConfig.setString(NetworkConfig.Keys.CONGESTION_CONTROL_ALGORITHM, "CocoaStrong");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(LinuxRtoConfig linuxRtoConfig) {
		networkConfig.setBoolean(NetworkConfig.Keys.USE_CONGESTION_CONTROL, true);
		networkConfig.setString(NetworkConfig.Keys.CONGESTION_CONTROL_ALGORITHM, "LinuxRto");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(PeakhopperRtoConfig peakhopperRtoConfig) {
		networkConfig.setBoolean(NetworkConfig.Keys.USE_CONGESTION_CONTROL, true);
		networkConfig.setString(NetworkConfig.Keys.CONGESTION_CONTROL_ALGORITHM, "PeakhopperRto");
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(UdpParams toVisit) {
		if (toVisit.getNetworkStageReceiverThreadCount() != null)
			networkConfig.setInt(NetworkConfig.Keys.NETWORK_STAGE_RECEIVER_THREAD_COUNT,
					toVisit.getNetworkStageReceiverThreadCount());

		if (toVisit.getNetworkStageSenderThreadCount() != null)
			networkConfig.setInt(NetworkConfig.Keys.NETWORK_STAGE_SENDER_THREAD_COUNT,
					toVisit.getNetworkStageSenderThreadCount());

		if (toVisit.getUdpConnectorDatagramSize() != null)
			networkConfig.setInt(NetworkConfig.Keys.UDP_CONNECTOR_DATAGRAM_SIZE, toVisit.getUdpConnectorDatagramSize());
		if (toVisit.getUdpConnectorReceiveBuffer() != null)
			networkConfig.setInt(NetworkConfig.Keys.UDP_CONNECTOR_RECEIVE_BUFFER,
					toVisit.getUdpConnectorReceiveBuffer());
		if (toVisit.getUdpConnectorSendBuffer() != null)
			networkConfig.setInt(NetworkConfig.Keys.UDP_CONNECTOR_SEND_BUFFER, toVisit.getUdpConnectorSendBuffer());
		if (toVisit.getUseRandomMidStart() != null)
			networkConfig.setBoolean(NetworkConfig.Keys.USE_RANDOM_MID_START, toVisit.getUseRandomMidStart());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(TcpParams toVisit) {
		if (toVisit.getTcpConnectTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.TCP_CONNECT_TIMEOUT, toVisit.getTcpConnectTimeout());

		if (toVisit.getTcpWorkerThreads() != null)
			networkConfig.setInt(NetworkConfig.Keys.TCP_WORKER_THREADS, toVisit.getTcpWorkerThreads());
		if (toVisit.getTcpConnectionIdleTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.TCP_CONNECTION_IDLE_TIMEOUT, toVisit.getTcpConnectTimeout());

	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(TlsParams toVisit) {
		if (toVisit.getTlsHandshakeTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.TLS_HANDSHAKE_TIMEOUT, toVisit.getTlsHandshakeTimeout());

		// TODO report Cf bug where long is used
		if (toVisit.getSecureSessionTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.SECURE_SESSION_TIMEOUT, toVisit.getSecureSessionTimeout());
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(DtlsParams toVisit) {
		if (toVisit.getDtlsAutoResumeTimeout() != null)
			networkConfig.setInt(NetworkConfig.Keys.DTLS_AUTO_RESUME_TIMEOUT, toVisit.getDtlsAutoResumeTimeout());
		if (toVisit.getResponseMatching() != null) {
			switch (toVisit.getResponseMatching()) {
			case STRICT:
				networkConfig.setString(NetworkConfig.Keys.RESPONSE_MATCHING, "STRICT");
				break;
			case PRINCIPAL:
				networkConfig.setString(NetworkConfig.Keys.RESPONSE_MATCHING, "PRINCIPAL");
				break;
			case RELAXED:
				networkConfig.setString(NetworkConfig.Keys.RESPONSE_MATCHING, "RELAXED");
				break;
			default:
				break;
			}
		}
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(SocketParams toVisit) {
		// TODO does Cf use networkconfig port when explicit endpoint is
		// configured?
		// host is configured on endpoint itself
		if (toVisit.getBindToPort() != null) {
			networkConfig.setInt(NetworkConfig.Keys.COAP_PORT, toVisit.getBindToPort());
			networkConfig.setInt(NetworkConfig.Keys.COAP_SECURE_PORT, toVisit.getBindToPort());
		}
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(NotificationParams toVisit) {
		if (toVisit.getNotificationCheckIntervalTime() != null)
			networkConfig.setLong(NetworkConfig.Keys.NOTIFICATION_CHECK_INTERVAL_TIME,
					toVisit.getNotificationCheckIntervalTime());
		if (toVisit.getNotificationCheckIntervalCount() != null)
			networkConfig.setInt(NetworkConfig.Keys.NOTIFICATION_CHECK_INTERVAL_COUNT,
					toVisit.getNotificationCheckIntervalCount());
		if (toVisit.getNotificationReregistrationBackoff() != null)
			networkConfig.setLong(NetworkConfig.Keys.NOTIFICATION_REREGISTRATION_BACKOFF,
					toVisit.getNotificationReregistrationBackoff());
	}

	// not at endpoint level
	// if ( toVisit.protocolStageThreadCount != null ) networkConfig.setInt(
	// NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT,
	// toVisit.protocolStageThreadCount ); // CORES);

	/*
	 * HTTP networkConfig: if ( toVisit.httpPort != null )
	 * networkConfig.setInt(NetworkConfig.Keys.HTTP_PORT, toVisit.httpPort ); //
	 * 8080); if ( toVisit.httpServerSocketTimeout != null )
	 * networkConfig.setInt(NetworkConfig.Keys.HTTP_SERVER_SOCKET_TIMEOUT,
	 * toVisit.httpServerSocketTimeout ); toVisit.httpServerSocketBufferSize !=
	 * null )
	 * networkConfig.setInt(NetworkConfig.Keys.HTTP_SERVER_SOCKET_BUFFER_SIZE,
	 * toVisit.httpServerSocketBufferSize ); toVisit.httpCacheResponseMaxAge !=
	 * null )
	 * networkConfig.setInt(NetworkConfig.Keys.HTTP_CACHE_RESPONSE_MAX_AGE,
	 * toVisit.httpCacheResponseMaxAge ); != null )
	 * networkConfig.setInt(NetworkConfig.Keys.HTTP_CACHE_SIZE,
	 * toVisit.httpCacheSize );
	 */

	/**
	 * Get the Californium Network configuration that has been collected.
	 */
	public NetworkConfig getNetworkConfig() {
		return networkConfig;

	}
}
