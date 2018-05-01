package com.qf.util;


import java.sql.PreparedStatement;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2018/5/1.
 */
public class JdbcUtils {

    private static Connection conn;
    private static String username;
    private static String password;
    private static String url;

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            conn=DriverManager.getConnection(url,username,password);
            System.out.println("conn===="+conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return JdbcUtils.conn;
    }

    public static void releaseResources(Connection conn,PreparedStatement preparedStatement)
    {
        try {
            if(!conn.isClosed())
            {
                conn.close();
            }
            if(!preparedStatement.isClosed())
            {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
