/**
 *
 */
package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Frame carrying a decimal value.
 *
 * @author Jan-Willem Spuij
 *
 */
public class DecimalFrame extends OpenthermFrame {

  /**
   * Divisor for a decimal value.
   */
  private static final int DIVISOR = 256;

  /**
   * The value of this decimal frame.
   */
  private final BigDecimal value;

  /**
   * Creates a new instance of the {@link DecimalFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public DecimalFrame(final Direction direction, final DataId dataId, final byte[] frameData) {
    super(direction, dataId, frameData);

    short s = ByteBuffer.wrap(frameData, 2, 2).order(ByteOrder.BIG_ENDIAN).getShort();
    this.value = new BigDecimal(s).divide(new BigDecimal(DIVISOR));

  }

  /**
   * The value contained in this {@link DecimalFrame}.
   *
   * @return the value The value
   */
  public final BigDecimal getValue() {
    return value;
  }

}
