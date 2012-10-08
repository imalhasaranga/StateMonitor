package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Imal
 */
public class sensors_inspectors {

    private Sensor_type sensorType;
    private Sensor sensor;
    private int SensorID;
    private int inspectorID;

    public ArrayList<sensors_inspectors> retrive_inspector_sensors() {
        ArrayList<sensors_inspectors> sensorInspec = new ArrayList<sensors_inspectors>();
        try {
            ResultSet rs = DB.getData("SELECT sensorID,inspectorID FROM sensors_inspectors where inspectorID ='" + getInspectorID() + "' ");
            while (rs.next()) {
                sensors_inspectors senins = new sensors_inspectors();
                senins.setSensorID(rs.getInt("sensorID"));
                senins.setInspectorID(rs.getInt("inspectorID"));


                Sensor sensor1 = new Sensor();
                sensor1.setSensor_id(rs.getInt("sensorID"));
                sensor1 = sensor1.getSensor();
                senins.setSensor(sensor1);


                Sensor_type sentype = new Sensor_type();
                senins.setSensorType(sentype.getSensorTypes(senins.getSensor().getSensor_type()));
                sensorInspec.add(senins);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return sensorInspec;
    }

    public void addSensor_inspector() {

        try {

            DB.setData("INSERT INTO sensor_db.sensors_inspectors "
                    + "(sensorID, "
                    + "inspectorID "
                    + ") "
                    + "VALUES "
                    + "('"+getSensorID()+"',  "
                    + "'"+getInspectorID()+"' "
                    + ")");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void deleteSensor_inspectorsRecord(){
    
        try {
            DB.setData("DELETE FROM sensors_inspectors WHERE sensorID = '"+getSensorID()+"' AND inspectorID = '"+getInspectorID()+"' ");
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    /**
     * @return the sensorType
     */
    public Sensor_type getSensorType() {
        return sensorType;
    }

    /**
     * @param sensorType the sensorType to set
     */
    public void setSensorType(Sensor_type sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * @return the sensor
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * @param sensor the sensor to set
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * @return the SensorID
     */
    public int getSensorID() {
        return SensorID;
    }

    /**
     * @param SensorID the SensorID to set
     */
    public void setSensorID(int SensorID) {
        this.SensorID = SensorID;
    }

    /**
     * @return the inspectorID
     */
    public int getInspectorID() {
        return inspectorID;
    }

    /**
     * @param inspectorID the inspectorID to set
     */
    public void setInspectorID(int inspectorID) {
        this.inspectorID = inspectorID;
    }
}
