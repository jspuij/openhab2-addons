/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.communication;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread that handles event posting.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class EventThread extends Thread {

  /**
   * Timeout in milliseconds before waking up the receive thread.
   */
  private static final int RECEIVE_TIMEOUT = 5000;

  /**
   * Queue to use to read events.
   */
  private final BlockingQueue<GatewayEvent> queue;

  /***
   * Set of listeners to send events to.
   */
  private final Set<GatewayEventListener> listeners;

  /**
   * Initializes a new instance of the {@link EventThread} class.
   *
   * @param queue the {@link BlockingQueue} to use to send events.
   * @param listeners the {@link GatewayEventListener} instances to send the event to.
   */
  public EventThread(final BlockingQueue<GatewayEvent> queue,
      final Set<GatewayEventListener> listeners) {
    this.queue = queue;
    this.listeners = listeners;
  }

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(EventThread.class);

  @Override
  public void run() {
    this.logger.debug("Starting Opentherm Gateway event thread.");

    try {
      while (!interrupted()) {
        GatewayEvent event = queue.poll(RECEIVE_TIMEOUT, TimeUnit.MILLISECONDS);

        if (event == null) {
          continue;
        }

        for (GatewayEventListener gatewayEventListener : listeners) {
          gatewayEventListener.receive(event);
        }
      }
    } catch (InterruptedException e) {

    } catch (Exception e) {
      this.logger.error("Exception while running Opentherm Gateway event thread.", e);
    }

    this.logger.debug("Stopped Opentherm Gateway event thread.");

  }
}