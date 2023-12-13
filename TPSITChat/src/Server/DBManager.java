package Server;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import Interfaces.XMLMessage;

public class DBManager {
    
    private static Document db;
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;

	protected static final String XML_FILE_NAME = "TPSITChat/src/Server/dbChat.xml";
    private DocumentBuilder builder;
    private File xmlFile;

    public DBManager()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            
            //DOM Builder(Parser) creation 
            builder = factory.newDocumentBuilder();

            //File Object Creation
            xmlFile = new File(XML_FILE_NAME);
            
			//Critical Section: A thread at the time can read the xml file because of it is a shared resource
            db = builder.parse(xmlFile);
			//remove white spaces from xml document (it is needed to indent correctly during the writing phase)
			removeWhitespaces(db.getDocumentElement());

		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    public synchronized int CheckUser(String user, String password)
    {
        int result = UserNotFound;
        Node NodeUser = db.getElementById(user);
        if(NodeUser!=null)
        {
            if(NodeUser.getFirstChild().getTextContent().equals(password))
            {
                result = PasswordChecked;
            }
            else
            {
                result = WrongPassword;
            }
        }
        return result;
    }

    public synchronized Boolean AddMessage(Element message)
    {
        Boolean res = false; 
        try {
            //get sender and receiver by xml message object
			String sender = message.getElementsByTagName("Sender").item(0).getTextContent();
			String receiver = message.getElementsByTagName("Receiver").item(0).getTextContent();
			//search the chat element with id sender_receiver
			Element chat = db.getElementById(sender+"_"+receiver);
			//import chat message node from xml message object to xml db object and get it in newMessage
			Node newMessage = db.importNode(message /*m.getElementsByTagName("ChatMessage").item(0)*/, true);
			
			if(chat != null)
			{
				//if id sender_receiver not found try to found receiver_sender
				chat.appendChild(newMessage);
			}
			else
			{
				
				chat = db.getElementById(receiver+"_"+sender);
				if(chat != null)
				{
					chat.appendChild(newMessage);
				}
				else
				{
					//if id not found add a new chat with id sender_receiver
					Element chatNew = db.createElement("chat");
					chatNew.setAttribute("id", sender+"_"+receiver);
					chatNew.appendChild(newMessage);
					Element chatList = (Element)db.getElementsByTagName("ChatList").item(0);
					chatList.appendChild(chatNew);
				}
			}

            res = true;

        } catch (Throwable t) {
            t.printStackTrace ();
        }

        return res;
    }

    public synchronized NodeList GetChatMessages(String user1, String user2)
    {
        NodeList chatMessages = null; 
        Element chat = GetChat(user1, user2);
        if(chat!=null)
        {
            chatMessages = chat.getChildNodes();
        }
        return chatMessages;
    }

    public synchronized NodeList GetContacts(String user)
    {
        
    }

    private synchronized Element GetChat(String user1, String user2)
    {
        Element chat = db.getElementById(user1+"_"+user2);        
        if(chat == null)
        {
            chat = db.getElementById(user2+"_"+user1);
        }
        return chat;
    }

    public synchronized static void removeWhitespaces(Element element) {
		NodeList children = element.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
				&& ((Text) child).getData().trim().isEmpty()) {
				element.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaces((Element) child);
			}
		}
	}

}
