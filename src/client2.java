import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client2 {
    public static void main(String[] args) {


        try {

            InetAddress local = InetAddress.getLocalHost();
            Socket socket = new Socket(local,8086);
            System.out.println("准备发送数据");
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            //0001,hnxq_a1,京A12345
            Scanner scan = new Scanner(System.in);
            printWriter.write(scan.nextLine());
            printWriter.flush();
            System.out.println("数据已发送");
            socket.shutdownOutput();

         /*   InputStream  inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("收到服务器消息:"+bufferedReader.readLine());

            bufferedReader.close();
            inputStream.close();
            inputStreamReader.close();*/
            outputStream.close();
            socket.close();
            printWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
