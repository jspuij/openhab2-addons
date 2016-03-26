/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.time.DayOfWeek;

/**
 * Frame containing a day of the week and the time of day.
 *
 * @author Jan-Willem Spuij
 *
 */
public final class DayOfWeekAndTimeOfDayFrame extends OpenThermFrame {

  /**
   * Day of week. Can be null, which means not defined.
   */
  private final DayOfWeek dayOfWeek;

  /**
   * Hours of day.
   */
  private final int hours;

  /**
   * Minutes of day.
   */
  private final int minutes;

  /**
   * Creates a new instance of the {@link DayOfWeekAndTimeOfDayFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public DayOfWeekAndTimeOfDayFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) throws IllegalArgumentException {
    super(direction, dataId, frameData);

    int dayOfWeekInteger = (frameData[2] >> 3);

    if (dayOfWeekInteger == 0) {
      this.dayOfWeek = null;
    } else {
      this.dayOfWeek = DayOfWeek.of(dayOfWeekInteger);
    }

    this.hours = (frameData[2] & 0x1F);
    this.minutes = (frameData[2] & 0xFF);

  }

  /**
   * @return Day of week. Can be null, which means not defined.
   */
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * @return Hours of day.
   */
  public int getHours() {
    return hours;
  }

  /**
   * @return Minutes of day.
   */
  public int getMinutes() {
    return minutes;
  }

}
