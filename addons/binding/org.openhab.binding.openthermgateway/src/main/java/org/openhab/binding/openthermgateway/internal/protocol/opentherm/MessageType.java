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
 * Opentherm message type.
 *
 * @author Jan-Willem Spuij
 *
 */
public enum MessageType {

  /**
   * Master to Slave Read Data.
   */
  MS_READ_DATA(0x0, "Master to Slave Read Data"),

  /**
   * Slave to Master Write Data.
   */
  MS_WRITE_DATA(0x1, "Slave to Master Write Data"),

  /**
   * Master to Slave Invalid Data.
   */
  MS_INVALID_DATA(0x02, "Master to Slave Invalid Data"),

  /**
   * Reserved.
   */
  RESERVED(0x03, "Reserved"),

  /**
   * Slave to Master Read Acknowledged.
   */
  SM_READ_ACK(0x04, "Slave to Master Read Acknowledged"),

  /**
   * Slave to Master Write Acknowledged.
   */
  SM_WRITE_ACK(0x05, "Slave to Master Write Acknowledged"),

  /**
   * Slave to Master Read Acknowledged.
   */
  SM_DATA_INVALID(0x06, "Slave to Master Data Invalid"),

  /**
   * Slave to Master Write Acknowledged.
   */
  SM_UNKNOWN_DATAID(0x07, "Slave to Master Unknown DataID");

  /**
   * A mapping between the integer and its corresponding
   * enumeration to facilitate lookup by code.
   */
  private static Map<Integer, MessageType> mapping;

  /**
   * Key of the enumeration value.
   */
  private final int key;

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
  MessageType(final int key, final String description) {
    this.key = key;
    this.description = description;
  }

  /**
   * Initializes the mapping.
   */
  private static void initMapping() {
    mapping = new HashMap<Integer, MessageType>();
    for (MessageType d : values()) {
      mapping.put(d.key, d);
    }
  }

  /**
   * Returns a {@link MessageType}.
   *
   * @param key the key to use to get the MessageType.
   * @return the {@link MessageType}.
   */
  static MessageType getMessageType(final int key) {
    if (mapping == null) {
      initMapping();
    }

    return mapping.get(key);
  }

  /**
   * @return the key
   */
  public int getKey() {
    return key;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

}
