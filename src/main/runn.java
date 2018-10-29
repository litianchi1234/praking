package main;

import java.io.*;
import java.net.Socket;

import static main.Constant.*;

public class runn implements Runnable {

    Socket socket;
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    OutputStream outputStream;
    PrintWriter printWriter;

    public runn(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);

            System.out.println("准备接收信息");
            String temp;
            int closeReq = 1;
                System.out.println("进来了");
                while ((temp = bufferedReader.readLine()) != null) {
                    System.out.println("接收到数据");
                    System.out.println(temp);
                    String getMsg = temp;
                    if (getMsg == null){
                        break;
                    }
                    if (getMsg.split(",").length!=0){
                        String type = getMsg.split(",")[0];
                        System.out.println(type);
                        String req = "没有值传进来";

                        switch (type) {
                            case "-1":
                                closeReq = -1;
                                closeStream();
                                break;
                             //0000-0001为线下没有用户的停车方法。
                            case "0000"://非会员停车
                                req = parking(getMsg);
                                System.out.println("已停车：" + getMsg);
                                break;
                            case "0001"://离开车位，（第一版）
                                req = leaveParking(getMsg);
                                System.out.println("已离开:" + getMsg);
                                break;
                            //0002-0004会员停车方法
                            case "0002"://会员预定车位
                                req = userbook(getMsg);
                                System.out.println(req);
                                break;
                            case "0003"://会员停车
                                req = userparking(getMsg);
                                System.out.println(req);
                                break;
                            case "0004"://离开车库：（第二版）
                                System.out.println(getMsg);
                                req = userleaving(getMsg);
                                System.out.println(req);

                                break;
                            case "0005"://添加车位
                                req = addspot(getMsg);
                                System.out.println(req);
                                break;
                            case "0006"://读停车场表
                                conn2DBUtil.readparking();
                                req = conn2DBUtil.readparking();
                                break;
                            case "0007"://读停车位表
                               req = readspot(getMsg);
                                break;
                            case "0008"://会员登录
                                req = login(getMsg);
                                break;
                            case "0009"://查看会员log
                                System.out.println("查看会员log");
                                req = getuserlog(getMsg);
                                break;

                        }
                        printWriter.write(req + "\n");
                        printWriter.flush();
                        break;
                    } else {
                        System.out.println("无效数据");
                    }
                }
            System.out.println("第一次走出了天道轮回");

            nowUser--;


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream();//关闭各种流

        }

    }

    private void closeStream() {
        try {
            inputStream.close();
            socket.close();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            outputStream.close();
            printWriter.close();
            System.out.println("all close");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("close io error");
        }

    }


    private String parking(String getMsg) {
        String spotName = getMsg.split(",")[1];
        String carNum = getMsg.split(",")[2];
        String req = new conn2DBUtil().parking(spotName, carNum);
        return req;
    }
    private String leaveParking(String getMsg) {
        String spotName = getMsg.split(",")[1];
        String req = new conn2DBUtil().leaveParking(spotName);
        return req;
    }
    private String addspot(String getMsg) {
        String spotName = getMsg.split(",")[1];
        String req = new conn2DBUtil().addspot(spotName);
        return req;
    }
    private String userbook(String getMsg) {
        String spotName = getMsg.split(",")[1];
        String carNum = getMsg.split(",")[2];
        String phone = getMsg.split(",")[3];
        String req = new conn2DBUtil().userbook(spotName, carNum,phone);
        return req;
    }
    private String userparking(String getMsg) {
        String spotName = getMsg.split(",")[1];
        String carNum = getMsg.split(",")[2];
        String req = new conn2DBUtil().userparking(spotName, carNum);
        return req;
    }
    private String userleaving(String getMsg) {
        String spotName = getMsg.split(",")[1];
        System.out.println(spotName);
        String req = new conn2DBUtil().leaveParking(spotName);
        return req;
    }
    private String readspot(String getMsg) {
        String parkingid = getMsg.split(",")[1];
        String req = new conn2DBUtil().readspot(parkingid);
        return req;
    }
    private String login(String getMsg) {
        String phone = getMsg.split(",")[1];
        String password = getMsg.split(",")[2];
        String req = new conn2DBUtil().login(phone, password);
        return req;
    }
    private String getuserlog(String getMsg) {
        System.out.println("进入getuserlog");
        String username = getMsg.split(",")[1];
        String req = new conn2DBUtil().getUserlog(username);
        return req;
    }

}

