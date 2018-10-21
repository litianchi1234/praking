import org.omg.CORBA.DataInputStream;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnUtil {
    public int MAX_USER = 10;
    public int nowUser = 0;
    public void getConn(){
        try {

            ServerSocket server = new ServerSocket(8086);//在本机的8086端口监听

            if (nowUser < MAX_USER){
                while (server.accept()!=null){
                    nowUser ++;
                    Socket socket = server.accept();
                    new A(socket,1).run();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class A implements Runnable{
        Socket socket;
        int type;
        // 1: 车来了 ， 2：车走了
        public A(Socket socket,int type) {
            this.socket = socket;
            this.type = type;
        }




        @Override
        public void run() {
            switch (type){
                case 1:
                    chelaile();
                    break;
                case 2:
                    chezoule();
                    break;
            }

            try {
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream bop = new BufferedOutputStream(socket.getOutputStream());

                bop.write(1);
                bop.flush();

                int get = bis.read();



                bop.close();
                bis.close();
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void chelaile(){

    }
    public void chezoule(){

    }
}
