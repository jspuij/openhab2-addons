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

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for Opentherm 32bit frames as provided
 * by the Opentherm Gateway.
 *
 * @author Jan-Willem Spuij
 *
 */
public abstract class OpenthermFrame {

  /**
   * Opentherm Frame_Length in bytes.
   */
  private static final int FRAME_LENGTH = 4;

  /***
   * Map with frame classes to look up and
   * instantiate in the parser.
   */
  private static final Map<DataId, OpenthermFrame> FRAME_CLASSES = new HashMap<DataId, OpenthermFrame>();

  /**
   * The direction in which the frame is flowing.
   */
  private final Direction direction;

  /**
   * The message type of the frame.
   */
  private final MessageType messageType;

  /**
   * Initializes a new instance of the {@link OpenthermFrame} class.
   *
   * @param direction The {@link Direction} in which the frame is flowing.
   * @param messageType The {@link MessageType} of the frame.
   */
  public OpenthermFrame(final Direction direction, final MessageType messageType) {
    this.direction = direction;
    this.messageType = messageType;
  }

  /**
   * Parses an Opentherm Frame. Returns null if the frame is not
   * a valid Opentherm Frame.
   *
   * @param frame {@link String} containing the message.
   * @return an {@link OpenthermFrame} instance.
   */
  public static OpenthermFrame parseString(final String frame) {
    Logger logger = LoggerFactory.getLogger(OpenthermFrame.class);

    if (frame == null) {
      return null;
    }

    if (frame.length() != 2 * FRAME_LENGTH + 1) {
      logger.debug("Incorrect frame length: {}", frame.length());
      return null;
    }

    Direction direction = Direction.getDirection(frame.charAt(0));
    if (direction == null) {
      logger.debug("Incorrect direction: {}", frame.charAt(0));
      return null;
    }

    byte[] frameData = DatatypeConverter.parseHexBinary(frame.substring(1, 2 * FRAME_LENGTH + 1));
    MessageType messageType = MessageType.getMessageType((frameData[0] >> 1));
    if (messageType == null) {
      logger.debug("Incorrect message type: {}", (frameData[0] >> 1));
      return null;
    }

    return null;

  }

  /**
   * @return the {@link Direction}
   */
  public final Direction getDirection() {
    return direction;
  }

  /**
   * @return the {@link MessageType}
   */
  public final MessageType getMessageType() {
    return messageType;
  }

  /**
   * @return the {@link DataId}
   */
  public abstract DataId getDataId();

}
