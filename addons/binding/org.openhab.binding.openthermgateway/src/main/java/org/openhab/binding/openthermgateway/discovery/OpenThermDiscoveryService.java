/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.discovery;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.openhab.binding.openthermgateway.handler.OpenThermGatewayHandler;

/**
 * Discovery service. Provides discovery of the master and slave things that
 * are connected to the gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public class OpenThermDiscoveryService extends AbstractDiscoveryService {

  /**
   * Discovery timeout.
   */
  private static final int DISCOVERY_TIMEOUT = 30;

  /**
   * Opentherm gateway handler.
   */
  private final OpenThermGatewayHandler openThermGatewayHandler;

  /**
   * Initializes a new instance of the {@link OpenThermDiscoveryService} class.
   *
   * @param openThermGatewayHandler Opentherm gateway handler.
   * @throws IllegalArgumentException thrown when supplied with an illegal argument.
   */
  public OpenThermDiscoveryService(final OpenThermGatewayHandler openThermGatewayHandler)
      throws IllegalArgumentException {
    super(DISCOVERY_TIMEOUT);
    this.openThermGatewayHandler = openThermGatewayHandler;
  }

  @Override
  protected void startScan() {

  }

}
