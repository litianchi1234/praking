package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class main {
    public static void main(String[] args) {

        System.out.println("waiting...");
        Thread p=new Thread(new receive());
        p.start();

    }
}

