/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
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
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.plugtests.resources;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;

import org.eclipse.californium.core.CoapExchange;
import org.eclipse.californium.core.CoapResource;

/**
 * This resource implements a test of specification for the
 * ETSI IoT CoAP Plugtests, London, UK, 7--9 Mar 2014.
 */
public class LocationQuery extends CoapResource {

	public LocationQuery() {
		super("location-query");
		getAttributes().setTitle("Perform POST transaction with responses containing several Location-Query options (CON mode)");
	}
	
	@Override
	public void handlePOST(CoapExchange exchange) {
		exchange.setLocationQuery("?first=1&second=2");
		exchange.respond(CREATED);
	}
	
}
