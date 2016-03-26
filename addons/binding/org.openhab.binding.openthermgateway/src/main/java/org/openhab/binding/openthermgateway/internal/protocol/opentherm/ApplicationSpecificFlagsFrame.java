/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Application-specific fault flags and OEM specific
 * fault code frame. Responded to by boiler.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class ApplicationSpecificFlagsFrame extends OpenthermFrame {

  /**
   * Service required.
   */
  private final boolean serviceRequest;

  /**
   * Remote reset enabled.
   */
  private final boolean lockoutReset;

  /**
   * Low water pressure fault.
   */
  private final boolean lowWaterPressureFault;

  /**
   * Gas / Flame fault.
   */
  private final boolean gasFlameFault;

  /**
   * Air pressure fault.
   */
  private final boolean airPressureFault;

  /**
   * Water over temperature fault.
   */
  private final boolean waterOverTempFault;

  /**
   * Oem fault code.
   */
  private final int oemFaultCode;

  /**
   * Creates a new instance of the {@link ApplicationSpecificFlagsFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public ApplicationSpecificFlagsFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);
    this.serviceRequest = (frameData[2] & 0x01) != 0;
    this.lockoutReset = (frameData[2] & 0x02) != 0;
    this.lowWaterPressureFault = (frameData[2] & 0x04) != 0;
    this.gasFlameFault = (frameData[2] & 0x08) != 0;
    this.airPressureFault = (frameData[2] & 0x10) != 0;
    this.waterOverTempFault = (frameData[2] & 0x20) != 0;

    this.oemFaultCode = frameData[3] & 0xFF;
  }

  /**
   * @return the Member ID of the thermostat.
   */
  public int getOemFaultCode() {
    return oemFaultCode;
  }

  /**
   * @return Service required.
   */
  public boolean isServiceRequest() {
    return serviceRequest;
  }

  /**
   * @return Remote reset enabled.
   */
  public boolean isLockoutReset() {
    return lockoutReset;
  }

  /**
   * @return Low water presssure fault.
   */
  public boolean isLowWaterPressureFault() {
    return lowWaterPressureFault;
  }

  /**
   * @return Gas / Flame fault.
   */
  public boolean isGasFlameFault() {
    return gasFlameFault;
  }

  /**
   * @return Air pressure fault.
   */
  public boolean isAirPressureFault() {
    return airPressureFault;
  }

  /**
   * @return Water over temperature fault.
   */
  public boolean isWaterOverTempFault() {
    return waterOverTempFault;
  }
}
