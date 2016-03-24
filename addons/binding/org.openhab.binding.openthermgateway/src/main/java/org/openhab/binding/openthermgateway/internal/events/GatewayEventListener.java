/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.openthermgateway.internal.events;

/**
 * Interface to implement on all classes that want to
 * receive events from the gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public interface GatewayEventListener {

  /**
   * This method gets called when the gateway sends an
   * event.
   *
   * @param gatewayEvent the event from the gateway.
   */
  void receive(GatewayEvent gatewayEvent);

}
