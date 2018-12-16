import java.net.*;
import java.io.*;

public class Client extends Network{

    public Client(Engine engine){
        super(engine);
    }

    protected void start() throws IOException {
        clientSocket = new Socket("localhost", PORT );
        dOut = new DataOutputStream(clientSocket.getOutputStream());
        dIn = new DataInputStream(clientSocket.getInputStream());
    }

    public void stop() throws IOException {
        dIn.close();
        dOut.close();
        serverSocket.close();
        clientSocket.close();
    }

}
