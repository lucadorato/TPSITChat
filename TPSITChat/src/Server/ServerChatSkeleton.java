package Server;


import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Interfaces.ChatFeatures;
public class ServerChatSkeleton implements ChatFeatures{

    private ServerChat sc;
    private ServerSocket SocketSer;
    private Semaphore MapSem;
    private HashMap<String,ObjectOutputStream> addrMap; 

    public ServerChatSkeleton(ServerChat schat)
    {
        sc = schat;
        addrMap = new HashMap<String,ObjectOutputStream>();
        MapSem = new Semaphore(1);
    }
    
    public void Skeleton()
    {
        try{
            SocketSer = new ServerSocket(8000);

            while(true)
            {
                Socket ConnSock = SocketSer.accept();
                ServerChatThread ConnThread = new ServerChatThread(ConnSock, this, addrMap, MapSem);
                ConnThread.start();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

    }

    @Override
    public int LogIn(String User, String Password) {
        return sc.LogIn(User, Password);
    }

    @Override
    public NodeList LoadChat(String User1, String User2) {
        return sc.LoadChat(User1, User2);
    }

    @Override
    public String SendMessage(Element Message) {
       return sc.SendMessage(Message);    
    }
    
    public NodeList LoadContacts(String User)
    {
        return sc.LoadContacts(User);
    }

    
}
