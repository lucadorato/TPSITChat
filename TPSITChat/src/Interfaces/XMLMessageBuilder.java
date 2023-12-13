package Interfaces;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLMessageBuilder {
    
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean instanciated = false;

    public XMLMessageBuilder()
    {
        //create a message (text+sender+receiver)
        if(!instanciated)
        {
            try
            {
                docFactory = DocumentBuilderFactory.newInstance();
                docBuilder = docFactory.newDocumentBuilder();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            instanciated = true;
        }
        
    }

    public Document createLogInXMLObject(String user, String password)
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("LogIn");
        rootElement.appendChild(e);
        e = doc.createElement("nickName");
        e.setTextContent(user);
        rootElement.appendChild(e);
        e = doc.createElement("password");
        e.setTextContent(password);
        rootElement.appendChild(e);

        //return the document object
        return doc;
    }

    public Document createMsgXMLObject(Element msg)
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("MsgFwd");
        rootElement.appendChild(e);
        Node mess = doc.importNode(msg,true);
        rootElement.appendChild(mess);

        //return the document object
        return doc;
    }

    public Document createChatXMLObject(NodeList chat)
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("ChatLoading");
        rootElement.appendChild(e);
        
        Element eChat = doc.createElement("chat");
        
        for(int i = 0; i<chat.getLength();i++)
        {
            Node mess = doc.importNode(chat.item(i), true);
            eChat.appendChild(mess);
        }
        rootElement.appendChild(eChat);

        //return the document object
        return doc;
    }

    public Document createAckXMLObject()
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("AckSentMessage");
        rootElement.appendChild(e);

        //return the document object
        return doc;
    }

    public Document createLoadContactReqXMLObject(String user)
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("ContactsReq");
        rootElement.appendChild(e);
        e = doc.createElement("user");
        e.setTextContent(user);
        rootElement.appendChild(e);

        //return the document object
        return doc;
    }

    public Document createContactsXMLObject(NodeList contacts)
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("XMLPacket");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add operation, user and password element as children elements
        Element e = doc.createElement("Operation");
        e.setTextContent("ContactsLoading");
        rootElement.appendChild(e);
        
        Element eUserList = doc.createElement("user_list");
        
        for(int i = 0; i<contacts.getLength();i++)
        {
            Node contact = doc.importNode(contacts.item(i), true);
            eUserList.appendChild(contact);
        }
        rootElement.appendChild(eUserList);

        //return the document object
        return doc;
    }

}
