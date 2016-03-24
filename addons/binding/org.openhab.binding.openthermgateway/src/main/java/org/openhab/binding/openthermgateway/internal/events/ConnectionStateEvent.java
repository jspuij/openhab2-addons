/**
 *
 */
package org.openhab.binding.openthermgateway.internal.events;

/**
 * Informs the gateway handler about the state of the connection.
 *
 * @author Jan-Willem Spuij
 *
 */
public class ConnectionStateEvent extends GatewayEvent {

    /**
     * The reason why the connection state occurred.
     */
    private String reason;

    /**
     * The connection state.
     */
    private ConnectionState connectionState;

    /**
     * Initializes a new instance of the {@link ConnectionState} class.
     *
     * @param connectionState
     * @param reason
     */
    public ConnectionStateEvent(ConnectionState connectionState, String reason) {
        this.reason = reason;
        this.connectionState = connectionState;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @return the connectionState
     */
    public ConnectionState getConnectionState() {
        return connectionState;
    }

    /**
     * Connection states of the gateway.
     *
     * @author Jan-Willem Spuij
     *
     */
    public enum ConnectionState {
        /**
         * Not connected yet.
         */
        NotConnected,
        /**
         * Connected to the gateway.
         */
        Connected,
        /**
         * Something disconnected the gateway.
         */
        Disconnected,
        /**
         * Connection failed, do not reconnect.
         */
        Failed
    }

    @Override
    public String toString() {
        return String.format("(%s) %s", this.getConnectionState(), this.getReason());
    }
}