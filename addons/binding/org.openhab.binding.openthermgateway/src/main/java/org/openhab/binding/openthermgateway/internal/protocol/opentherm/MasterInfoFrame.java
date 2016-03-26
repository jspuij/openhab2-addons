/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Master information Frame.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class MasterInfoFrame extends OpenThermFrame {

  /**
   * MemberId of the master.
   */
  private final int masterMemberId;

  /**
   * Creates a new instance of the {@link MasterInfoFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public MasterInfoFrame(final Direction direction, final DataId dataId, final byte[] frameData) {
    super(direction, dataId, frameData);
    this.masterMemberId = frameData[3] & 0xFF;
  }

  /**
   * @return the Member ID of the thermostat.
   */
  public int getMasterMemberId() {
    return masterMemberId;
  }

  @Override
  public String toString() {
    return String.format(
        "Direction: %s (%s), Message Type: %d (%s), Data-Id: %d (%s), Master member Id: %d",
        this.getDirection().getKey(), this.getDirection().getDescription(),
        this.getMessageType().getKey(), this.getMessageType().getDescription(),
        this.getDataId().getKey(), this.getDataId().getDescription(), this.getMasterMemberId());
  }
}
