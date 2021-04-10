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

package org.eclipse.californium.core.config.deduplication;

import org.eclipse.californium.core.config.AbstractConfigVisitor;

/**
 * Mark and swweep deduplication configuration.
 *
 */
public class MarkAndSweepConfig extends DeduplicatorConfig {

	/**
	 * The period of MARK_AND_SWEEP deduplicators cleanup cycle in milliseconds
	 * [ms].
	 */
	private Long markAndSweepInterval = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the markAndSweepInterval
	 */
	public Long getMarkAndSweepInterval() {
		return markAndSweepInterval;
	}

	/**
	 * @param markAndSweepInterval the markAndSweepInterval to set
	 */
	public void setMarkAndSweepInterval(Long markAndSweepInterval) {
		this.markAndSweepInterval = markAndSweepInterval;
	}
}
