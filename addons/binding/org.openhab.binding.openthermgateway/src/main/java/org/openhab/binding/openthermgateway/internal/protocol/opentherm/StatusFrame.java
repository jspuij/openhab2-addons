/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Status Frame. Sent by thermostat,
 * responded to by boiler.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class StatusFrame extends OpenThermFrame {
  /**
   * Central Heating enabled.
   */
  private final boolean masterChEnable;

  /**
   * Domestic Hot water enabled.
   */
  private final boolean masterDhwEnable;

  /**
   * Cooling enabled.
   */
  private final boolean masterCoolingEnable;

  /**
   * Outside temperature compensation enabled.
   */
  private final boolean masterOtcActive;

  /**
   * Central heating second circuit enabled.
   */
  private final boolean masterCh2Enable;

  /**
   * Slave has fault.
   */
  private final boolean slaveFaultIndication;

  /**
   * Central heating active.
   */
  private final boolean slaveChActive;

  /**
   * Domestic hot water active.
   */
  private final boolean slaveDhwActive;

  /**
   * Flame active.
   */
  private final boolean slaveFlameActive;

  /**
   * Cooling active.
   */
  private final boolean slaveCoolingActive;

  /**
   * Central heating second circuit active.
   */
  private final boolean slaveCh2Active;

  /**
   * Slave diagnostic indication.
   */
  private final boolean slaveDiagnosticIndication;

  /**
   * Creates a new instance of the {@link StatusFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public StatusFrame(final Direction direction, final DataId dataId, final byte[] frameData) {
    super(direction, dataId, frameData);
    this.masterChEnable = (frameData[2] & 0x01) != 0;
    this.masterDhwEnable = (frameData[2] & 0x02) != 0;
    this.masterCoolingEnable = (frameData[2] & 0x04) != 0;
    this.masterOtcActive = (frameData[2] & 0x08) != 0;
    this.masterCh2Enable = (frameData[2] & 0x10) != 0;
    this.slaveFaultIndication = (frameData[3] & 0x01) != 0;
    this.slaveChActive = (frameData[3] & 0x02) != 0;
    this.slaveDhwActive = (frameData[3] & 0x04) != 0;
    this.slaveFlameActive = (frameData[3] & 0x08) != 0;
    this.slaveCoolingActive = (frameData[3] & 0x10) != 0;
    this.slaveCh2Active = (frameData[3] & 0x20) != 0;
    this.slaveDiagnosticIndication = (frameData[3] & 0x40) != 0;
  }

  /**
   * @return whether central heating is enabled on the thermostat.
   */
  public boolean isMasterChEnable() {
    return masterChEnable;
  }

  /**
   * @return whether domestic hot water is enabled on the thermostat.
   */
  public boolean isMasterDhwEnable() {
    return masterDhwEnable;
  }

  /**
   * @return whether cooling is enabled on the thermostat.
   */
  public boolean isMasterCoolingEnable() {
    return masterCoolingEnable;
  }

  /**
   * @return whether outside temperature is enabled on the thermostat.
   */
  public boolean isMasterOtcActive() {
    return masterOtcActive;
  }

  /**
   * @return whether central heating on the second circuit is enabled on the thermostat.
   */
  public boolean isMasterCh2Enable() {
    return masterCh2Enable;
  }

  /**
   * @return whether the boiler has a fault.
   */
  public boolean isSlaveFaultIndication() {
    return slaveFaultIndication;
  }

  /**
   * @return whether central heating on the boiler is active.
   */
  public boolean isSlaveChActive() {
    return slaveChActive;
  }

  /**
   * @return whether domestic hot water is active on the boiler.
   */
  public boolean isSlaveDhwActive() {
    return slaveDhwActive;
  }

  /**
   * @return whether the flame is burning in the boiler.
   */
  public boolean isSlaveFlameActive() {
    return slaveFlameActive;
  }

  /**
   * @return whether cooling is active in the boiler.
   */
  public boolean isSlaveCoolingActive() {
    return slaveCoolingActive;
  }

  /**
   * @return whether the central heating on the second circuit is enabled on the boiler.
   */
  public boolean isSlaveCh2Active() {
    return slaveCh2Active;
  }

  /**
   * @return whether there is a diagnostic indication on the boiler.
   */
  public boolean isSlaveDiagnosticIndication() {
    return slaveDiagnosticIndication;
  }

  @Override
  public String toString() {
    return String.format(
        "Direction: %s (%s), Message Type: %d (%s), Data-Id: %d (%s), Master Central Heating enabled: %b, "
            + "Master Domestic Hot water enabled: %b, Master Cooling enabled: %b, "
            + "Master Outside temperature compensation enabled: %b, Master Central heating second circuit enabled %b, "
            + "Slave has fault: %b, Slave Central heating active: %b, Slave Domestic hot water active: %b"
            + "Slave Flame active: %b, Slave Cooling active: %b, Slave Central heating second circuit active: %b",
        this.getDirection().getKey(), this.getDirection().getDescription(),
        this.getMessageType().getKey(), this.getMessageType().getDescription(),
        this.getDataId().getKey(), this.getDataId().getDescription(), this.isMasterChEnable(),
        this.isMasterDhwEnable(), this.isMasterCoolingEnable(), this.isMasterOtcActive(),
        this.isMasterCh2Enable(), this.isSlaveFaultIndication(), this.isSlaveChActive(),
        this.isSlaveDhwActive(), this.isSlaveFlameActive(), this.isSlaveCoolingActive(),
        this.isSlaveCh2Active());
  }
}
