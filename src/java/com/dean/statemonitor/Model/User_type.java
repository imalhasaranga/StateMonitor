package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Imal
 */
public class User_type {
/*
    public static int ACTIVE = 10;
    public static int INACTIVE = 11;
    public static int ALL = 12;
    public ArrayList<String> userTypes = new ArrayList<String>();
    public ArrayList<Integer> userTypesID = new ArrayList<Integer>();

    public ArrayList<DataClass> getAllUserTypes(int type) {
        ArrayList<DataClass> data = new ArrayList<DataClass>();
        String condition = " where is_active='1'";
        switch (type) {
            case 10:
                condition += " where is_active='1'";
                break;
            case 11:
                condition += " where is_active='0'";
                break;
            case 12:
                condition += " where is_active='0'";
                break;
        }

        try {
            ResultSet rs = DB.getData("SELECT user_type_id, user_type FROM user_type  " + condition);
            while (rs.next()) {
                DataClass item = new DataClass();
                item.setFieldName(rs.getString("user_type"));
                item.setID(rs.getInt("user_type_id"));
                data.add(item);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public void addUserType(String Sensor_type) {
        userTypes.add(Sensor_type);
    }

    public boolean SaveUserTypeToDb() {

        try {
            Iterator i = userTypes.iterator();
            while (i.hasNext()) {

                String usertype = (String) i.next();
                DB.setData("INSERT INTO user_type "
                        + "(user_type)"
                        + "VALUES "
                        + "'" + usertype + "')");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    
     public void addSensorTypeID(int Sensor_type_id) {
        userTypesID.add(Sensor_type_id);
    }
    
    public boolean removeSensor(){  
         try {
            Iterator i = userTypesID.iterator();
            while (i.hasNext()) {
                int usertypeID = Integer.parseInt(i.next().toString());
                DB.setData("DELETE FROM user_type  WHERE user_type_id = '"+usertypeID+"' ");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }*/
}
