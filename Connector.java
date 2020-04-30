import answer.Answer;
import instruction.Instruction;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connector {
    private int port;
    private InetAddress address;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Socket socket;
    private boolean connected;


    Connector(InetAddress address, int port) {
        this.port = port;
        this.address = address;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean connect()  {
        try {
            socket = new Socket("localhost", 5000);
            connected=true;
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            //System.out.println("Подключение не удалось");
            return false;
        }
    }

    public void send(Instruction<?> instruction) {
        try {
            outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            outputStream.writeObject(instruction);
            outputStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            //System.out.println("");
        }
    }

    public String receiveResult() {
        Answer answer;
        try {
            inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            answer = (Answer) inputStream.readObject();
            return answer.getResult();
        } catch (ClassNotFoundException | IOException e) {
            connected=false;
            System.out.println("");
            //e.printStackTrace();
            //return "Неудача";
        }
        return "Неудача";
    }

    public void disconnect(){
        try{
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
