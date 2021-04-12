package org.eclipse.californium.core.config;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.endpoint.MulticastUDPEndpointConfig;
import org.eclipse.californium.core.config.endpoint.UDPEndpointConfig;
import org.eclipse.californium.core.config.visitor.EndpointConstructionException;
import org.eclipse.californium.core.config.visitor.MulticastUdpEndpointConfigVisitor;
import org.eclipse.californium.core.config.visitor.NetworkConfigGetVisitor;
import org.eclipse.californium.core.config.visitor.NetworkConfigSetVisitor;
import org.eclipse.californium.core.config.visitor.UdpEndpointConfigVisitor;
import org.eclipse.californium.core.network.config.NetworkConfig;

public class UsageExample {

	/**
	 * Build a Server using Multicast UDP endpoint.
	 * 
	 * @param config The Multicast UDP endpoint configuration
	 * @return The configured server.
	 * @throws EndpointConstructionException When the endpoint could not be
	 *             constructed.
	 * 
	 */
	public CoapServer buildServer(MulticastUDPEndpointConfig config) throws EndpointConstructionException {
		MulticastUdpEndpointConfigVisitor visitor = new MulticastUdpEndpointConfigVisitor();
		config.accept(visitor);
		CoapServer server = new CoapServer();
		server.addEndpoint(visitor.getEndpointBuilder().build());
		return server;
	}

	/**
	 * Build a Server using UDP endpoint.
	 * 
	 * @param config The UDP endpoint configuration
	 * @return The configured server.
	 * @throws EndpointConstructionException When the endpoint could not be
	 *             constructed.
	 */
	public CoapServer buildServer(UDPEndpointConfig config) throws EndpointConstructionException {
		UdpEndpointConfigVisitor visitor = new UdpEndpointConfigVisitor();
		config.accept(visitor);
		CoapServer server = new CoapServer();
		server.addEndpoint(visitor.getEndpointBuilder().build());
		return server;
	}

	/**
	 * Extract networkconfig.
	 * 
	 * @param config The UDP endpoint configuration
	 * @return The extracted networkconfig.
	 */
	public NetworkConfig getNetworkConfig(UDPEndpointConfig config) {
		NetworkConfigGetVisitor visitor = new NetworkConfigGetVisitor();
		config.accept(visitor);
		return visitor.getNetworkConfig();
	}

	/**
	 * Impose networkconfig.
	 * 
	 * @param config The UDP endpoint configuration on which networkconfig
	 *            settings are imposed.
	 * @param networkConfig The network config settings to use.
	 */
	public void setNetworkConfig(UDPEndpointConfig config, NetworkConfig networkConfig) {
		NetworkConfigSetVisitor visitor = new NetworkConfigSetVisitor(networkConfig);
		config.accept(visitor);
	}

}
