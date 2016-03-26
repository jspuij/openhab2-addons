/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.openthermgateway.internal;

import static org.openhab.binding.openthermgateway.OpenThermGatewayBindingConstants.THING_TYPE_GATEWAY;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.openthermgateway.handler.OpenThermGatewayHandler;

/**
 * The {@link OpenThermGatewayHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Jan-Willem Spuij - Initial contribution
 */
public final class OpenThermGatewayHandlerFactory extends BaseThingHandlerFactory {

  /**
   * Supported thing types.
   */
  private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections
      .singleton(THING_TYPE_GATEWAY);

  @Override
  public boolean supportsThingType(final ThingTypeUID thingTypeUID) {
    return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
  }

  /**
   * Creates a handler for the specified thing.
   *
   * @param thing a thing.
   * @return a thing handler for the thing.
   */
  @Override
  protected ThingHandler createHandler(final Thing thing) {

    ThingTypeUID thingTypeUID = thing.getThingTypeUID();

    if (thingTypeUID.equals(THING_TYPE_GATEWAY)) {
      return new OpenThermGatewayHandler((Bridge) thing);
    }

    return null;
  }
}
