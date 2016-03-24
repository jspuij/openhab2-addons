/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.openthermgateway.internal;

import java.util.concurrent.LinkedBlockingQueue;

import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for communication providers to communicate with an Opentherm Gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public class AbstractCommunicationProvider {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(AbstractCommunicationProvider.class);

    /**
     * Initial transmission queue size.
     */
    private static final int INITIAL_TX_QUEUE_SIZE = 8;

    /**
     * Initial receive queue size.
     */
    private static final int INITIAL_RX_QUEUE_SIZE = 8;

    /**
     * Thread for handling communication.
     */
    protected Thread communicationThread;

    /**
     * Thread for sending events.
     */
    protected Thread eventThread;

    /**
     * Queue for sending protocol messages.
     */
    private final LinkedBlockingQueue<GatewayEvent> sendQueue = new LinkedBlockingQueue<GatewayEvent>(
            INITIAL_TX_QUEUE_SIZE);

    /**
     * Queue for receiving protocol messages.
     */
    private final LinkedBlockingQueue<GatewayEvent> recvQueue = new LinkedBlockingQueue<GatewayEvent>(
            INITIAL_RX_QUEUE_SIZE);

    /**
     * Enqueues an event for handling on another thread.
     *
     * @param gatewayEvent the event to enqueue.
     */
    protected void Enqueue(GatewayEvent gatewayEvent) {
        this.logger.debug("Enqueuing event for handling: {}", gatewayEvent.toString());
        this.sendQueue.add(gatewayEvent);
    }

}
