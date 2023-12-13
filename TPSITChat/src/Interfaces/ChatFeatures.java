package Interfaces;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public interface ChatFeatures {
    
    public int LogIn(String User, String Password);
    public NodeList LoadChat(String User1, String User2);
    public String SendMessage(Element Message);

    public NodeList LoadContacts(String User);
}
