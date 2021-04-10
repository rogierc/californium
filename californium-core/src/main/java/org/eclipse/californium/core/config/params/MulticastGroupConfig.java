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

/**
 * Configuration of a Multicast group.
 */

public class MulticastGroupConfig
{
    /**
    * The name of the resource that will be used to identify it in CoAP uri's.
    */
    private String group= null;

    /**
    * The name of the resource that will be used to identify it in CoAP uri's.
    */

    private String networkInterface= null;
    
    public MulticastGroupConfig( String group, String networkInterface )
    {
        this.group= group;
        this.networkInterface= networkInterface;
    }

	
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	
	/**
	 * @return the networkInterface
	 */
	public String getNetworkInterface() {
		return networkInterface;
	}

	
	/**
	 * @param networkInterface the networkInterface to set
	 */
	public void setNetworkInterface(String networkInterface) {
		this.networkInterface = networkInterface;
	}
}
