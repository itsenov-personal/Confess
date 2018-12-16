import java.net.*;
import java.io.*;

public class Server extends Network{

    public Server(Engine engine){
    super(engine);
}

    protected void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientSocket = serverSocket.accept();
        dOut = new DataOutputStream(clientSocket.getOutputStream());
        dIn = new DataInputStream(clientSocket.getInputStream());
    }

    public void stop() throws IOException {
        dIn.close();
        dOut.close();
        clientSocket.close();
        serverSocket.close();
    }

}
