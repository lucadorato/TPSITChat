package Interfaces;

import java.util.ArrayList;

import Client.Message;

public interface ClientBLInterface {
    
    int LoadingChat(ArrayList<Message> messages);
    int GetSendMessage(Message m);
    int getSentMsgAck();
    
    int getLoadingContacts(ArrayList<String> contacts);
}
