/**
 *
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
public class SignedIntegerFrame extends OpenthermFrame {

  /**
   * The value of this frame.
   */
  private final int value;

  /**
   * Creates a new instance of the {@link SignedIntegerFrame} class.
   *
   * @param direction The {@link Direction} in which the frame is flowing.
   * @param message The bytes of the message.
   */
  public SignedIntegerFrame(final Direction direction, final byte[] message) {
    super(direction, message);

    value = ByteBuffer.wrap(message, 2, 2).order(ByteOrder.BIG_ENDIAN).getShort();
  }

  /**
   * The value contained in this {@link SignedIntegerFrame}.
   *
   * @return the value The value
   */
  public final int getValue() {
    return value;
  }

}
