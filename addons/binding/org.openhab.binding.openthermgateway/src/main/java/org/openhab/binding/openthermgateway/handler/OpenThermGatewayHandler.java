/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.handler;

import static org.openhab.binding.openthermgateway.OpenThermGatewayBindingConstants.*;

import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.openthermgateway.internal.CommunicationProvider;
import org.openhab.binding.openthermgateway.internal.NetworkCommunicationProvider;
import org.openhab.binding.openthermgateway.internal.SerialCommunicationProvider;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link OpenThermGatewayHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jan-Willem Spuij - Initial contribution
 */
public final class OpenThermGatewayHandler extends BaseThingHandler
    implements GatewayEventListener {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(OpenThermGatewayHandler.class);

  /**
   * Communication provider for the Opentherm Gateway.
   */
  private CommunicationProvider communicationProvider;

  /**
   * Creates a new instance of this class for the {@link Thing}.
   *
   * @param thing
   *          thing
   */
  public OpenThermGatewayHandler(final Thing thing) {
    super(thing);
  }

  @Override
  public void handleCommand(final ChannelUID channelUID, final Command command) {
    if (channelUID.getId().equals(CHANNEL_GATEWAY_VERSION)) {
      // TODO: handle command
    }
  }

  /**
   * Starts initialization of the Opentherm Gateway.
   */
  @Override
  public void initialize() {
    this.logger.trace("initialize() enter.");
    String port = (String) this.getConfig().get(CONFIGURATION_PORT);

    if (port == null || port.length() == 0) {
      this.logger.error("Opentherm Gateway port is not set.");
      return;
    }

    String[] parts = port.split(":");

    if (parts.length == 2) {
      this.communicationProvider = new NetworkCommunicationProvider();
    } else if (parts.length == 1) {
      this.communicationProvider = new SerialCommunicationProvider();
    } else {
      this.logger.error(
          "The configuration of the Opentherm Gateway has an incorrect value for parameter 'port'.");
      return;
    }

    this.communicationProvider.addEventListener(this);
    this.communicationProvider.start(port);
    this.logger.trace("initialize() exit.");
  }

  /**
   * Disposes of the thing. Calls the communication provider to stop.
   */
  @Override
  public void dispose() {
    this.logger.trace("dispose() enter.");
    if (this.communicationProvider != null) {
      this.communicationProvider.stop();
      this.communicationProvider.removeEventListener(this);
      this.communicationProvider = null;
    }
    super.dispose();
    this.logger.trace("dispose() exit.");
  }

  @Override
  public void receive(final GatewayEvent gatewayEvent) {
    // TODO: make dynamic.
    if (gatewayEvent instanceof ConnectionStateEvent) {
      ConnectionStateEvent connectionStateEvent = (ConnectionStateEvent) gatewayEvent;

      switch (connectionStateEvent.getConnectionState()) {
        case Connected:
          updateStatus(ThingStatus.ONLINE);
          break;
        case Disconnected:
          updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR);
          break;
        case Failed:
          updateStatus(ThingStatus.UNINITIALIZED, ThingStatusDetail.CONFIGURATION_ERROR);
          break;
        case NotConnected:
        default:
          break;

      }
    }
  }
}
