package Server;


public class ServerMain {
    public static void main(String[] args) {
        DBManager dbm = new DBManager();
        ServerChat sc = new ServerChat(dbm);
        ServerChatSkeleton Skeleton = new ServerChatSkeleton(sc);

        Skeleton.Skeleton();
    }
}
