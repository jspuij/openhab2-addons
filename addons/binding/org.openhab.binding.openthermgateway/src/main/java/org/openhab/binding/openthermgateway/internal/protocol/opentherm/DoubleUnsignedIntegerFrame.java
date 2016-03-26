/**
 *
 */
package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

/**
 * Frame carrying two unsigned integer values.
 *
 * @author Jan-Willem Spuij
 *
 */
public class DoubleUnsignedIntegerFrame extends OpenthermFrame {

  /**
   * The first value of this frame.
   */
  private final int firstValue;

  /**
   * The second value of this frame.
   */
  private final int secondValue;

  /**
   * Creates a new instance of the {@link DoubleUnsignedIntegerFrame} class.
   *
   * @param direction The {@link Direction} in which the frame is flowing.
   * @param message The bytes of the message.
   */
  public DoubleUnsignedIntegerFrame(final Direction direction, final byte[] message) {
    super(direction, message);
    firstValue = message[3] & 0xFF;
    secondValue = message[4] & 0xFF;
  }

  /**
   * The first value contained in this {@link DoubleUnsignedIntegerFrame}.
   *
   * @return the value The value
   */
  public final int getFirstValue() {
    return firstValue;
  }

  /**
   * The first value contained in this {@link DoubleUnsignedIntegerFrame}.
   *
   * @return the value The value
   */
  public final int getSecondValue() {
    return secondValue;
  }
}
