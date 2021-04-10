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
 * Configuration visitor that imports NetworkConfig and writes these to the
 * configuration items visited.
 *
 */
public class NetworkConfigSetVisitor extends AbstractConfigVisitor {

	/**
	 * The network configuration to import.
	 */
	private NetworkConfig networkConfig;

	/**
	 * @param networkConfig The network configuration to import.
	 */
	public NetworkConfigSetVisitor(NetworkConfig networkConfig) {
		this.networkConfig = networkConfig;
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(BlockwiseParams toVisit) {
		if (networkConfig.getOptInteger(NetworkConfig.Keys.PREFERRED_BLOCK_SIZE) != null)
			toVisit.setPreferredBlockSize(networkConfig.getInt(NetworkConfig.Keys.PREFERRED_BLOCK_SIZE));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.MAX_MESSAGE_SIZE) != null)
			toVisit.setMaxMessageSize(networkConfig.getInt(NetworkConfig.Keys.MAX_MESSAGE_SIZE));
		// TODO: only transparent blockwise is supported: maxResourceBodySize >

		if (networkConfig.getOptInteger(NetworkConfig.Keys.MAX_RESOURCE_BODY_SIZE) != null)
			toVisit.setMaxResourceBodySize(networkConfig.getInt(NetworkConfig.Keys.MAX_RESOURCE_BODY_SIZE));

		if (networkConfig.getOptInteger(NetworkConfig.Keys.BLOCKWISE_STATUS_LIFETIME) != null)
			toVisit.setBlockwiseStatusLifetime(networkConfig.getInt(NetworkConfig.Keys.BLOCKWISE_STATUS_LIFETIME));
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(ExchangeParams toVisit) {
		if (networkConfig.getOptInteger(NetworkConfig.Keys.MAX_ACTIVE_PEERS) != null)
			toVisit.setMaxActivePeers(networkConfig.getInt(NetworkConfig.Keys.MAX_ACTIVE_PEERS));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.MAX_PEER_INACTIVITY_PERIOD) != null)
			toVisit.setMaxPeerInactivityPeriod(networkConfig.getInt(NetworkConfig.Keys.MAX_PEER_INACTIVITY_PERIOD));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.ACK_TIMEOUT) != null)
			toVisit.setAckTimeout(networkConfig.getInt(NetworkConfig.Keys.ACK_TIMEOUT));
		if (networkConfig.getFloat(NetworkConfig.Keys.ACK_RANDOM_FACTOR) != 0.0F)
			toVisit.setAckRandomFactor(networkConfig.getFloat(NetworkConfig.Keys.ACK_RANDOM_FACTOR));

		if (networkConfig.getFloat(NetworkConfig.Keys.ACK_TIMEOUT_SCALE) != 0.0F)
			toVisit.setAckTimeoutScale(networkConfig.getFloat(NetworkConfig.Keys.ACK_TIMEOUT_SCALE));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.MAX_RETRANSMIT) != null)
			toVisit.setMaxRetransmit(networkConfig.getInt(NetworkConfig.Keys.MAX_RETRANSMIT));
		if (networkConfig.getOptLong(NetworkConfig.Keys.EXCHANGE_LIFETIME) != null)
			toVisit.setExchangeLifetime(networkConfig.getLong(NetworkConfig.Keys.EXCHANGE_LIFETIME));

		if (networkConfig.getOptLong(NetworkConfig.Keys.NON_LIFETIME) != null)
			toVisit.setNonLifetime(networkConfig.getLong(NetworkConfig.Keys.NON_LIFETIME));

		if (networkConfig.getOptInteger(NetworkConfig.Keys.NSTART) != null)
			toVisit.setNstart(networkConfig.getInt(NetworkConfig.Keys.NSTART));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.TOKEN_SIZE_LIMIT) != null)
			toVisit.setTokenSizeLimit(networkConfig.getInt(NetworkConfig.Keys.TOKEN_SIZE_LIMIT));
		if (networkConfig.getString(NetworkConfig.Keys.DEDUPLICATOR, null) != null) {
			switch (networkConfig.getString(NetworkConfig.Keys.DEDUPLICATOR)) {
			case NetworkConfig.Keys.DEDUPLICATOR_AUTO_REPLACE:
				// TODO
				break;
			case NetworkConfig.Keys.DEDUPLICATOR_CROP_ROTATION:
				toVisit.setDeduplicator(new CropRotationConfig());
				break;
			case NetworkConfig.Keys.DEDUPLICATOR_MARK_AND_SWEEP:
				toVisit.setDeduplicator(new MarkAndSweepConfig());
				break;
			case NetworkConfig.Keys.DEDUPLICATOR_PEERS_MARK_AND_SWEEP:
				// TODO
				break;
			default:
				// TODO throw?
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
	public void visit(GroupedMidTrackerConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.MID_TRACKER, "GROUPED");
		if (networkConfig.getOptInteger(NetworkConfig.Keys.MID_TRACKER_GROUPS) != null)
			toVisit.setMidTrackerGroups(networkConfig.getInt(NetworkConfig.Keys.MID_TRACKER_GROUPS));
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
		if (networkConfig.getOptLong(NetworkConfig.Keys.CROP_ROTATION_PERIOD) != null)
			toVisit.setCropRotationPeriod(networkConfig.getLong(NetworkConfig.Keys.CROP_ROTATION_PERIOD));
	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(MarkAndSweepConfig toVisit) {
		networkConfig.setString(NetworkConfig.Keys.DEDUPLICATOR, NetworkConfig.Keys.DEDUPLICATOR_MARK_AND_SWEEP);
		if (networkConfig.getOptLong(NetworkConfig.Keys.MARK_AND_SWEEP_INTERVAL) != null)
			toVisit.setMarkAndSweepInterval(networkConfig.getLong(NetworkConfig.Keys.MARK_AND_SWEEP_INTERVAL));

	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(LogHealthStatus toVisit) {
		if (toVisit.getHealthStatusInterval() != null) {
			toVisit.setHealthStatusInterval(networkConfig.getInt(NetworkConfig.Keys.HEALTH_STATUS_INTERVAL));
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
		if (networkConfig.getOptInteger(NetworkConfig.Keys.UDP_CONNECTOR_DATAGRAM_SIZE) != null)
			toVisit.setUdpConnectorDatagramSize(networkConfig.getInt(NetworkConfig.Keys.UDP_CONNECTOR_DATAGRAM_SIZE));
		if (toVisit.getUdpConnectorReceiveBuffer() != null)
			networkConfig.setInt(NetworkConfig.Keys.UDP_CONNECTOR_RECEIVE_BUFFER,
					toVisit.getUdpConnectorReceiveBuffer());
		if (networkConfig.getOptInteger(NetworkConfig.Keys.UDP_CONNECTOR_SEND_BUFFER) != null)
			toVisit.setUdpConnectorSendBuffer(networkConfig.getInt(NetworkConfig.Keys.UDP_CONNECTOR_SEND_BUFFER));
		if (networkConfig.getBoolean(NetworkConfig.Keys.USE_RANDOM_MID_START) != false)
			toVisit.setUseRandomMidStart(networkConfig.getBoolean(NetworkConfig.Keys.USE_RANDOM_MID_START));
		if (networkConfig.getString(NetworkConfig.Keys.MID_TRACKER, null) != null) {
			switch (networkConfig.getString(NetworkConfig.Keys.MID_TRACKER)) {
			case "NULL":
				toVisit.setMidTracker(new NullMidTrackerConfig());
				break;
			case "GROUPED":
				toVisit.setMidTracker(new GroupedMidTrackerConfig());
				break;
			case "MAPBASED":
				toVisit.setMidTracker(new MapBasedMidTrackerConfig());
				break;
			default:
				// TODO throw?
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
	public void visit(TcpParams toVisit) {
		if (networkConfig.getOptInteger(NetworkConfig.Keys.TCP_CONNECT_TIMEOUT) != null)
			toVisit.setTcpConnectTimeout(networkConfig.getInt(NetworkConfig.Keys.TCP_CONNECT_TIMEOUT));

		if (networkConfig.getOptInteger(NetworkConfig.Keys.TCP_WORKER_THREADS) != null)
			toVisit.setTcpWorkerThreads(networkConfig.getInt(NetworkConfig.Keys.TCP_WORKER_THREADS));
		if (networkConfig.getOptInteger(NetworkConfig.Keys.TCP_CONNECTION_IDLE_TIMEOUT) != null)
			toVisit.setTcpConnectTimeout(networkConfig.getInt(NetworkConfig.Keys.TCP_CONNECTION_IDLE_TIMEOUT));

	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(TlsParams toVisit) {
		if (networkConfig.getOptInteger(NetworkConfig.Keys.TLS_HANDSHAKE_TIMEOUT) != null)
			toVisit.setTlsHandshakeTimeout(networkConfig.getInt(NetworkConfig.Keys.TLS_HANDSHAKE_TIMEOUT));

		if (networkConfig.getOptInteger(NetworkConfig.Keys.SECURE_SESSION_TIMEOUT) != null)
			toVisit.setSecureSessionTimeout(networkConfig.getInt(NetworkConfig.Keys.SECURE_SESSION_TIMEOUT));

	}

	/*
	 * Retrieve configuration from configuration item and add to networkconfig.
	 * 
	 * @param toVisit The configuration item to process.
	 */
	@Override
	public void visit(DtlsParams toVisit) {
		if (networkConfig.getOptInteger(NetworkConfig.Keys.DTLS_AUTO_RESUME_TIMEOUT) != null)
			toVisit.setDtlsAutoResumeTimeout(networkConfig.getInt(NetworkConfig.Keys.DTLS_AUTO_RESUME_TIMEOUT));
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
			toVisit.setBindToPort(networkConfig.getInt(NetworkConfig.Keys.COAP_PORT));
			toVisit.setBindToPort(networkConfig.getInt(NetworkConfig.Keys.COAP_SECURE_PORT));
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

}
