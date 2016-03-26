/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.handler;

import static org.openhab.binding.openthermgateway.OpenThermGatewayBindingConstants.*;

import java.util.Hashtable;

import org.eclipse.smarthome.config.discovery.DiscoveryService;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.openthermgateway.discovery.OpenThermDiscoveryService;
import org.openhab.binding.openthermgateway.internal.communication.CommunicationProvider;
import org.openhab.binding.openthermgateway.internal.communication.NetworkCommunicationProvider;
import org.openhab.binding.openthermgateway.internal.communication.SerialCommunicationProvider;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEvent;
import org.openhab.binding.openthermgateway.internal.events.GatewayEventListener;
import org.openhab.binding.openthermgateway.internal.events.MessageEvent;
import org.openhab.binding.openthermgateway.internal.protocol.opentherm.OpenThermFrame;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link OpenThermGatewayHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jan-Willem Spuij - Initial contribution
 */
public final class OpenThermGatewayHandler extends BaseBridgeHandler
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
   * Discovery service.
   */
  private OpenThermDiscoveryService discoveryService;

  /**
   * Discovery service registration.
   */
  private ServiceRegistration discoveryServiceRegistration;

  /**
   * Creates a new instance of this class for the {@link Bridge}.
   *
   * @param bridge
   *          the opentherm gateway bridge
   */
  public OpenThermGatewayHandler(final Bridge bridge) {
    super(bridge);
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

    this.discoveryService = new OpenThermDiscoveryService(this);
    this.discoveryServiceRegistration = this.bundleContext.registerService(DiscoveryService.class,
        this.discoveryService, new Hashtable<String, Object>());

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

    if (this.discoveryService != null) {
      this.discoveryServiceRegistration.unregister();
      this.discoveryService = null;
    }
    super.dispose();
    this.logger.trace("dispose() exit.");
  }

  @Override
  public void receive(final GatewayEvent gatewayEvent) {
    // TODO: make dynamic.
    if (gatewayEvent instanceof ConnectionStateEvent) {
      ConnectionStateEvent connectionStateEvent = (ConnectionStateEvent) gatewayEvent;
      logger.debug("Received ConnectionStateEvent: {}", connectionStateEvent);

      switch (connectionStateEvent.getConnectionState()) {
        case Connected:
          updateStatus(ThingStatus.ONLINE);
          break;
        case Disconnected:
          updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR);
          this.communicationProvider.stop();
          break;
        case Failed:
          updateStatus(ThingStatus.UNINITIALIZED, ThingStatusDetail.CONFIGURATION_ERROR);
          this.communicationProvider.stop();
          break;
        case NotConnected:
        default:
          break;

      }
    } else if (gatewayEvent instanceof MessageEvent) {
      MessageEvent messageEvent = (MessageEvent) gatewayEvent;
      logger.debug("Received MessageEvent: {}", messageEvent);

      // maybe the message is an opentherm frame.
      OpenThermFrame frame = OpenThermFrame.parseString(messageEvent.getMessage());

      if (frame != null) {
        logger.debug("Message is a frame: {}", frame.toString());
      }
    }
  }
}
