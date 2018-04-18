package com.boce.flcp.util;
import java.sql.Connection;          //Connection接口
import java.sql.DriverManager;       //使用DriverManager类连接数据库
import java.sql.PreparedStatement;
import java.sql.ResultSet;           //用于数据库查询的接口
import java.sql.Statement;           //对数据库操作需要用到的Statement接口，此接口可以通过使用Connection接口中提供的createStatement（）方法实例化。
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("unused")


public class SqlServerTest{

    // 连接数据库
    final static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";          //定义SQL Server数据库的驱动程序
    final static String url = "jdbc:sqlserver://192.168.1.107:1433;DatabaseName=flcp";    // 定义数据库的连接地址
    // final static String url ="jdbc:sqlserver://192.168.3.44:1433;DatabaseName=WindElec";
    final static String user = "sa";                                                      //定义数据库的连接用户名
    final static String password = "abc!@#";                                              //连接sql数据库连接密码

    public static void main(String[] args) throws Exception {
// TODO 自动生成的方法存根

        Connection conn=null;   //数据库连接，新建Connection接口，所有数据库的操作都是从此接口开始。
        Statement stmt=null;    //申明Statement对象
        ResultSet rs=null;
        Class.forName(driver);                                       //加载驱动程序
        conn = DriverManager.getConnection(url, user, password);     //通过JDBC驱动与对应的数据库、用户名、密码相连接。getConnection（）方法的作用是取得连接对象

        if(conn !=null){
            System.out.println("数据库连接成功");
        }

        stmt=conn.createStatement();     //创建Statement对象

        stmt.close();                    //stmt对象关闭
        conn.close();                    //数据库关闭
    }
}