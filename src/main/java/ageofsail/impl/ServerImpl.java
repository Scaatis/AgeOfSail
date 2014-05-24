package ageofsail.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import ageofsail.Connection;
import ageofsail.GameServer;
import ageofsail.Player;

public class ServerImpl implements GameServer {

    private ServerSocket                               server;
    private ConcurrentHashMap<InetAddress, Connection> connections;
    private ConcurrentHashMap<InetAddress, Player>     players;
    private boolean                                    running;

    @Override
    public void run() {
        running = true;
        connections = new ConcurrentHashMap<>();
        players = new ConcurrentHashMap<>();
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(new ConnectionListener(this)).start();

        while (running) {
            // FIXME: Main game loop
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getIP(), player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player.getIP());
    }

    @Override
    public void removeConnection(Connection connection) {
        connections.remove(connection.getAddress());
    }

    private class ConnectionListener implements Runnable {

        private ServerImpl parent;

        public ConnectionListener(ServerImpl parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            while (!server.isClosed()) {
                Socket client;
                try {
                    client = server.accept();
                    connections.put(client.getInetAddress(), new ConnectionImpl(client, parent));
                } catch (IOException e) {
                    System.err.println("Accepting a connection failed.");
                }
            }
        }

    }

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

}
