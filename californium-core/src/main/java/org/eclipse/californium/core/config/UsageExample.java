package org.eclipse.californium.core.config;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.endpoint.MulticastUDPEndpointConfig;
import org.eclipse.californium.core.config.endpoint.UDPEndpointConfig;
import org.eclipse.californium.core.config.visitor.EndpointConstructionException;
import org.eclipse.californium.core.config.visitor.MulticastUdpEndpointConfigVisitor;
import org.eclipse.californium.core.config.visitor.UdpEndpointConfigVisitor;

public class UsageExample {
    /**
     * Build a Server using Multicast UDP endpoint.
     * @param config The Multicast UDP endpoint configuration
     * @throws EndpointConstructionException When the endpoint could not be constructed.

     */
    public CoapServer buildServer( MulticastUDPEndpointConfig config ) throws EndpointConstructionException 
    {
        MulticastUdpEndpointConfigVisitor visitor= new MulticastUdpEndpointConfigVisitor();
        config.accept( visitor );
    	CoapServer server= new CoapServer();
        server.addEndpoint( visitor.getEndpointBuilder().build());
		return server;
    }
    
    /**
     * Build a Server using UDP endpoint.
     * @param config The UDP endpoint configuration
     * @throws EndpointConstructionException When the endpoint could not be constructed.

     */
    public CoapServer buildServer( UDPEndpointConfig config ) throws EndpointConstructionException 
    {
    	UdpEndpointConfigVisitor visitor= new UdpEndpointConfigVisitor();
        config.accept( visitor );
    	CoapServer server= new CoapServer();
        server.addEndpoint( visitor.getEndpointBuilder().build());
		return server;
    }
}
