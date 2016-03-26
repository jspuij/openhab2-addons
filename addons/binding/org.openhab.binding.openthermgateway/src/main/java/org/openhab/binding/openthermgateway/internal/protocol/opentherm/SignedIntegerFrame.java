/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Frame carrying a signed 16bit value.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class SignedIntegerFrame extends OpenThermFrame {

  /**
   * The value of this frame.
   */
  private final int value;

  /**
   * Creates a new instance of the {@link SignedIntegerFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public SignedIntegerFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);
    value = ByteBuffer.wrap(frameData, 2, 2).order(ByteOrder.BIG_ENDIAN).getShort();
  }

  /**
   * The value contained in this {@link SignedIntegerFrame}.
   *
   * @return the value The value
   */
  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("Direction: %s (%s), Message Type: %d (%s), Data-Id: %d (%s), Value: %d",
        this.getDirection().getKey(), this.getDirection().getDescription(),
        this.getMessageType().getKey(), this.getMessageType().getDescription(),
        this.getDataId().getKey(), this.getDataId().getDescription(), this.getValue());
  }
}
