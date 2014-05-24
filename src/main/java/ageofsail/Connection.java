package ageofsail;

import java.io.Closeable;
import java.net.InetAddress;

public interface Connection extends Closeable, Runnable {
    public InetAddress getAddress();
    
    /**
     * Schedule a message for sending
     */
    public void send(String message);
    
    /**
     * Returns the player this connection is controlling
     */
    public Player getPlayer();
    
    /**
     * Returns the server that this connection belongs to.
     */
    public GameServer getServer();
}
