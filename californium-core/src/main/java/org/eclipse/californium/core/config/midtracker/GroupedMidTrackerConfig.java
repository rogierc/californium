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

package org.eclipse.californium.core.config.midtracker;

import org.eclipse.californium.core.config.AbstractConfigVisitor;

/**
 * Configuration for the GROUPED mid tracker algorithm
 *
 */
public class GroupedMidTrackerConfig extends MidTrackerConfig {

	/**
	 * The default number of MID groups. Used for {@code GROUPED} message Id
	 * tracker.
	 */
	private Integer midTrackerGroups = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the name of the Midtracker algoritm
	 */
	@Override
	public String name() {
		return this.getClass().getSimpleName();
	}

	
	/**
	 * @return the midTrackerGroups
	 */
	public Integer getMidTrackerGroups() {
		return midTrackerGroups;
	}

	
	/**
	 * @param midTrackerGroups the midTrackerGroups to set
	 */
	public void setMidTrackerGroups(Integer midTrackerGroups) {
		this.midTrackerGroups = midTrackerGroups;
	}
}
