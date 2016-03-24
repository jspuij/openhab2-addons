/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link OpenThermGatewayBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Jan-Willem Spuij - Initial contribution
 */
public final class OpenThermGatewayBindingConstants {

  /**
   * Initializes a new instance of the
   * {@ link OpenThermGatewayBindingConstants} class.
   */
  private OpenThermGatewayBindingConstants() {

  }

  /**
   * Binding id.
   */
  public static final String BINDING_ID = "openthermgateway";

  /**
   * Configuration port.
   */
  public static final String CONFIGURATION_PORT = "port";

  /**
   * List of all Thing Type UIDs.
   */
  public static final ThingTypeUID THING_TYPE_GATEWAY = new ThingTypeUID(BINDING_ID, "gateway");

  /**
   * Gateway version.
   */
  public static final String CHANNEL_GATEWAY_VERSION = "channelGatewayVersion";

}
