package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Imal
 */
public class Sensor_type {

    private int sensor_type_id;
    private String sensor_type;
    private String sensor_family_code;
    private String State;
    public ArrayList<Sensor_type> sensorTypes = new ArrayList<Sensor_type>();
    public ArrayList<Integer> sensorTypesID = new ArrayList<Integer>();
    public static int ALL = 10;
    public static int INACTIVE = 12;
    public static int ACTIVE = 13;

    public ArrayList<Sensor_type> getAllSensorTypes(int condition) {
        ArrayList<Sensor_type> data = new ArrayList<Sensor_type>();
        String conditn = "";
        switch (condition) {
            case 10:
                conditn = "";
                break;
            case 12:
                conditn = "where is_active='0'";
                break;
            case 13:
                conditn = "where is_active='1'";
                break;
        }
        try {
            ResultSet rs = DB.getData("SELECT sensor_type_id, sensor_type, family_code,is_active FROM sensor_type " + conditn);
            while (rs.next()) {
                Sensor_type sensortype = new Sensor_type();
                sensortype.setSensor_family_code(rs.getString("family_code"));
                sensortype.setSensor_type(rs.getString("sensor_type"));
                sensortype.setSensor_type_id(rs.getInt("sensor_type_id"));
                if (rs.getInt("is_active") == 1) {
                    sensortype.setState("Active");
                } else {
                    sensortype.setState("InActive");
                }
                data.add(sensortype);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public Sensor_type getSensorTypes(int sensor_type_id) {
        Sensor_type sensortype = null;
        try {
            ResultSet rs = DB.getData("SELECT sensor_type_id, sensor_type, family_code,is_active FROM sensor_type where sensor_type_id = '" + sensor_type_id + "'");
            sensortype = new Sensor_type();
            if (rs.next()) {
                sensortype.setSensor_family_code(rs.getString("family_code"));
                sensortype.setSensor_type(rs.getString("sensor_type"));
                sensortype.setSensor_type_id(rs.getInt("sensor_type_id"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return sensortype;
    }

    public void addSensorType() {
        sensorTypes.add(this);
    }

    public boolean SaveSensorsToDb() {

        try {
            Iterator i = sensorTypes.iterator();
            while (i.hasNext()) {

                Sensor_type sensortype = (Sensor_type) i.next();

                DB.setData("INSERT INTO sensor_type "
                        + "(sensor_type,family_code)"
                        + "VALUES ("
                        + "'" + sensortype.getSensor_type() + "','" + sensortype.getSensor_family_code() + "')");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e + "___________________________________");
            return false;
        }
    }

    public void addSensorTypeID(int Sensor_type_id) {
        sensorTypesID.add(Sensor_type_id);
    }

    public boolean removeSensor() {
        try {
            Iterator i = sensorTypesID.iterator();
            while (i.hasNext()) {
                int sensortypeid = Integer.parseInt(i.next().toString());
                DB.setData("DELETE FROM sensor_type WHERE sensor_type_id = '" + sensortypeid + "' ");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deactivateSensor() {

        try {
            DB.setData("UPDATE sensor_db.sensor_type SET is_active = !is_active WHERE sensor_type_id = '" + getSensor_type_id() + "'");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * @return the sensor_type_id
     */
    public int getSensor_type_id() {
        return sensor_type_id;
    }

    /**
     * @param sensor_type_id the sensor_type_id to set
     */
    public void setSensor_type_id(int sensor_type_id) {
        this.sensor_type_id = sensor_type_id;
    }

    /**
     * @return the sensor_type
     */
    public String getSensor_type() {
        return sensor_type;
    }

    /**
     * @param sensor_type the sensor_type to set
     */
    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }

    /**
     * @return the sensor_family_code
     */
    public String getSensor_family_code() {
        return sensor_family_code;
    }

    /**
     * @param sensor_family_code the sensor_family_code to set
     */
    public void setSensor_family_code(String sensor_family_code) {
        this.sensor_family_code = sensor_family_code;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }
}
