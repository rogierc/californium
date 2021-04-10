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


import java.net.InetSocketAddress;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.config.endpoint.EndpointConfig;
import org.eclipse.californium.core.config.params.SocketParams;
import org.eclipse.californium.core.network.CoapEndpoint;


/**
 * Configuration visitor for building an endpoint
 *
 */
public class UdpEndpointConfigVisitor extends NetworkConfigGetVisitor
{
    /**
     * The name of the endpont 
     */
    private String endpointName;

    /**
     * The Endpoint Builder that is used to collect endpoint configuration.
     */
    CoapEndpoint.Builder endPointBuilder= new CoapEndpoint.Builder();

    /**
     * Visit Endpoint configuration object.
     * @param toVisit the object to visit.
     */
    @Override
    public void visit( EndpointConfig toVisit )
    {
        super.visit( toVisit );
    }

    /* (non-Javadoc)
     * @see nl.teslanet.mule.connectors.coap.api.config.ConfigVisitor#visit(nl.teslanet.mule.connectors.coap.api.config.SocketParams)
     */
    @Override
    public void visit( SocketParams toVisit )
    {
        super.visit( toVisit );
        int port= ( toVisit.getBindToPort() != null ? toVisit.getBindToPort() : CoAP.DEFAULT_COAP_PORT );

        if ( toVisit.getBindToHost() != null )
        {
            endPointBuilder.setInetSocketAddress( new InetSocketAddress( toVisit.getBindToHost(), port ) );
        }
        else
        {
            endPointBuilder.setInetSocketAddress( new InetSocketAddress( port ) );
        }
    }

    /**
     * @return The configured endpoint name.
     */
    public String getEndpointName()
    {
        return endpointName;
    }

    /**
     * Get the Builder that is ready to build the endpoint.
     * @return The Endpoint Builder.
     */
    public CoapEndpoint.Builder getEndpointBuilder() throws EndpointConstructionException
    {
        endPointBuilder.setNetworkConfig( this.getNetworkConfig() );
        return endPointBuilder;
    }
}
