/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Opentherm Remote override function frame. Indicates how the thermostat
 * should react to remote overrides.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class RemoteOverrideFrame extends OpenThermFrame {

  /**
   * Indicates whether a manual change on the thermostat has priority
   * over a remote set point.
   */
  private final boolean manualChangePriority;

  /**
   * Indicates whether a programmatic change on the thermostat has priority
   * over a remote set point.
   */
  private final boolean programChangePriority;

  /**
   * Creates a new instance of the {@link RemoteOverideFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public RemoteOverrideFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);
    this.manualChangePriority = (frameData[2] & 0x01) != 0;
    this.programChangePriority = (frameData[2] & 0x02) != 0;
  }

  /**
   * @return Indicates whether a manual change on the thermostat has priority
   *         over a remote set point.
   */
  public boolean isManualChangePriority() {
    return manualChangePriority;
  }

  /**
   * @return Indicates whether a programmatic change on the thermostat has priority
   *         over a remote set point.
   */
  public boolean isProgramChangePriority() {
    return programChangePriority;
  }

}
