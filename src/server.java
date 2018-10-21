import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
public class server {

    public void conn(){

        try {
            ServerSocket ss = new ServerSocket(8086);
            Socket s = ss.accept();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
