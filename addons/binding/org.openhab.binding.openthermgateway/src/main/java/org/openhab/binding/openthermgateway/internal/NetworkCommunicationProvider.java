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

  /**
   * The socket to use to communicate with the gateway.
   */
  private Socket socket = null;

  /**
   * Starts the network connection and opens sets the required streams.
   */
  @Override
  public void onStart(final String networkAddress) throws UnknownHostException, IOException {
    this.logger.trace("onStart() enter");

    String[] parts = networkAddress.split(":");
    String host = parts[0];
    int port = Integer.parseInt(parts[1]);

    socket = new Socket(host, port);

    this.setInputStream(socket.getInputStream());
    this.setOutputStream(socket.getOutputStream());

    this.logger.trace("onStart() exit");

  }

  /**
   * Stops the network connection.
   */
  @Override
  public void onStop() throws IOException {
    this.logger.trace("onStop() enter");

    if (socket != null) {
      if (!socket.isClosed()) {
        socket.close();
      }
      socket = null;
    }

    this.logger.trace("onStop() exit");
  }

}
