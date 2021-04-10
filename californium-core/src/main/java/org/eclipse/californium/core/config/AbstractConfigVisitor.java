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

package org.eclipse.californium.core.config;

import org.eclipse.californium.core.config.congestion.BasicRtoConfig;
import org.eclipse.californium.core.config.congestion.CocoaConfig;
import org.eclipse.californium.core.config.congestion.CocoaStrongConfig;
import org.eclipse.californium.core.config.congestion.LinuxRtoConfig;
import org.eclipse.californium.core.config.congestion.PeakhopperRtoConfig;
import org.eclipse.californium.core.config.deduplication.CropRotationConfig;
import org.eclipse.californium.core.config.deduplication.MarkAndSweepConfig;
import org.eclipse.californium.core.config.endpoint.DTLSEndpointConfig;
import org.eclipse.californium.core.config.endpoint.EndpointConfig;
import org.eclipse.californium.core.config.endpoint.MulticastUDPEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TCPClientEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TCPEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TCPServerEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TLSClientEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TLSEndpointConfig;
import org.eclipse.californium.core.config.endpoint.TLSServerEndpointConfig;
import org.eclipse.californium.core.config.endpoint.UDPEndpointConfig;
import org.eclipse.californium.core.config.midtracker.GroupedMidTrackerConfig;
import org.eclipse.californium.core.config.midtracker.MapBasedMidTrackerConfig;
import org.eclipse.californium.core.config.midtracker.NullMidTrackerConfig;
import org.eclipse.californium.core.config.params.BlockwiseParams;
import org.eclipse.californium.core.config.params.DtlsParams;
import org.eclipse.californium.core.config.params.SecurityParams;
import org.eclipse.californium.core.config.params.ExchangeParams;
import org.eclipse.californium.core.config.params.LogHealthStatus;
import org.eclipse.californium.core.config.params.MulticastParams;
import org.eclipse.californium.core.config.params.NotificationParams;
import org.eclipse.californium.core.config.params.SocketParams;
import org.eclipse.californium.core.config.params.TcpParams;
import org.eclipse.californium.core.config.params.TlsParams;
import org.eclipse.californium.core.config.params.UdpParams;

/**
 * Visitor for Configurations. The visitor interface in api package allows
 * making the concrete visitor internal.
 */
public abstract class AbstractConfigVisitor {

	/**
	 * Visit BlockwiseParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(BlockwiseParams toVisit) {
	}

	/**
	 * Visit UdpParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(UdpParams toVisit) {
	}

	/**
	 * Visit SocketParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(SocketParams toVisit) {
	}

	/**
	 * Visit EncryptionParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(SecurityParams toVisit) {
	}

	/**
	 * Visit ExchangeParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(ExchangeParams toVisit) {
	}

	/**
	 * Visit GroupedMidTracker configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(GroupedMidTrackerConfig toVisit) {
	}

	/**
	 * Visit MapBasedMidTracker configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(MapBasedMidTrackerConfig toVisit) {
	}

	/**
	 * Visit NullMidTracker configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(NullMidTrackerConfig toVisit) {
	}

	/**
	 * Visit CropRotation configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(CropRotationConfig toVisit) {
	}

	/**
	 * Visit MarkAndSweep configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(MarkAndSweepConfig toVisit) {
	}

	/**
	 * Visit LogHealthStatus configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(LogHealthStatus toVisit) {
	}

	/**
	 * Visit NotificationParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(NotificationParams toVisit) {
	}

	/**
	 * Visit BasicRto configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(BasicRtoConfig toVisit) {
	}

	/**
	 * Visit Cocoa configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(CocoaConfig toVisit) {
	}

	/**
	 * Visit CocoaStrong configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(CocoaStrongConfig toVisit) {
	}

	/**
	 * Visit LinuxRto configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(LinuxRtoConfig toVisit) {
	}

	/**
	 * Visit PeakhopperRto configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(PeakhopperRtoConfig toVisit) {
	}

	/**
	 * Visit Endpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(EndpointConfig toVisit) {
	}

	/**
	 * Visit UDPEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(UDPEndpointConfig toVisit) {
	}

	/**
	 * Visit MulticastUDPEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(MulticastUDPEndpointConfig toVisit) {
	}

	/**
	 * Visit DTLSEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(DTLSEndpointConfig toVisit) {
	}

	/**
	 * Visit TCPEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TCPEndpointConfig toVisit) {
	}

	/**
	 * Visit TcpParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TcpParams toVisit) {
	}

	/**
	 * Visit TlsParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TlsParams toVisit) {
	}

	/**
	 * Visit TCPClientEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TCPClientEndpointConfig toVisit) {
	}

	/**
	 * Visit TCPServerEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TCPServerEndpointConfig toVisit) {
	}

	/**
	 * Visit TLSEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TLSEndpointConfig toVisit) {
	}

	/**
	 * Visit TLSClientEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TLSClientEndpointConfig toVisit) {
	}

	/**
	 * Visit TLSServerEndpoint configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(TLSServerEndpointConfig toVisit) {
	}

	/**
	 * Visit DtlsParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(DtlsParams toVisit) {
	}

	/**
	 * Visit MulticastParams configuration object.
	 * 
	 * @param toVisit the object to visit.
	 */
	public void visit(MulticastParams toVisit) {
	}
}
