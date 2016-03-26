/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent.ConnectionState;
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
   * Thread for handling reading from a stream.
   */
  private Thread readThread;

  /**
   * Thread for handling writing to a stream.
   */
  private Thread sendThread;

  /**
   * read stream.
   */
  private InputStream inputStream;

  /**
   * write stream.
   */
  private OutputStream outputStream;

  /**
   * Thread for sending events.
   */
  private Thread eventThread;

  /**
   * Queue for sending protocol messages to the gateway.
   */
  private final LinkedBlockingQueue<GatewayEvent> sendQueue = new LinkedBlockingQueue<GatewayEvent>(
      INITIAL_TX_QUEUE_SIZE);

  /**
   * Queue for receiving protocol messages from the gateway.
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

    this.eventThread = new EventThread(this.recvQueue, this.eventListeners);
    this.eventThread.start();

    try {
      this.onStart(port);
    } catch (IOException e) {
      this.logger.error("An error occurred while trying to start the connection to the gateway.",
          e);
      this.recvQueue.add(new ConnectionStateEvent(ConnectionState.Failed, e.getLocalizedMessage()));
      return;
    }

    this.readThread = new ReceiveThread(this.inputStream, this.recvQueue);
    this.readThread.start();

    this.recvQueue.add(new ConnectionStateEvent(ConnectionState.Connected, ""));

    this.logger.trace("start() exit");
  }

  /**
   * Method to be implemented by derived classes on startup.
   * The derived method should set the input and output streams
   * using the setInputStream and setOutputStream methods.
   *
   * @param port the port to connect to.
   * @throws UnknownHostException the host is not known.
   * @throws IOException there was some sort of IO error.
   */
  public abstract void onStart(final String port) throws UnknownHostException, IOException;

  /**
   * Stops communication with the gateway.
   */
  @Override
  public final void stop() {
    this.logger.trace("stop() enter");

    // onStop has to close the socket. This is the only
    // way to stop the read thread.

    try {
      this.onStop();
    } catch (IOException e) {
    }

    if (this.readThread != null) {
      this.readThread = null;
    }

    if (this.eventThread != null) {
      this.logger.debug("Interrupting the event thread.");
      this.eventThread.interrupt();
      this.eventThread = null;
    }

    this.logger.trace("stop() exit");
  }

  /**
   * Method to be implemented by derived classes on shutdown.
   *
   * @throws IOException there was some sort of IO error.
   */
  public abstract void onStop() throws IOException;

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
   * Sets the output stream.
   *
   * @param outputStream the outputStream to set
   */
  protected final void setOutputStream(final OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  /**
   * Sets the input stream.
   *
   * @param inputStream the {@link InputStream} to set
   */
  protected final void setInputStream(final InputStream inputStream) {
    this.inputStream = inputStream;
  }

}
