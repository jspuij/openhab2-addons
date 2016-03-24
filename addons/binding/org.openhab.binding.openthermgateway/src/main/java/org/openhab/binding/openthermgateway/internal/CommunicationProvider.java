/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.openthermgateway.internal;

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
     * @param port
     */
    void start(String port);

    /**
     * Stops communication with the gateway.
     */
    void stop();

}
