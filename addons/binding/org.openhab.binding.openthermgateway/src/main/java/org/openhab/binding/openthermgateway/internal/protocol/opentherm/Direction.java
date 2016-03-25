/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.util.HashMap;
import java.util.Map;

/**
 * Direction in which the frame is flowing.
 *
 * @author Jan-Willem Spuij
 *
 */
public enum Direction {

  /**
   * From Boiler.
   */
  BOILER_TO_THERMOSTAT('B', "From Boiler"),

  /**
   * From Thermostat.
   */
  THERMOSTAT_TO_BOILER('T', "From Thermostat"),

  /**
   * Intercepted From Gateway to Boiler.
   */
  INTERCEPTED_REQUEST('R', "Intercepted, from Gateway to Boiler"),

  /**
   * Intercepted From Gateway to Thermostat.
   */
  INTERCEPTED_ANSWER('A', "Intercepted, from Gateway to Thermostat");

  /**
   * A mapping between the character code and its corresponding
   * enumeration to facilitate lookup by code.
   */
  private static Map<Character, Direction> mapping;

  /**
   * Key of the enumeration value.
   */
  private final char key;

  /**
   * Description of the enumeration value.
   */
  private final String description;

  /**
   * Initializes a new instance of the enumeration.
   *
   * @param key Key of the enumeration value
   * @param description Description of the enumeration value.
   */
  Direction(final char key, final String description) {
    this.key = key;
    this.description = description;
  }

  /**
   * Initializes the mapping.
   */
  private static void initMapping() {
    mapping = new HashMap<Character, Direction>();
    for (Direction d : values()) {
      mapping.put(d.key, d);
    }
  }

  /**
   * Returns a {@link Direction}.
   *
   * @param key the key to use to get the Direction.
   * @return the {@link Direction}.
   */
  static Direction getDirection(final char key) {
    if (mapping == null) {
      initMapping();
    }

    return mapping.get(key);
  }

  /**
   * @return the key
   */
  public char getKey() {
    return key;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

}
