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


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.config.params.MulticastGroupConfig;
import org.eclipse.californium.core.config.params.MulticastParams;
import org.eclipse.californium.core.config.params.SocketParams;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.elements.UdpMulticastConnector;

/**
 * Configuration visitor that collects multi-cast UDP Endpoint configuration
 *
 */
public class MulticastUdpEndpointConfigVisitor extends UdpEndpointConfigVisitor
{
    /**
     * The UDP MulticasConnetor Builder that collects relevant configuration and will be used to build the connector.
     */
    private UdpMulticastConnector.Builder connectorBuilder= new UdpMulticastConnector.Builder();

    /**
     * Network interface to use for multicast.
     */
    private String multiCastNetworkInterfaceConfig= null;

    /**
     * The configured multicast groups.
     */
    private List< MulticastGroupConfig > joinMulticastGroups= null;

    /**
     * The configured disable loopback flag, if any.
     */
    private boolean disableLoopback= false;

    /**
     * The configured interface for outgoing multicast traffic, if any.
     */
    private String outgoingInterface= null;

    /**
     * The configured address indicating the interface for outgoing multicast traffic, if any.
     */
    private String outgoingAddress= null;


    @Override
    public void visit( MulticastParams toVisit )
    {
        super.visit( toVisit );
        joinMulticastGroups= toVisit.getJoin();
        disableLoopback= toVisit.getOutgoingMulticastConfig().getDisableLoopback();
        outgoingInterface= toVisit.getOutgoingMulticastConfig().getOutgoingInterface();
        outgoingAddress= toVisit.getOutgoingMulticastConfig().getOutgoingAddress();
    }

    @Override
    public void visit( SocketParams toVisit )
    {
        //Do not call super because UDP endpoint bind configuration is not allowed by Cf when connector is is set.  
        //super.visit( toVisit );
        int port= ( toVisit.getBindToPort() != null ? toVisit.getBindToPort() : CoAP.DEFAULT_COAP_PORT );

        if ( toVisit.getBindToHost() != null )
        {
            connectorBuilder.setLocalAddress( new InetSocketAddress( toVisit.getBindToHost(), port ) );
        }
        else
        {
            connectorBuilder.setLocalAddress( new InetSocketAddress( port ) );
        }
    }

    @Override
    public CoapEndpoint.Builder getEndpointBuilder() throws EndpointConstructionException
    {
        if ( outgoingInterface != null )
        {
            try
            {
                connectorBuilder.setOutgoingMulticastInterface( NetworkInterface.getByName(  outgoingInterface ));
            }
            catch ( SocketException e )
            {
                throw new EndpointConstructionException(
                    "Endpoint construction failed. Outgoing network interface { " + outgoingInterface + " } is invalid.",
                    e );
            }
        }
        if ( outgoingAddress != null )
        {
            try
            {
                connectorBuilder.setOutgoingMulticastInterface( InetAddress.getByName(  outgoingAddress ));
            }
            catch ( UnknownHostException e )
            {
                throw new EndpointConstructionException(
                    "CoAP Endpoint { " + getEndpointName() + " } construction failed. Outgoing network address { " + outgoingAddress + " } is invalid.",
                    e );
            }
        }
        if ( joinMulticastGroups != null )
        {
            for ( MulticastGroupConfig groupConfig : joinMulticastGroups )
            {
                NetworkInterface networkInterface;
                if ( groupConfig.getNetworkInterface() == null )
                {
                    networkInterface= null;
                }
                else
                {
                    try
                    {
                        networkInterface= NetworkInterface.getByName( groupConfig.getNetworkInterface() );
                    }
                    catch ( SocketException e )
                    {
                        throw new EndpointConstructionException(
                            "CoAP Endpoint { " + getEndpointName() + " } construction failed. Network interface { " + multiCastNetworkInterfaceConfig + " } is invalid.",
                            e );
                    }
                }
                InetAddress groupAddress;
                try
                {
                    groupAddress= InetAddress.getByName( groupConfig.getGroup() );
                }
                catch ( UnknownHostException e )
                {
                    throw new EndpointConstructionException( "CoAP Endpoint { " + getEndpointName() + " } construction failed. Multicast group { " + groupConfig + " } is invalid.", e );
                }
                connectorBuilder.addMulticastGroup( groupAddress, networkInterface );
            }
        }
        endPointBuilder.setNetworkConfig( this.getNetworkConfig() );
        UdpMulticastConnector connector= connectorBuilder.build();
        connector.setLoopbackMode( disableLoopback );
        endPointBuilder.setConnectorWithAutoConfiguration( connector );
        return endPointBuilder;
    }
}
