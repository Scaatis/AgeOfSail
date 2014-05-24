package ageofsail;

import java.util.Collection;

public interface GameServer extends Runnable {
    public static final int port = 1718;
    
    public void addPlayer(Player player);
    
    public void removePlayer(Player player);
    
    public void removeConnection(Connection connection);
    
    public void stop();
    
    public Collection<Player> getPlayers();
}
