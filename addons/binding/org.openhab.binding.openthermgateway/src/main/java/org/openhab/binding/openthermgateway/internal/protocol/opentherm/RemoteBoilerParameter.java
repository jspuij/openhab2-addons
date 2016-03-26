/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Remote boiler parameter transfer-enable & read/write flags frame.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class RemoteBoilerParameter extends OpenThermFrame {

  /**
   * Indicates whether transfer from boiler to thermostat of
   * the Domestic Hot Water set point is enabled or disabled.
   */
  private final boolean dhwSetPointTransferEnable;

  /**
   * Indicates whether transfer from boiler to thermostat of
   * the maximum central heating set point is enabled or disabled.
   */
  private final boolean maxChSetPointTransferEnable;

  /**
   * Indicates whether the thermostat can change the domestic hot
   * water set point.
   */
  private final boolean dhwSetPointReadWrite;

  /**
   * Indicates whether the thermostat can change the maximum central heating
   * set point.
   */
  private final boolean maxSetPointChReadWrite;

  /**
   * Creates a new instance of the {@link RemoteBoilerParameter} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public RemoteBoilerParameter(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);
    this.dhwSetPointTransferEnable = (frameData[2] & 0x01) != 0;
    this.maxChSetPointTransferEnable = (frameData[2] & 0x02) != 0;
    this.dhwSetPointReadWrite = (frameData[3] & 0x01) != 0;
    this.maxSetPointChReadWrite = (frameData[3] & 0x02) != 0;

  }

  /**
   * @return Indicates whether transfer from boiler to thermostat of
   *         the Domestic Hot Water set point is enabled or disabled.
   */
  public boolean isDhwSetPointTransferEnable() {
    return dhwSetPointTransferEnable;
  }

  /**
   * @return Indicates whether transfer from boiler to thermostat of
   *         the maximum central heating set point is enabled or disabled.
   */
  public boolean isMaxChSetPointTransferEnable() {
    return maxChSetPointTransferEnable;
  }

  /**
   * @return Indicates whether the thermostat can change the domestic hot
   *         water set point.
   */
  public boolean isDhwSetPointReadWrite() {
    return dhwSetPointReadWrite;
  }

  /**
   * @return Indicates whether the thermostat can change the maximum central heating
   *         set point.
   */
  public boolean isMaxSetPointChReadWrite() {
    return maxSetPointChReadWrite;
  }

  @Override
  public String toString() {
    return String.format(
        "Direction: %s (%s), Message Type: %d (%s), Data-Id: %d (%s), "
            + "Domestic Hot Water set point transfer enabled: %b, "
            + "Central heating set point transfer enabled: %b, "
            + "Domestic Hot Water set point read/write: %b, "
            + "Central Heating set point read/write: %b",
        this.getDirection().getKey(), this.getDirection().getDescription(),
        this.getMessageType().getKey(), this.getMessageType().getDescription(),
        this.getDataId().getKey(), this.getDataId().getDescription(),
        this.isDhwSetPointTransferEnable(), this.isMaxChSetPointTransferEnable(),
        this.isDhwSetPointReadWrite(), this.isMaxSetPointChReadWrite());
  }
}
