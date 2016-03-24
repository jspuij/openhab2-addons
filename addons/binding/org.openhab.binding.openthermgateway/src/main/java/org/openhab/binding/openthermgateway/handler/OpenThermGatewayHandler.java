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
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.openthermgateway.internal.CommunicationProvider;
import org.openhab.binding.openthermgateway.internal.NetworkCommunicationProvider;
import org.openhab.binding.openthermgateway.internal.SerialCommunicationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link OpenThermGatewayHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Jan-Willem Spuij - Initial contribution
 */
public class OpenThermGatewayHandler extends BaseThingHandler {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(OpenThermGatewayHandler.class);

    /**
     * Communication provider for the Opentherm Gateway.
     */
    private CommunicationProvider communicationProvider;

    /**
     * Creates a new instance of this class for the {@link Thing}.
     *
     * @param thing
     *            thing
     */
    public OpenThermGatewayHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (channelUID.getId().equals(CHANNEL_GATEWAY_VERSION)) {
            // TODO: handle command
        }
    }

    /**
     * Starts initialization of the Opentherm Gateway.
     */
    @Override
    public void initialize() {
        logger.trace("initialize() enter.");
        String port = (String) getConfig().get(CONFIGURATION_PORT);

        if (port == null || port.length() == 0) {
            logger.error("Opentherm Gateway port is not set.");
            return;
        }

        String[] parts = port.split(":");

        if (parts.length == 2) {
            communicationProvider = new NetworkCommunicationProvider();
        } else if (parts.length == 1) {
            communicationProvider = new SerialCommunicationProvider();
        } else {
            logger.error("The configuration of the Opentherm Gateway has an incorrect value for parameter 'port'.");
            return;
        }

        communicationProvider.start(port);
        logger.trace("initialize() exit.");
    }

    /**
     * Disposes of the thing. Calls the communication provider to stop.
     */
    @Override
    public void dispose() {
        logger.trace("dispose() enter.");
        communicationProvider.stop();
        super.dispose();
        logger.trace("dispose() exit.");
    }
}
