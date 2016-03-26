/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.events;

/**
 * Informs the gateway handler about a newly arrived message.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class MessageEvent extends GatewayEvent {

  /**
   * The message received by the gateway.
   */
  private final String message;

  /**
   * Initializes a new instance of the {@link MessageEvent} class.
   *
   * @param message the message received.
   */
  public MessageEvent(final String message) {
    super();
    this.message = message;
  }

  /**
   * Gets the message.
   *
   * @return the message.
   */
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
