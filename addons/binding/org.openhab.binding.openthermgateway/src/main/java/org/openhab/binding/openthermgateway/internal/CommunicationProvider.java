/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal;

import org.openhab.binding.openthermgateway.internal.events.GatewayEventListener;

/**
 * Interface that defines communication with an Opentherm Gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public interface CommunicationProvider {

  /**
   * Starts communication with the gateway on the specified port.
   *
   * @param port the port to connect to.
   */
  void start(String port);

  /**
   * Stops communication with the gateway.
   */
  void stop();

  /**
   * Adds a {@link GatewayEventListener} to the communication provider.
   *
   * @param gatewayEventListener the event listener.
   */
  void addEventListener(GatewayEventListener gatewayEventListener);

  /**
   * Removes a {@link GatewayEventListener} from the communication provider.
   *
   * @param gatewayEventListener the event listener.
   */
  void removeEventListener(GatewayEventListener gatewayEventListener);
}
