package main;

import java.sql.*;


public class conn2DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parking_mag_sys?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASS = "root";


    public static String parking(String spotId,String carNum) {
        Connection conn = null;
        Statement stmt = null;



        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "update spot set isnull=2 where spotname='" + spotId +"'";
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));
            String sql2 = "insert into log (spotname,carnum,isgone,starttime) values('" + spotId + "','" + carNum + "','2',now())";
            //System.out.println(sql2);
            System.out.println(stmt.executeUpdate(sql2));

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "已停车";
    }

    public static String leaveParking(String spotId) {
        Connection conn = null;
        Statement stmt = null;
        System.out.println("jinqu");

        try {


            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "update spot set isnull=0 where spotname='" + spotId+"'" ;
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));
            String sql3 = "select logid from log where spotname= '" + spotId + "' and isgone='2'" ;
            System.out.println(sql3);
            //System.out.println(stmt.execute(sql1));
            ResultSet rs =stmt.executeQuery(sql3);
            while (rs.next()) {
                String deptno = rs.getString(1);
                System.out.println(deptno);

            }
            String sql2 = "update log set isgone=0,endtime=now() where spotname= '" + spotId + "' and isgone='2'";
            //System.out.println(sql2);
            System.out.println(stmt.executeUpdate(sql2));



            stmt.close();
            conn.close();
            return "1";

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "0";

    }

    public static String userbook(String spotId,String carNum,String phone){
        Connection conn = null;
        Statement stmt = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "update spot set isnull=1 where spotname='" + spotId +"'";
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));
            String sql2 = "insert into log (spotname,carnum,userphone,isgone,starttime) values('" + spotId + "','" + carNum + "','" + phone + "','1',now())";
            //System.out.println(sql2);
            System.out.println(stmt.executeUpdate(sql2));

            stmt.close();
            conn.close();
            return "1";

        } catch (SQLException se) {
            // 处理 JDB

            se.printStackTrace();
            return "0";
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            return "0";
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();

            }
        }
    }

    public static String userparking(String spotId,String carNum){
        Connection conn = null;
        Statement stmt = null;
        StringBuffer res = new StringBuffer();


        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();
            String sql1 = "select carnum from log where spotname='"+spotId+"' and isgone=1";
            System.out.println(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);
            System.out.println("进入对比");
            while (rs1.next()){
                System.out.println("返回结果中。。。");
                StringBuffer temp = new StringBuffer();

                temp.append(rs1.getObject(1));

                res.append(temp.toString());
                System.out.println(res.toString());
            }
            if(res.toString().equals(carNum)){
                String sql = "update spot set isnull=2 where spotname='" + spotId +"'";
                System.out.println(stmt.executeUpdate(sql));
                System.out.println(sql);

                String sql2 = "update log set isgone=2 where spotname='"+spotId+"' and isgone=1";
                System.out.println(stmt.executeUpdate(sql2));
                System.out.println(sql2);
                return "1";
            }//成功
            else {
                return "0";
            }//车牌不同


        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "已停车";

    }

    public static String userleaving(String spotId){
        Connection conn = null;
        Statement stmt = null;


        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql1 = "update spot set isnull=2 where spotname='" + spotId+"'" ;
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));


            String sql3 = "select logid from log where where spotname= '" + spotId + "' and isgone='2'" ;
            //System.out.println(stmt.execute(sql1));
            ResultSet rs =stmt.executeQuery(sql3);
            while (rs.next()) {
                String deptno = rs.getString(1);
                System.out.println(deptno);

            }
            String temp = rs.getString(1);


            String sql2 = "update log set isgone=1,endtime=now() where spotname= '" + spotId + "' and isgone='0'";
            System.out.println(stmt.executeUpdate(sql2));

            String sql4 = "select starttime from log where logid="+temp+"" ;
            ResultSet rs1 =stmt.executeQuery(sql4);
            while (rs1.next()) {
                String deptno = rs1.getString(1);
                System.out.println(deptno);

            }
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "已出发";

    }

    public static String addspot(String spotName){
        Connection conn = null;
        Statement stmt = null;
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "insert into spot(isnull,spotname) values(0,'"+spotName+"')" ;
            System.out.println(stmt.executeUpdate(sql1));

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "已添加车库";
    }

    public static String readparking() {
        Connection conn = null;
        Statement stmt = null;

        StringBuffer res = new StringBuffer();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();


            String sql1 = "select * from parking";
            System.out.println(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);
            System.out.println("cha dao jie guo ");

            while (rs1.next()){
                System.out.println("返回结果中。。。");
                StringBuffer temp = new StringBuffer();
                temp.append(rs1.getObject(1)+",");
                temp.append(rs1.getObject(2)+",");
                temp.append(rs1.getObject(3)+",\n");

                res.append(temp.toString());
            }
            System.out.println("查询结束");
            rs1.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return res.toString();

    }

    public static String readspot(String parkingid) {
        Connection conn = null;
        Statement stmt = null;

        StringBuffer res = new StringBuffer();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();


            String sql1 = "select * from spot where parkingid="+parkingid+"";
            System.out.println(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);
            System.out.println("cha dao jie guo ");

            while (rs1.next()){
                StringBuffer temp = new StringBuffer();
                temp.append(rs1.getObject(1)+",");
                temp.append(rs1.getObject(2)+",");
                temp.append(rs1.getObject(3)+",");
                temp.append(rs1.getObject(4)+",\n");

                res.append(temp.toString());
            }

            rs1.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 关闭资源

        }

        return res.toString();

    }

    public static String login(String phone, String password){

        Connection conn = null;
        Statement stmt = null;
        try{

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql = "SELECT * FROM user where phone = '"+phone+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库


            while(rs.next())
            {
                // 通过字段检索
                String s = rs.getString("phone");
                String s1 = rs.getString("password");

                System.out.println("查询到的账户："+rs.getString("phone"));
                System.out.println("查询到的密码："+rs.getString("password"));

                rs.close();
                stmt.close();
                conn.close();

                if ( s.equals(phone) )
                {
                    if (s1.equals(password))
                    {
                        System.out.println("登录成功");
                        return "1";//登录成功
                    }
                    else
                    {
                        System.out.println("密码错误");
                        return "0";//密码错误
                    }

                }
                else {
                    System.out.println("改手机号不存在");
                }
            }

            return conn2DBUtil.register(phone,password);




        }
                catch(SQLException se)
                {
                    // 处理 JDBC 错误
                    se.printStackTrace();
                }
                catch(Exception e)
                {
                    // 处理 Class.forName 错误
                    e.printStackTrace();
                }

                finally{
                    // 关闭资源
                    try{
                        if(stmt!=null) stmt.close();
                    }catch(SQLException se2){
                    }// 什么都不做
                    try{
                        if(conn!=null) conn.close();
                    }catch(SQLException se){
                        se.printStackTrace();
                    }
                }
               /* conn2DBUtil.register(phone,password);*/
        return "4";
    }

    public static String register(String phone, String password){
        String DB_URL = "jdbc:mysql://localhost:3306/parking_mag_sys?useSSL=false&serverTimezone=UTC";
        Connection conn = null;
        Statement stmt = null;
        String sql2;
        try{

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            sql2 = "insert into user(phone,password) values ('"+phone+"','"+password+"')";
            System.out.println(sql2);
//            int res = stmt.executeUpdate(sql2);
//            System.out.println("插入返回的结果 "+res);
            Boolean success = stmt.execute(sql2);
            System.out.println(success);
            if (!success){
                return "3";
                //chenggong
            } else {
                return "0";
                //shibai
            }
            // 完成后关闭


        }catch(SQLException se){
            // 数据库中没有文件
            se.printStackTrace();

        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return "4";
    }

    public static String getUserlog(String username) {
        System.out.println("进入酒精发酵防腐剂");
        Connection conn = null;
        Statement stmt = null;

        StringBuffer res = new StringBuffer();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();


            String sql1 = "select * from log where userphone='"+username+"'";

            System.out.println(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);

            while (rs1.next()){
                System.out.println("进入k查询");
                StringBuffer temp = new StringBuffer();
                temp.append(rs1.getObject(1)+",");
                temp.append(rs1.getObject(2)+",");
                temp.append(rs1.getObject(3)+",");
                temp.append(rs1.getObject(4)+",");
                temp.append(rs1.getObject(5)+",");
                temp.append(rs1.getObject(6)+",");
                temp.append(rs1.getObject(7)+",\n");

                res.append(temp.toString());
            }
            System.out.println(res.toString());
            rs1.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return res.toString();

    }

    public static void foundate(){
        Connection conn = null;
        Statement stmt = null;


        try {

            StringBuffer res = new StringBuffer();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "select starttime from log where logid=18" ;
            //System.out.println(stmt.execute(sql1));
            ResultSet rs =stmt.executeQuery(sql1);
            while (rs.next()) {
                String deptno = rs.getString(1);
                System.out.println(deptno);

            }

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }

















   /* public String getParking(){
        Connection conn = null;
        Statement stmt = null;
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // 执行查询
        System.out.println(" 实例化Statement对象...");

        stmt = conn.createStatement();


        String sql1 = "select * from "+tablename;
        System.out.println(sql1);
        ResultSet rs1 = stmt.executeQuery(sql1);
        System.out.println("cha dao jie guo ");
        return "1";
    }*/

    /*public static void main(String[] args) {
        System.out.println(readtable("spot"));
    }*/
}
