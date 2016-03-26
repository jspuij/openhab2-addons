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
public class DoubleSignedIntegerFrame extends OpenthermFrame {

  /**
   * The first value of this frame.
   */
  private final int firstValue;

  /**
   * The second value of this frame.
   */
  private final int secondValue;

  /**
   * Creates a new instance of the {@link DoubleSignedIntegerFrame} class.
   *
   * @param direction The {@link Direction} the message flows into.
   * @param dataId The {@link DataId} of the frame.
   * @param frameData The frame data.
   * @throws IllegalArgumentException if the frame data is invalid.
   */
  public DoubleSignedIntegerFrame(final Direction direction, final DataId dataId,
      final byte[] frameData) {
    super(direction, dataId, frameData);

    firstValue = frameData[3];
    secondValue = frameData[4];
  }

  /**
   * The first value contained in this {@link DoubleSignedIntegerFrame}.
   *
   * @return the value The value
   */
  public final int getFirstValue() {
    return firstValue;
  }

  /**
   * The first value contained in this {@link DoubleSignedIntegerFrame}.
   *
   * @return the value The value
   */
  public final int getSecondValue() {
    return secondValue;
  }
}
