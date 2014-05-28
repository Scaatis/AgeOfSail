package ageofsail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionImpl implements Connection {

    private Socket         socket;
    private Player         player;
    private GameServer     server;
    private PrintWriter    out;
    private BufferedReader in;

    public ConnectionImpl(Socket socket, GameServer server) throws IOException {
        this.socket = socket;

        this.server = server;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void close() throws IOException {
        server.removeConnection(this);
        out.close();
        in.close();
        socket.close();
    }

    @Override
    public void run() {
        while (true) { // I love it when methods start this way...
            String message;
            try {
                message = in.readLine();
            } catch (IOException e) {
                System.out.println("Connection to " + socket.getInetAddress().toString() + " closed unexpectedly.");
                try {
                    close();
                } catch (IOException e2) {

                }
                break;
            }
            if (message == null) {
                System.out.println("Connection to " + socket.getInetAddress().toString() + " was closed.");
                try {
                    close();
                } catch (IOException e) {
                }
                break;
            }
            // FIXME: Parse message
        }
    }

    @Override
    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    @Override
    public void send(String message) {
        out.write(message);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public GameServer getServer() {
        return server;
    }

}
