package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Interfaces.ClientBLInterface;

public class ClientChatThread extends Thread{
    
    private Socket s;
    private ClientBLInterface ci;
    private Boolean terminate;
    ClientChatThread(Socket s, ClientBLInterface ci)
    {
        this.s=s;
        this.ci=ci;
        this.terminate=false;
    }

    public void TerminateThread()
    {
        terminate=true;
    }
    public void run()
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            while(!terminate)
            {
                Document packet;
            
                packet = (Document)ois.readObject();
            
                String operation = packet.getElementsByTagName("Operation").item(0).getTextContent();
                switch(operation)
                {
                    case "ChatLoading":
                        NodeList chat = packet.getElementsByTagName("ChatMessage");
                        ArrayList<Message> chatMessages = new ArrayList<Message>();
                        for(int i = 0;i<chat.getLength();i++)
                        {
                            Element e = (Element)chat.item(i);
                            chatMessages.add(new Message(e.getElementsByTagName("Content").item(0).getTextContent(),e.getElementsByTagName("Sender").item(0).getTextContent()));
                        }
                        ci.LoadingChat(chatMessages);
                        break;
                    case "MsgFwd":
                        Element message = (Element)packet.getElementsByTagName("ChatMessage").item(0);
                        Message m = new Message(message.getElementsByTagName("Content").item(0).getTextContent(),message.getElementsByTagName("Sender").item(0).getTextContent());
                        ci.GetSendMessage(m);
                        break;
                    case "AckSentMsg":
                        ci.getSentMsgAck();
                        break;
                    default:
                        break;
                }   
                     
            }
        } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }   
        
    }
}
