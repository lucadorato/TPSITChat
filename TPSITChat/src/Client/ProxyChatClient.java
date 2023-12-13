package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Interfaces.ChatFeatures;
import Interfaces.ClientBLInterface;
import Interfaces.XMLMessageBuilder;

public class ProxyChatClient implements ChatFeatures{

    private Socket sock;
    private ClientBLInterface ci;
    private XMLMessageBuilder mb;
    private ClientChatThread clientThread;
    ProxyChatClient()
    {
        try {
            sock = new Socket("localhost",8000);
            ci = null;
            mb = new XMLMessageBuilder();
            clientThread = null;


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public int LogIn(String User, String Password) {
        // TODO Auto-generated method stub
        int LoginResult = 0;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            Document packet = mb.createLogInXMLObject(User, Password);
            oos.writeObject(packet);
            LoginResult = (int)ois.readObject();
            if(LoginResult!=2)
            {
                sock.close();
                oos.close();
                ois.close();
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return LoginResult;
    }

    public void CreateClientThread(ClientBLInterface clInt)
    {
        ci=clInt;
        clientThread = new ClientChatThread(sock, ci);
        clientThread.start();
    } 

    public void KillClientThread()
    {
        clientThread.TerminateThread();
    } 

    @Override
    public NodeList LoadChat(String User1, String User2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LoadChat'");
    }

    @Override
    public String SendMessage(Element Message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SendMessage'");
    }
    
}
