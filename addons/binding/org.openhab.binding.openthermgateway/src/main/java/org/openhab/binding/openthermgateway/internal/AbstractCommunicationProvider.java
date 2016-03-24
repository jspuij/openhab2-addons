/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for communication providers to communicate with an Opentherm Gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public abstract class AbstractCommunicationProvider implements CommunicationProvider {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(AbstractCommunicationProvider.class);

  /**
   * Initial transmission queue size.
   */
  private static final int INITIAL_TX_QUEUE_SIZE = 8;

  /**
   * Initial receive queue size.
   */
  private static final int INITIAL_RX_QUEUE_SIZE = 8;

  /**
   * Timeout in milliseconds before waking up the receive thread.
   */
  private static final int RECEIVE_TIMEOUT = 5000;

  /**
   * Thread for handling communication.
   */
  private Thread communicationThread;

  /**
   * Thread for sending events.
   */
  private Thread eventThread;

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
   * A set of event listeners.
   */
  private final Set<GatewayEventListener> eventListeners = new HashSet<GatewayEventListener>();

  /**
   * Starts communication with the gateway on the specified port.
   *
   * @param port the port to connect to.
   */
  @Override
  public final void start(final String port) {
    this.logger.trace("start() enter");

    this.eventThread = new EventThread();
    this.eventThread.start();

    this.onStart(port);

    this.logger.trace("start() exit");
  }

  /**
   * Method to be implemented by derived classes on startup.
   *
   * @param port the port to connect to.
   */
  public abstract void onStart(final String port);

  /**
   * Stops communication with the gateway.
   */
  @Override
  public final void stop() {
    this.logger.trace("stop() enter");

    this.onStop();

    if (this.eventThread != null) {
      this.logger.debug("Interrupting the event thread.");
      this.eventThread.interrupt();
      try {
        this.eventThread.join();
      } catch (InterruptedException e) {
      }
      this.eventThread = null;
    }

    this.logger.trace("stop() exit");
  }

  /**
   * Method to be implemented by derived classes on shutdown.
   */
  public abstract void onStop();

  /**
   * Enqueues an event for handling on another thread.
   *
   * @param gatewayEvent the event to enqueue.
   */
  protected final void enqueue(final GatewayEvent gatewayEvent) {
    this.logger.debug("Enqueuing event for handling: {}", gatewayEvent.toString());
    this.sendQueue.add(gatewayEvent);
  }

  /**
   * Adds a {@link GatewayEventListener} to the communication provider.
   *
   * @param gatewayEventListener the event listener.
   */
  @Override
  public final void addEventListener(final GatewayEventListener gatewayEventListener) {
    this.eventListeners.add(gatewayEventListener);
  }

  /**
   * Removes a {@link GatewayEventListener} from the communication provider.
   *
   * @param gatewayEventListener the event listener.
   */
  @Override
  public final void removeEventListener(final GatewayEventListener gatewayEventListener) {
    this.eventListeners.remove(gatewayEventListener);
  }

  /**
   * @return the communicationThread
   */
  protected final Thread getCommunicationThread() {
    return communicationThread;
  }

  /**
   * @param communicationThread the communicationThread to set
   */
  protected final void setCommunicationThread(final Thread communicationThread) {
    this.communicationThread = communicationThread;
  }

  /**
   * Thread that handles event posting.
   *
   * @author Jan-Willem Spuij
   *
   */
  private class EventThread extends Thread {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(EventThread.class);

    @Override
    public void run() {
      this.logger.debug("Starting Opentherm Gateway event thread");

      try {
        while (!interrupted()) {
          GatewayEvent event = sendQueue.poll(RECEIVE_TIMEOUT, TimeUnit.MILLISECONDS);

          if (event == null) {
            continue;
          }

          for (GatewayEventListener gatewayEventListener : eventListeners) {
            gatewayEventListener.receive(event);
          }
        }
      } catch (InterruptedException e) {

      } catch (Exception e) {
        this.logger.error("Exception while running Opentherm Gateway event thread", e);
      }

      this.logger.debug("Stopped Opentherm Gateway event thread.");

    }
  }
}
