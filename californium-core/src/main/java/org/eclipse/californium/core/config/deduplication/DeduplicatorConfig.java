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

import org.eclipse.californium.core.config.VisitableConfig;

/**
 * Deduplucator algorithm interface.
 * 
 */
public abstract class DeduplicatorConfig implements VisitableConfig {

	/**
	 * @return the name of the deduplicator algorithm
	 */
	public String name() {
		return this.getClass().getSimpleName();
	}
}
