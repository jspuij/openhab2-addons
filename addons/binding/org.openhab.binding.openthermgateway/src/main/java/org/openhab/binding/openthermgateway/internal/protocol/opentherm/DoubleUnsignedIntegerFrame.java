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
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public DoubleUnsignedIntegerFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);
    firstValue = frameData[3] & 0xFF;
    secondValue = frameData[4] & 0xFF;
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
