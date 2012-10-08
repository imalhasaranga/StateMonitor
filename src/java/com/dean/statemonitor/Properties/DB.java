package com.dean.statemonitor.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

    public static Connection con;

    public static Connection getCon() throws Exception {

        if (con == null) {
            String port = null;
            String DBname = null;
            String Dbpass = null;
            String Dbuser = null;
            String host = null;
            try {
                System.getProperties().load(DB.class.getResourceAsStream("Statemon.properties"));
                host = System.getProperty("host");
                port = System.getProperty("port");
                DBname = System.getProperty("Dbname");
                Dbpass = System.getProperty("Dbpass");
                Dbuser = System.getProperty("Dbuser");
            } catch (Exception e) {
                System.out.println(e);
            }

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + DBname + "?autoReconnect=true", Dbuser, Dbpass);
        }
        return con;
    }

    public static ResultSet getData(String sql) throws Exception {


        return getCon().createStatement().executeQuery(sql);
    }

    public static void setData(String sql) throws Exception {


        getCon().createStatement().executeUpdate(sql);

    }

    public static PreparedStatement prepareData(String sql) throws Exception {


        return getCon().prepareStatement(sql);
    }

    public static int setDataAndGet(String sql) throws Exception {

        int returnInt = getCon().createStatement().executeUpdate(sql);

        return returnInt;
    }
}
