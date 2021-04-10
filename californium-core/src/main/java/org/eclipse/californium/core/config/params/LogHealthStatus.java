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

/**
 * Configuration of Encryption parameters.
 *
 */
public class LogHealthStatus implements VisitableConfig {

	/**
	 * The interval of healthStatus logging in seconds [s].
	 */
	private Integer healthStatusInterval = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the healthStatusInterval
	 */
	public Integer getHealthStatusInterval() {
		return healthStatusInterval;
	}

	/**
	 * @param healthStatusInterval the healthStatusInterval to set
	 */
	public void setHealthStatusInterval(Integer healthStatusInterval) {
		this.healthStatusInterval = healthStatusInterval;
	}

}
