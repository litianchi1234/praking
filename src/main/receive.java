package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static main.Constant.*;

public class receive extends Thread{


    public receive() {
    }

    @Override
    public void run() {

        try {
            ServerSocket server = new ServerSocket(8086);
            while (true){
                if (nowUser < MAX_USER){
                    System.out.println("等待接收数据");
                    nowUser ++;
                    Socket socket = server.accept();
                    new runn(socket).run();
                    System.out.println("已接收到数据，线程开启");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}