import jdk.internal.util.xml.impl.Input;
import org.omg.CORBA.portable.OutputStream;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.InflaterOutputStream;

public class main {
    public static void main(String[] args) {





        Scanner sc =new Scanner(System.in);
        String spotId = sc.nextLine();
        String carnum = sc.nextLine();

        System.out.println(new control().parking(spotId,carnum));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(new control().leaveParking(spotId));




        // String tablename = sc.nextLine();
        //System.out.println(new control().readtable(tablename));


    }
}
