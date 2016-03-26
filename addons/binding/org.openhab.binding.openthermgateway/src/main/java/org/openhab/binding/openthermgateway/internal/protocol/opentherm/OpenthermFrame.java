/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.lang.reflect.Constructor;

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

  /**
   * The direction in which the frame is flowing.
   */
  private final Direction direction;

  /**
   * The message type of the frame.
   */
  private final MessageType messageType;

  /**
   * The data id of the frame.
   */
  private final DataId dataId;

  /**
   * Initializes a new instance of the {@link OpenthermFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public OpenthermFrame(final Direction direction, final DataId dataId, final byte[] frameData)
      throws IllegalArgumentException {
    this.direction = direction;
    this.dataId = dataId;

    this.messageType = MessageType.getMessageType((frameData[0] >> 1));
    if (this.messageType == null) {
      throw new IllegalArgumentException(
          String.format("Incorrect message type: %d", (frameData[0] >> 1)));
    }

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

    DataId dataId = DataId.getDataId(frameData[1]);
    if (dataId == null) {
      throw new IllegalArgumentException(String.format("Unknown dataId: %d", (frameData[1])));
    }

    try {
      Constructor<? extends OpenthermFrame> constructor = dataId.getFrameClass()
          .getConstructor(Direction.class, DataId.class, byte[].class);

      return constructor.newInstance(direction, dataId, frameData);
    } catch (Exception e) {
      logger.error("Unable to instantiate frame class.", e);
    }

    return null;
  }

  /**
   * Returns the {@link Direction} the frame is flowing into.
   *
   * @return the {@link Direction}
   */
  public final Direction getDirection() {
    return direction;
  }

  /**
   * Returns the {@link MessageType} of the frame.
   *
   * @return the {@link MessageType}
   */
  public final MessageType getMessageType() {
    return messageType;
  }

  /**
   * Returns the {@link DataId} of the frame.
   *
   * @return the {@link DataId}
   */
  public final DataId getDataId() {
    return this.dataId;
  }

}
