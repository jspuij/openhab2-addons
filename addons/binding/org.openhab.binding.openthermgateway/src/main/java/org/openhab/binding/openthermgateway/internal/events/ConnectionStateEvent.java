/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.events;

/**
 * Informs the gateway handler about the state of the connection.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class ConnectionStateEvent extends GatewayEvent {

  /**
   * The reason why the connection state occurred.
   */
  private final String reason;

  /**
   * The connection state.
   */
  private final ConnectionState connectionState;

  /**
   * Initializes a new instance of the {@link ConnectionStateEvent} class.
   *
   * @param connectionState the {@link ConnectionState}
   * @param reason the reason the state changed.
   */
  public ConnectionStateEvent(final ConnectionState connectionState, final String reason) {
    this.reason = reason;
    this.connectionState = connectionState;
  }

  /**
   * @return the reason
   */
  public String getReason() {
    return reason;
  }

  /**
   * @return the connectionState
   */
  public ConnectionState getConnectionState() {
    return connectionState;
  }

  /**
   * Connection states of the gateway.
   *
   * @author Jan-Willem Spuij
   *
   */
  public enum ConnectionState {
    /**
     * Not connected yet.
     */
    NotConnected,
    /**
     * Connected to the gateway.
     */
    Connected,
    /**
     * Something disconnected the gateway.
     */
    Disconnected,
    /**
     * Connection failed, do not reconnect.
     */
    Failed
  }

  @Override
  public String toString() {
    return String.format("(%s) %s", this.getConnectionState(), this.getReason());
  }
}