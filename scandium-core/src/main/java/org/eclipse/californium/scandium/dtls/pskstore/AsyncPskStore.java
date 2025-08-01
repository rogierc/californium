/*******************************************************************************
 * Copyright (c) 2020 Bosch.IO GmbH and others.
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
 *    Bosch.IO GmbH - initial creation
 ******************************************************************************/
package org.eclipse.californium.scandium.dtls.pskstore;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.eclipse.californium.elements.util.DaemonThreadFactory;
import org.eclipse.californium.elements.util.ExecutorsUtil;
import org.eclipse.californium.elements.util.NamedThreadFactory;
import org.eclipse.californium.scandium.dtls.ConnectionId;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;
import org.eclipse.californium.scandium.dtls.PskPublicInformation;
import org.eclipse.californium.scandium.dtls.PskSecretResult;
import org.eclipse.californium.scandium.util.SecretUtil;
import org.eclipse.californium.scandium.util.ServerNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Asynchronous test implementation using a provided {@link PskStore}.
 * <p>
 * Use {@code 0} or negative delays for test with synchronous blocking
 * behavior. And positive delays for test with asynchronous none-blocking
 * behavior.
 * 
 * @since 4.0 (Renamed AsyncAdvancedPskStore into AsyncPskStore)
 */
public class AsyncPskStore implements PskStore {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncPskStore.class);

	/**
	 * Thread factory.
	 */
	private static final NamedThreadFactory THREAD_FACTORY = new DaemonThreadFactory("AsyncPskStoreTimer#", NamedThreadFactory.SCANDIUM_THREAD_GROUP);
	/**
	 * Executor for asynchronous behaviour.
	 */
	private final ScheduledExecutorService executorService;
	/**
	 * PSK store.
	 */
	private final PskStore pskStore;
	/**
	 * Delay for psk result. {@code 0} or negative delays for test with
	 * synchronous blocking behaviour. Positive delays for test with
	 * asynchronous none-blocking behaviour.
	 */
	private volatile int delayMillis = 1;
	/**
	 * {@code true} to return generated master secret, {@code false} for PSK
	 * secret key.
	 */
	private volatile boolean generateMasterSecret;
	/**
	 * Result handler set during initialization.
	 * 
	 * @see #setResultHandler(HandshakeResultHandler)
	 */
	private volatile HandshakeResultHandler resultHandler;

	/**
	 * Create an asynchronous pskstore from {@link PskStore}.
	 * <p>
	 * A call to {@link #shutdown()} is required to cleanup the used resources
	 * (executor).
	 * 
	 * @param pskStore psk store
	 */
	public AsyncPskStore(PskStore pskStore) {
		this.pskStore = pskStore;
		executorService = ExecutorsUtil.newSingleThreadScheduledExecutor(THREAD_FACTORY); // $NON-NLS-1$
	}

	/**
	 * Set secret mode.
	 * 
	 * @param enableGenerateMasterSecret {@code true} to return generated master
	 *            secret, {@code false} for PSK secret key.
	 * @return this psk store for command chaining
	 */
	public AsyncPskStore setSecretMode(boolean enableGenerateMasterSecret) {
		this.generateMasterSecret = enableGenerateMasterSecret;
		return this;
	}

	/**
	 * Set delay milliseconds.
	 * 
	 * @param delayMillis delay in milliseconds to report result. {@code 0} or
	 *            negative delays using synchronous blocking behaviour. Positive
	 *            delays using asynchronous none-blocking behaviour.
	 * @return this psk store for command chaining
	 */
	public AsyncPskStore setDelay(int delayMillis) {
		this.delayMillis = delayMillis;
		if (delayMillis > 0) {
			LOGGER.info("Asynchronous delayed PSK store {}ms.", delayMillis);
		} else if (delayMillis < 0) {
			LOGGER.info("Synchronous delayed PSK store {}ms.", -delayMillis);
		} else {
			LOGGER.info("Synchronous PSK store.");
		}
		return this;
	}

	/**
	 * Get delay milliseconds.
	 * 
	 * @return delay milliseconds. {@code 0} or negative delays using
	 *         synchronous blocking behaviour. Positive delays using
	 *         asynchronous none-blocking behaviour.
	 */
	public int getDelay() {
		return delayMillis;
	}

	/**
	 * Shutdown. Cleanup resources.
	 */
	public void shutdown() {
		executorService.shutdown();
	}

	@Override
	public PskSecretResult requestPskSecretResult(final ConnectionId cid, final ServerNames serverNames,
			final PskPublicInformation identity, final String hmacAlgorithm, SecretKey otherSecret, byte[] seed, final boolean useExtendedMasterSecret) {
		if (delayMillis <= 0) {
			if (delayMillis < 0) {
				try {
					Thread.sleep(-delayMillis);
				} catch (InterruptedException e) {
				}
			}
			return getPskSecretResult(cid, serverNames, identity, hmacAlgorithm, otherSecret, seed, useExtendedMasterSecret);
		} else {
			final byte[] randomSeed = Arrays.copyOf(seed, seed.length);
			final SecretKey other = SecretUtil.create(otherSecret);
			executorService.schedule(new Runnable() {

				@Override
				public void run() {
					getSecretAsynchronous(cid, serverNames, identity, hmacAlgorithm, other, randomSeed, useExtendedMasterSecret);
				}
			}, delayMillis, TimeUnit.MILLISECONDS);
			return null;
		}
	}

	/**
	 * Get secret asynchronous and forward it to the {@link #resultHandler}.
	 * 
	 * @param cid connection id for stateless asynchronous implementations.
	 * @param serverNames server names. Maybe {@code null}, if SNI is not enabled
	 *            or not used by the client.
	 * @param identity psk identity. Maybe normalized
	 * @param hmacAlgorithm HMAC algorithm name for PRF.
	 * @param otherSecret other secret from ECDHE, or {@code null}. Must be
	 *            cloned for asynchronous use.
	 * @param seed seed for PRF.
	 * @param useExtendedMasterSecret  {@code true}, if the extended master secret (RFC 7627) is to be used, {@code false}, if the master secret (RFC 5246) is to be used.
	 */
	private void getSecretAsynchronous(ConnectionId cid, ServerNames serverNames, PskPublicInformation identity,
			String hmacAlgorithm, SecretKey otherSecret, byte[] seed, boolean useExtendedMasterSecret) {
		PskSecretResult result = getPskSecretResult(cid, serverNames, identity, hmacAlgorithm, otherSecret, seed, useExtendedMasterSecret);
		resultHandler.apply(result);
	}

	/**
	 * Get psk secret result.
	 * 
	 * Depending on {@link #generateMasterSecret}, either the generated master
	 * secret (algorithm "MAC"), or a PSK secret key (algorithm "PSK") is
	 * included in the result.
	 * 
	 * @param cid connection id for stateless asynchronous implementations.
	 * @param serverNames server names. Maybe {@code null}, if SNI is not enabled
	 *            or not used by the client.
	 * @param identity psk identity. Maybe normalized
	 * @param hmacAlgorithm HMAC algorithm name for PRF.
	 * @param otherSecret other secret from ECDHE, or {@code null}.
	 * @param seed seed for PRF.
	 * @param useExtendedMasterSecret  {@code true}, if the extended master secret (RFC 7627) is to be used, {@code false}, if the master secret (RFC 5246) is to be used.
	 * @return psk secret result
	 */
	private PskSecretResult getPskSecretResult(ConnectionId cid, ServerNames serverNames, PskPublicInformation identity,
			String hmacAlgorithm, SecretKey otherSecret, byte[] seed, boolean useExtendedMasterSecret) {
		PskSecretResult result = pskStore.requestPskSecretResult(cid, serverNames, identity, hmacAlgorithm, otherSecret,
				seed, useExtendedMasterSecret);
		if (generateMasterSecret && result.isPskSecret()) {
			SecretKey masterSecret = result.generateMasterSecret(hmacAlgorithm, otherSecret, seed,
					useExtendedMasterSecret);
			SecretUtil.destroy(result);
			return new PskSecretResult(cid, result.getPskPublicInformation(), masterSecret, true, true);
		}
		return result;
	}

	@Override
	public void setResultHandler(HandshakeResultHandler resultHandler) {
		if (this.resultHandler != null && resultHandler != null && this.resultHandler != resultHandler) {
			throw new IllegalStateException("handshake result handler already set!");
		}
		this.resultHandler = resultHandler;
	}

	@Override
	public boolean hasEcdhePskSupported() {
		return true;
	}

	@Override
	public PskPublicInformation getIdentity(InetSocketAddress peerAddress, ServerNames virtualHost) {
		return pskStore.getIdentity(peerAddress, virtualHost);
	}
}
