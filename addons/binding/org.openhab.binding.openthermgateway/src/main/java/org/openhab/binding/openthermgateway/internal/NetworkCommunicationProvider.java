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

import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent;
import org.openhab.binding.openthermgateway.internal.events.ConnectionStateEvent.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Communicates with the Opentherm Gateway over a network connection.
 *
 * @author Jan-Willem Spuij
 *
 */
public class NetworkCommunicationProvider extends AbstractCommunicationProvider implements CommunicationProvider {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(NetworkCommunicationProvider.class);

    @Override
    public void start(String port) {
        logger.trace("start() enter");

        String[] parts = port.split(":");

        this.communicationThread = new CommunicationThread(parts[0], Integer.parseInt(parts[1]));
        this.communicationThread.start();

        logger.trace("start() exit");
    }

    @Override
    public void stop() {
        logger.trace("stop() enter");

        if (this.communicationThread != null) {
            logger.debug("Interrupting the communication thread.");
            this.communicationThread.interrupt();
            try {
                this.communicationThread.join();
            } catch (InterruptedException e) {
            }
            this.communicationThread = null;
        }

        logger.trace("stop() exit");
    }

    /**
     * Thread that handles communication and posts serial messages to a queue.
     *
     * @author Jan-Willem Spuij
     *
     */
    private class CommunicationThread extends Thread {

        /**
         * Host to connect to.
         */
        private String host;

        /**
         * Port to connect to.
         */
        private int port;

        /**
         * Initializes a new instance of the {@link CommunicationThread} class.
         *
         * @param host The host to connect to.
         * @param port The port to connect to.
         */
        public CommunicationThread(String host, int port) {
            this.host = host;
            this.port = port;
        }

        /**
         * Runs the communication loop.
         */
        @Override
        public void run() {
            logger.debug("Starting Opentherm Gateway communication thread");

            Socket socket = null;

            try {
                socket = new Socket(host, port);
            } catch (UnknownHostException e1) {
                logger.error("Unknown host: {}", host);
                Enqueue(new ConnectionStateEvent(ConnectionState.Failed, e1.getLocalizedMessage()));
                return;
            } catch (IOException e1) {
                logger.error("IOException received: {}", e1.getLocalizedMessage());
                Enqueue(new ConnectionStateEvent(ConnectionState.Failed, e1.getLocalizedMessage()));
                return;
            }

            try {
                while (!interrupted()) {
                }
            } catch (Exception e) {
                logger.error("Exception while running Opentherm Gateway communication thread", e);
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                socket = null;
            }

            logger.debug("Stopping Opentherm Gateway communication thread.");
        }
    }

}
