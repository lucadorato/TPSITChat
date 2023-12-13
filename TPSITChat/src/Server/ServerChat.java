package Server;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Interfaces.ChatFeatures;
import Interfaces.XMLMessage;

public class ServerChat implements ChatFeatures{

    private DBManager dbm;
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;

    public ServerChat(DBManager dbm)
    {
        this.dbm = dbm;
    }

    @Override
    public int LogIn(String User, String Password) {
        // TODO Auto-generated method stub
        int result = UserNotFound;
        switch(dbm.CheckUser(User, Password))
        {
            case 1:
                return WrongPassword;
                
            case 2:
                return PasswordChecked;
                
            default:
                break;
        }
        return result;
    }
    @Override
    public NodeList LoadChat(String User1, String User2) {
        // TODO Auto-generated method stub
        NodeList chatMessages = dbm.GetChatMessages(User1, User2);
        return chatMessages;
    }
    @Override
    public String SendMessage(Element Message) {
        dbm.AddMessage(Message);
        return Message.getElementsByTagName("Receiver").item(0).getTextContent();
    }
    
}
