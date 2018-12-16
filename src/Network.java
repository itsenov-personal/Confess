import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

abstract public class Network implements Runnable {

    protected DataOutputStream dOut;
    protected DataInputStream dIn;
    protected Engine engine;
    protected final static int PORT = 6666;
    protected ServerSocket serverSocket;
    protected Socket clientSocket;

    public Network(Engine engine){
        this.engine = engine;
    }

    public void run(){
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean takingInput = true;
        while(takingInput) {
            try {
                takingInput = readInput();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    abstract protected void start() throws IOException;
    abstract protected void stop() throws IOException;

    public Boolean readInput() throws IOException {
        byte dataType = dIn.readByte();
        switch (dataType) {
            case 1:
                receiveMove();
                return true;
            case 0:
                stop();
                return false;
            default:
                System.out.println("The input byte is corrupted");
                return false;
        }
    }

    public void sendMove(int i, int j, int newI, int newJ) throws IOException {
        dOut.writeByte(1);
        dOut.writeInt(i);
        dOut.writeInt(j);
        dOut.writeInt(newI);
        dOut.writeInt(newJ);
        dOut.flush();
    }

    private void receiveMove() throws IOException{
        int i = dIn.readInt();
        int j = dIn.readInt();
        int newI = dIn.readInt();
        int newJ = dIn.readInt();
        engine.move(i,j,newI,newJ, false);
    }
}
