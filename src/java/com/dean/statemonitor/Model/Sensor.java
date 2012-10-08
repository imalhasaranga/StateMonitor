package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Imal
 */
public class Sensor {

    private int sensor_id;
    private String sensor_serial;
    private int sensor_type;
    private String description;
    private String minimum;
    private String maximum;
    private String ActiveState;
    

    public ArrayList<Sensor> getAllSensors() {
        ArrayList<Sensor> data = new ArrayList<Sensor>();

        try {
            ResultSet rs = DB.getData("SELECT 	is_active,minimum,maximum,sensor_id, sensor_serial, sensor_type, description FROM sensor");
            while (rs.next()) {
                Sensor sensortype = new Sensor();
                sensortype.setDescription(rs.getString("description"));
                sensortype.setSensor_type(rs.getInt("sensor_type"));
                sensortype.setSensor_serial(rs.getString("sensor_serial"));
                sensortype.setSensor_id(rs.getInt("sensor_id"));
                sensortype.setMinimum(rs.getString("minimum"));
                sensortype.setMaximum(rs.getString("maximum"));
                sensortype.setActiveState(rs.getInt("is_active")==1?"Active":"InActive");
                data.add(sensortype);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public ArrayList<Sensor> getAllSensorsWithinId() {
        ArrayList<Sensor> data = new ArrayList<Sensor>();

        try {
            ResultSet rs = DB.getData("SELECT 	sensor_id, sensor_serial, sensor_type, description FROM sensor where sensor_type= '" + getSensor_type() + "' AND is_active='1'");
            while (rs.next()) {
                Sensor sensortype = new Sensor();
                sensortype.setDescription(rs.getString("description"));
                sensortype.setSensor_type(rs.getInt("sensor_type"));
                sensortype.setSensor_serial(rs.getString("sensor_serial"));
                sensortype.setSensor_id(rs.getInt("sensor_id"));
                data.add(sensortype);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }
    
    public int SensorCount(int sentype){
    
        try {
            ResultSet rs = DB.getData("SELECT COUNT(*) as couw FROM sensor WHERE sensor_type  ='"+sentype+"' AND is_active='1' ");
            if(rs.next()){
            return rs.getInt("couw");
            }else{
            return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    return 0;
    }
    
    public void deActivate(){
    
        try {
            DB.setData("UPDATE sensor SET is_active = !is_active WHERE sensor_id = '"+getSensor_id()+"'");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Sensor getSensor() {

        Sensor sensorS = new Sensor();
        try {
            ResultSet rs = DB.getData("SELECT 	sensor_id, sensor_serial, sensor_type, description FROM sensor where sensor_id= '" + getSensor_id() + "'");

            while (rs.next()) {

                sensorS.setDescription(rs.getString("description"));
                sensorS.setSensor_type(rs.getInt("sensor_type"));
                sensorS.setSensor_serial(rs.getString("sensor_serial"));
                sensorS.setSensor_id(rs.getInt("sensor_id"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return sensorS;
    }

    public void addSensorToDb() {

        try {

            DB.setData("INSERT INTO sensor_db.sensor "
                    + "(sensor_serial,  "
                    + "sensor_type,  "
                    + "description "
                    + ") "
                    + "VALUES "
                    + "('" + getSensor_serial() + "',  "
                    + "'" + getSensor_type() + "',  "
                    + "'" + getDescription() + "' "
                    + "); ");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void updateSensor(){
    
        try {
            
            DB.setData("UPDATE sensor SET minimum = '"+getMinimum()+"',maximum='"+getMaximum()+"' WHERE sensor_id = '"+getSensor_id()+"'");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    /**
     * @return the sensor_id
     */
    public int getSensor_id() {
        return sensor_id;
    }

    /**
     * @param sensor_id the sensor_id to set
     */
    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    /**
     * @return the sensor_serial
     */
    public String getSensor_serial() {
        return sensor_serial;
    }

    /**
     * @param sensor_serial the sensor_serial to set
     */
    public void setSensor_serial(String sensor_serial) {
        this.sensor_serial = sensor_serial;
    }

    /**
     * @return the sensor_type
     */
    public int getSensor_type() {
        return sensor_type;
    }

    /**
     * @param sensor_type the sensor_type to set
     */
    public void setSensor_type(int sensor_type) {
        this.sensor_type = sensor_type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    /**
     * @return the maximum
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    /**
     * @return the ActiveState
     */
    public String getActiveState() {
        return ActiveState;
    }

    /**
     * @param ActiveState the ActiveState to set
     */
    public void setActiveState(String ActiveState) {
        this.ActiveState = ActiveState;
    }
}
