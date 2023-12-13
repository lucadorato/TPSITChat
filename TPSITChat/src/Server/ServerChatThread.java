package Server;

import java.lang.Thread;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Interfaces.XMLMessageBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerChatThread extends Thread{
    
    private Socket ConnSocket;
    private ServerChatSkeleton scs;
    private HashMap<String,ObjectOutputStream> addrMp;
    private final int SentMessage = 1;
    private Semaphore semMap;
    private XMLMessageBuilder mb;

    public ServerChatThread(Socket s, ServerChatSkeleton skserver, HashMap<String,ObjectOutputStream> mp, Semaphore mapsem)
    {
        ConnSocket = s;
        scs = skserver;
        addrMp = mp; 
        semMap = mapsem;
        mb = new XMLMessageBuilder();
    }

    public void run()
    {
        Boolean esci = false;
        try
        {
            ObjectInputStream buffIn = new ObjectInputStream(ConnSocket.getInputStream());
            ObjectOutputStream buffOut = new ObjectOutputStream(ConnSocket.getOutputStream());

            while(!esci)
            {
            
                Document packet = (Document)buffIn.readObject();
                String requiredOp = packet.getElementsByTagName("Operation").item(0).getTextContent();
                switch(requiredOp)
                {
                    case "LogIn":
                        String user = packet.getElementsByTagName("nickName").item(0).getTextContent();
                        String pass = packet.getElementsByTagName("password").item(0).getTextContent();
                        int LoginResult = scs.LogIn(user, pass);
                        
                        buffOut.writeObject(LoginResult);
                        if(LoginResult == 2)
                        {
                            semMap.acquire();
                            addrMp.put(user, buffOut);
                            semMap.release();
                        }
                        else
                        {
                            ConnSocket.close();
                            buffIn.close();
                            buffOut.close();
                            esci=true;
                        }
                        break;
                    case "LoadChat":
                        String reQUser = packet.getElementsByTagName("localUser").item(0).getTextContent();
                        String searchUser = packet.getElementsByTagName("remoteUser").item(0).getTextContent();
                        NodeList messages = scs.LoadChat(reQUser, searchUser);
                        Document chat = mb.createChatXMLObject(messages);
                        buffOut.writeObject(chat);
                        break;
                    case "SendMessage":
                        Element msg = (Element)packet.getElementsByTagName("ChatMessage").item(0);
                        String receiver=scs.SendMessage(msg);
                        semMap.acquire();
                        ObjectOutputStream oos = addrMp.get(receiver);
                        semMap.release();
                        Document msgPacket = mb.createMsgXMLObject(msg);
                        oos.writeObject(msgPacket);
                        Document ackPacket = mb.createAckXMLObject();
                        buffOut.writeObject(ackPacket);
                        break;
                    case "ContactsReq":
                        String Cuser = packet.getElementsByTagName("user").item(0).getTextContent();
                        NodeList users=scs.LoadContacts(Cuser);
                        Document ContactsPacket = mb.createContactsXMLObject(users);
                        buffOut.writeObject(ContactsPacket);
                        break;
                }

           
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        

    }
}
