/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent.ConnectionState;
import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.openhab.binding.openthermgateway.internal.events.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread that handles reading and posts serial messages to a queue.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class ReceiveThread extends Thread {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(ReceiveThread.class);

  /**
   * Stream to read from.
   */
  private final InputStream stream;

  /**
   * Queue to post to.
   */
  private final BlockingQueue<GatewayEvent> queue;

  /**
   * Initializes a new instance of the {@link CommunicationThread} class.
   *
   * @param stream The stream to read from.
   * @param queue The queue to post events to.
   */
  public ReceiveThread(final InputStream stream, final BlockingQueue<GatewayEvent> queue) {
    this.stream = stream;
    this.queue = queue;
  }

  /**
   * Runs the communication loop.
   */
  @Override
  public void run() {
    this.logger.debug("Starting Opentherm Gateway read thread");
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(stream, "US-ASCII"));
      while (!interrupted()) {
        String data = reader.readLine();
        logger.debug("Got data: {}", data);
        this.queue.add(new MessageEvent(data));
      }
    } catch (IOException e) {
      this.logger.debug("Disconnected while running Opentherm Gateway read thread",
          e.getLocalizedMessage());
      this.queue
          .add(new ConnectionStateEvent(ConnectionState.Disconnected, e.getLocalizedMessage()));
    } catch (Exception e) {
      this.logger.error("Exception while running Opentherm Gateway read thread", e);
      this.queue.add(new ConnectionStateEvent(ConnectionState.Failed, e.getLocalizedMessage()));
    } finally {
      if (reader != null) {
        try {
          reader.close();
          reader = null;
        } catch (IOException e) {
        }
      }
    }

    this.logger.debug("Stopped Opentherm Gateway read thread.");
  }
}