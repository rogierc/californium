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
 * Configuration of notification parameters
 *
 */
public class NotificationParams implements VisitableConfig {

	/**
	 * The time in milliseconds [ms] that may pass sending only Non-Confirmable
	 * notifications to an observing client. After this period the first
	 * notification will be Confirmable to verify the client is listening. When
	 * this notification isn't acknowledged, the CoAP relation is considered
	 * stale and removed.
	 */

	private Long notificationCheckIntervalTime = null;

	/**
	 * The maximum number of notifications that may pass before a Confirmable
	 * notification must be sent to an observing client, to verify that this
	 * client is listening. When this notification isn't acknowledged, the CoAP
	 * relation is considered stale and removed.
	 */

	private Integer notificationCheckIntervalCount = null;

	/**
	 * The time a client waits for re-registration after Max-Age is expired.
	 */

	private Long notificationReregistrationBackoff = null;

	/**
	 * Accept a configuration visitor that processes this configuration-item.
	 */
	@Override
	public void accept(AbstractConfigVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the notificationCheckIntervalTime
	 */
	public Long getNotificationCheckIntervalTime() {
		return notificationCheckIntervalTime;
	}

	/**
	 * @param notificationCheckIntervalTime the notificationCheckIntervalTime to
	 *            set
	 */
	public void setNotificationCheckIntervalTime(Long notificationCheckIntervalTime) {
		this.notificationCheckIntervalTime = notificationCheckIntervalTime;
	}

	/**
	 * @return the notificationCheckIntervalCount
	 */
	public Integer getNotificationCheckIntervalCount() {
		return notificationCheckIntervalCount;
	}

	/**
	 * @param notificationCheckIntervalCount the notificationCheckIntervalCount
	 *            to set
	 */
	public void setNotificationCheckIntervalCount(Integer notificationCheckIntervalCount) {
		this.notificationCheckIntervalCount = notificationCheckIntervalCount;
	}

	/**
	 * @return the notificationReregistrationBackoff
	 */
	public Long getNotificationReregistrationBackoff() {
		return notificationReregistrationBackoff;
	}

	/**
	 * @param notificationReregistrationBackoff the
	 *            notificationReregistrationBackoff to set
	 */
	public void setNotificationReregistrationBackoff(Long notificationReregistrationBackoff) {
		this.notificationReregistrationBackoff = notificationReregistrationBackoff;
	}
}
