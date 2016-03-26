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
   * @param direction The {@link Direction} in which the frame is flowing.
   * @param message The bytes of the message.
   */
  public DecimalFrame(final Direction direction, final byte[] message) {
    super(direction, message);

    short s = ByteBuffer.wrap(message, 2, 2).order(ByteOrder.BIG_ENDIAN).getShort();
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
