/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Communicates with the Opentherm Gateway over a network connection.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class NetworkCommunicationProvider extends AbstractCommunicationProvider
    implements CommunicationProvider {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(NetworkCommunicationProvider.class);

  @Override
  public void onStart(final String port) {
    this.logger.trace("onStart() enter");

    String[] parts = port.split(":");

    this.setCommunicationThread(new CommunicationThread(parts[0], Integer.parseInt(parts[1])));
    this.getCommunicationThread().start();

    this.logger.trace("onStart() exit");
  }

  @Override
  public void onStop() {
    this.logger.trace("onStop() enter");

    if (this.getCommunicationThread() != null) {
      this.logger.debug("Interrupting the communication thread.");
      this.getCommunicationThread().interrupt();
      try {
        this.getCommunicationThread().join();
      } catch (InterruptedException e) {
      }
      this.setCommunicationThread(null);
    }

    this.logger.trace("onStop() exit");
  }

  /**
   * Thread that handles communication and posts serial messages to a queue.
   *
   * @author Jan-Willem Spuij
   *
   */
  private class CommunicationThread extends Thread {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(CommunicationThread.class);

    /**
     * Host to connect to.
     */
    private final String host;

    /**
     * Port to connect to.
     */
    private final int port;

    /**
     * Initializes a new instance of the {@link CommunicationThread} class.
     *
     * @param host The host to connect to.
     * @param port The port to connect to.
     */
    CommunicationThread(final String host, final int port) {
      this.host = host;
      this.port = port;
    }

    /**
     * Runs the communication loop.
     */
    @Override
    public void run() {
      this.logger.debug("Starting Opentherm Gateway communication thread");

      Socket socket = null;

      try {
        socket = new Socket(host, port);
      } catch (UnknownHostException e1) {
        this.logger.error("Unknown host: {}", host);
        enqueue(new ConnectionStateEvent(ConnectionState.Failed, e1.getLocalizedMessage()));
        return;
      } catch (IOException e1) {
        this.logger.error("IOException received: {}", e1.getLocalizedMessage());
        enqueue(new ConnectionStateEvent(ConnectionState.Failed, e1.getLocalizedMessage()));
        return;
      }

      this.logger.debug("Connected to {}:{}", host, port);
      enqueue(new ConnectionStateEvent(ConnectionState.Connected, ""));

      try {
        while (!interrupted()) {
        }
      } catch (Exception e) {
        this.logger.error("Exception while running Opentherm Gateway communication thread", e);
      }

      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
        }
        socket = null;
      }

      this.logger.debug("Stopped Opentherm Gateway communication thread.");
    }
  }
}
