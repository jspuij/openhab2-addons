/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Frame carrying two unsigned integer values.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class DoubleSignedIntegerFrame extends OpenThermFrame {

  /**
   * The first value of this frame.
   */
  private final int firstValue;

  /**
   * The second value of this frame.
   */
  private final int secondValue;

  /**
   * Creates a new instance of the {@link DoubleSignedIntegerFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public DoubleSignedIntegerFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);

    firstValue = frameData[3];
    secondValue = frameData[4];
  }

  /**
   * The first value contained in this {@link DoubleSignedIntegerFrame}.
   *
   * @return the value The value
   */
  public int getFirstValue() {
    return firstValue;
  }

  /**
   * The first value contained in this {@link DoubleSignedIntegerFrame}.
   *
   * @return the value The value
   */
  public int getSecondValue() {
    return secondValue;
  }

  @Override
  public String toString() {
    return String.format(
        "Direction: %s (%s), Message Type: %d (%s), Data-Id: %d (%s), First value: %d, Second value: %d",
        this.getDirection().getKey(), this.getDirection().getDescription(),
        this.getMessageType().getKey(), this.getMessageType().getDescription(),
        this.getDataId().getKey(), this.getDataId().getDescription(), this.getFirstValue(),
        this.getSecondValue());
  }
}
