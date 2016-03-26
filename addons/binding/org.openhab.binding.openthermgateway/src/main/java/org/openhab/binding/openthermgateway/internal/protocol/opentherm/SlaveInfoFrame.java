/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Slave information Frame.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class SlaveInfoFrame extends OpenThermFrame {

  /**
   * Indicates whether Domestic Hot Water is present on the boiler.
   */
  private final boolean slaveDhwPresent;

  /**
   * Indicates whether the boiler supports moduling control.
   */
  private final boolean slaveControlType;

  /**
   * Indicates whether cooling is supported.
   */
  private final boolean slaveCoolingConfig;

  /**
   * Indicates whether the Domestic Hot Water is instantaneous (0)
   * or a storage tank (1).
   */
  private final boolean slaveDhwConfig;

  /**
   * Indicates whether the thermostat is allowed to control the pump.
   */
  private final boolean slaveMasterLowOffPumpControl;

  /**
   * Indicates whether a second central heating circuit is present on the boiler.
   */
  private final boolean slaveCh2Present;

  /**
   * MemberId of the slave.
   */
  private final int slaveMemberId;

  /**
   * Creates a new instance of the {@link SlaveInfoFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public SlaveInfoFrame(final Direction direction, final DataId dataId, final byte[] frameData) {
    super(direction, dataId, frameData);
    this.slaveDhwPresent = (frameData[2] & 0x01) != 0;
    this.slaveControlType = (frameData[2] & 0x02) != 0;
    this.slaveCoolingConfig = (frameData[2] & 0x04) != 0;
    this.slaveDhwConfig = (frameData[2] & 0x08) != 0;
    this.slaveMasterLowOffPumpControl = (frameData[2] & 0x10) != 0;
    this.slaveCh2Present = (frameData[2] & 0x20) != 0;

    this.slaveMemberId = frameData[3] & 0xFF;
  }

  /**
   * @return the Member ID of the boiler.
   */
  public int getSlaveMemberId() {
    return slaveMemberId;
  }

  /**
   * @return Indicates whether Domestic Hot Water is present on the boiler.
   */
  public boolean isSlaveDhwPresent() {
    return slaveDhwPresent;
  }

  /**
   * @return Indicates whether the boiler supports moduling control.
   */
  public boolean isSlaveControlType() {
    return slaveControlType;
  }

  /**
   * @return Indicates whether cooling is supported.
   */
  public boolean isSlaveCoolingConfig() {
    return slaveCoolingConfig;
  }

  /**
   * @return Indicates whether the Domestic Hot Water is instantaneous (0)
   *         or a storage tank (1).
   */
  public boolean isSlaveDhwConfig() {
    return slaveDhwConfig;
  }

  /**
   * @return Indicates whether the thermostat is allowed to control the pump.
   */
  public boolean isSlaveMasterLowOffPumpControl() {
    return slaveMasterLowOffPumpControl;
  }

  /**
   * @return Indicates whether a second central heating circuit is present on the boiler.
   */
  public boolean isSlaveCh2Present() {
    return slaveCh2Present;
  }
}
