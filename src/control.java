import sun.rmi.runtime.Log;
import java.sql.*;


public class control {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parking_mag_sys?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASS = "root";
    private String sql;



    public String readtable(String tablename) {
        Connection conn = null;
        Statement stmt = null;


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();


            String sql1 = "select * from "+tablename;
            System.out.println(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);
            System.out.println("cha dao jie guo ");
            while (rs1.next()){

                System.out.print(rs1.getObject(1) + " ");
                System.out.print(rs1.getObject(2) + " ");
                System.out.print(rs1.getObject(3) + " ");
                System.out.print(rs1.getObject(4) + " ");
                System.out.println();

            }

            rs1.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源

        }

        return "ok";

    }

    public String parking(String spotId, String carNum) {
        Connection conn = null;
        Statement stmt = null;



        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "update spot set isnull=0 where spotid='" + spotId +"'";
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));
            String sql2 = "insert into log (spotid,carnum,isgone,starttime) values('" + spotId + "','" + carNum + "','0',now())";
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

    public String leaveParking(String spotId) {
        Connection conn = null;
        Statement stmt = null;


        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            String sql1 = "update spot set isnull=1 where spotid='" + spotId+"'" ;
            //System.out.println(sql1);
            System.out.println(stmt.executeUpdate(sql1));
            String sql2 = "update log set isgone=1,endtime=now() where spotid= '" + spotId + "' and isgone='0'";
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

        return "已出发";

    }

}
