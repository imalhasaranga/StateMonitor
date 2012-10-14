package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Properties.DB;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TotalSensorReadings {

    private int sensor_id;
    private int sen_type_id;
    private String sen_type_name;
    private String family_code;
    private String sen_serial;
    private String Sen_description;
    private String sen_min;
    private String sen_max;
    private int reading_id;
    private String reading;
    private String reading_datime;
    private String color;
    private String readingTime;
    private boolean normal;
    private boolean inDanger;

    public ArrayList<TotalSensorReadings> getLatestReadings(int Sentypeid) {
        ArrayList<TotalSensorReadings> redng = new ArrayList<TotalSensorReadings>();
        try {

            ResultSet rs = DB.getData("SELECT MAX(reading_time) AS mtime FROM reading");
            rs.first();
            String maxtime = rs.getString("mtime").substring(0, 16);

            ResultSet readings = DB.getData("SELECT* FROM allsenreding WHERE SUBSTRING(reading_time,1, 16)  = '" + maxtime + "' AND sensor_type_id = '" + Sentypeid + "' ");
            while (readings.next()) {
                TotalSensorReadings toread = new TotalSensorReadings();
                toread.setSensor_id(readings.getInt("sensor_id"));
                toread.setSen_type_id(readings.getInt("sensor_type_id"));
                toread.setSen_type_name(readings.getString("sensor_type_name"));
                toread.setFamily_code(readings.getString("family_code"));
                toread.setSen_serial(readings.getString("sensor_serial"));
                toread.setSen_description(readings.getString("description"));
                toread.setSen_min(readings.getString("minimum"));
                toread.setSen_max(readings.getString("maximum"));
                toread.setReading_id(readings.getInt("reading_id"));
                toread.setReading(readings.getString("reading"));
                toread.setReading_datime(readings.getString("reading_time"));
                if ((Integer.parseInt(toread.getSen_max()) > Integer.parseInt(toread.getReading())) && (Integer.parseInt(toread.getReading()) > Integer.parseInt(toread.getSen_min()))) {

                    if (readings.getDouble("minUpper") < readings.getDouble("reading") && readings.getDouble("reading") < readings.getDouble("maxLower")) {
                        toread.setColor("green");
                    } else {
                        toread.setColor("#FC6");
                    }

                    toread.setNormal(true);
                } else {
                    toread.setColor("red");
                    toread.setNormal(false);
                }

                redng.add(toread);
            }

        } catch (Exception e) {
        }
        return redng;
    }

    public ArrayList<TotalSensorReadings> getLatestReadingsCharts(String sensorID) {
        ArrayList<TotalSensorReadings> redng = new ArrayList<TotalSensorReadings>();
        try {



            ResultSet readings = DB.getData("SELECT*,SUBSTRING(reading_time,11,16) as timer1 FROM reading WHERE sensor_id = '" + sensorID + "' ");
            while (readings.next()) {
                TotalSensorReadings toread = new TotalSensorReadings();
                toread.setReading_id(readings.getInt("reading_id"));
                toread.setSensor_id(readings.getInt("sensor_id"));
                toread.setReading(readings.getString("reading"));
                toread.setReading_datime(readings.getString("reading_time"));
                toread.setReadingTime(readings.getString("timer1"));
                redng.add(toread);
            }

        } catch (Exception e) {
        }
        return redng;
    }

    public ArrayList<TotalSensorReadings> getLatestOverReadings() {
        ArrayList<TotalSensorReadings> redng = new ArrayList<TotalSensorReadings>();
        try {

            ResultSet rs = DB.getData("SELECT MAX(reading_time) AS mtime FROM reading");
            rs.first();
            String maxtime = rs.getString("mtime").substring(0, 16);

            ResultSet readings = DB.getData("SELECT* FROM allsenreding WHERE SUBSTRING(reading_time,1, 16)  = '" + maxtime + "' AND (maxLower  <= reading || reading <=minUpper ) ");
            while (readings.next()) {
                TotalSensorReadings toread = new TotalSensorReadings();
                toread.setSensor_id(readings.getInt("sensor_id"));
                toread.setSen_type_id(readings.getInt("sensor_type_id"));
                toread.setSen_type_name(readings.getString("sensor_type_name"));
                toread.setFamily_code(readings.getString("family_code"));
                toread.setSen_serial(readings.getString("sensor_serial"));
                toread.setSen_description(readings.getString("description"));
                toread.setSen_min(readings.getString("minimum"));
                toread.setSen_max(readings.getString("maximum"));
                toread.setReading_id(readings.getInt("reading_id"));
                toread.setReading(readings.getString("reading"));
                toread.setReading_datime(readings.getString("reading_time"));
                if ((readings.getDouble("minimum") < readings.getDouble("reading")) && (readings.getDouble("reading") < readings.getDouble("maximum"))) {
                    toread.setInDanger(false);
                } else {
                    toread.setInDanger(true);
                }

                redng.add(toread);
            }

        } catch (Exception e) {
        }
        return redng;
    }

    public ArrayList<TotalSensorReadings> getLatestReadings() {
        ArrayList<TotalSensorReadings> redng = new ArrayList<TotalSensorReadings>();
        try {

            ResultSet rs = DB.getData("SELECT MAX(reading_time) AS mtime FROM reading");
            rs.first();
            String maxtime = rs.getString("mtime").substring(0, 16);

            ResultSet readings = DB.getData("SELECT* FROM allsenreding WHERE SUBSTRING(reading_time,1, 16)  = '" + maxtime + "'  ");
            while (readings.next()) {
                TotalSensorReadings toread = new TotalSensorReadings();
                toread.setSensor_id(readings.getInt("sensor_id"));
                toread.setSen_type_id(readings.getInt("sensor_type_id"));
                toread.setSen_type_name(readings.getString("sensor_type_name"));
                toread.setFamily_code(readings.getString("family_code"));
                toread.setSen_serial(readings.getString("sensor_serial"));
                toread.setSen_description(readings.getString("description"));
                toread.setSen_min(readings.getString("minimum"));
                toread.setSen_max(readings.getString("maximum"));
                toread.setReading_id(readings.getInt("reading_id"));
                toread.setReading(readings.getString("reading"));
                toread.setReading_datime(readings.getString("reading_time"));
                if ((Integer.parseInt(toread.getSen_max()) > Integer.parseInt(toread.getReading())) && (Integer.parseInt(toread.getReading()) > Integer.parseInt(toread.getSen_min()))) {
                    toread.setColor("green");
                    toread.setNormal(true);
                } else {
                    toread.setColor("red");
                    toread.setNormal(false);
                }

                redng.add(toread);
            }

        } catch (Exception e) {
        }
        return redng;
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
     * @return the sen_type_id
     */
    public int getSen_type_id() {
        return sen_type_id;
    }

    /**
     * @param sen_type_id the sen_type_id to set
     */
    public void setSen_type_id(int sen_type_id) {
        this.sen_type_id = sen_type_id;
    }

    /**
     * @return the sen_type_name
     */
    public String getSen_type_name() {
        return sen_type_name;
    }

    /**
     * @param sen_type_name the sen_type_name to set
     */
    public void setSen_type_name(String sen_type_name) {
        this.sen_type_name = sen_type_name;
    }

    /**
     * @return the family_code
     */
    public String getFamily_code() {
        return family_code;
    }

    /**
     * @param family_code the family_code to set
     */
    public void setFamily_code(String family_code) {
        this.family_code = family_code;
    }

    /**
     * @return the sen_serial
     */
    public String getSen_serial() {
        return sen_serial;
    }

    /**
     * @param sen_serial the sen_serial to set
     */
    public void setSen_serial(String sen_serial) {
        this.sen_serial = sen_serial;
    }

    /**
     * @return the Sen_description
     */
    public String getSen_description() {
        return Sen_description;
    }

    /**
     * @param Sen_description the Sen_description to set
     */
    public void setSen_description(String Sen_description) {
        this.Sen_description = Sen_description;
    }

    /**
     * @return the sen_min
     */
    public String getSen_min() {
        return sen_min;
    }

    /**
     * @param sen_min the sen_min to set
     */
    public void setSen_min(String sen_min) {
        this.sen_min = sen_min;
    }

    /**
     * @return the sen_max
     */
    public String getSen_max() {
        return sen_max;
    }

    /**
     * @param sen_max the sen_max to set
     */
    public void setSen_max(String sen_max) {
        this.sen_max = sen_max;
    }

    /**
     * @return the reading_id
     */
    public int getReading_id() {
        return reading_id;
    }

    /**
     * @param reading_id the reading_id to set
     */
    public void setReading_id(int reading_id) {
        this.reading_id = reading_id;
    }

    /**
     * @return the reading
     */
    public String getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    /**
     * @return the reading_datime
     */
    public String getReading_datime() {
        return reading_datime;
    }

    /**
     * @param reading_datime the reading_datime to set
     */
    public void setReading_datime(String reading_datime) {
        this.reading_datime = reading_datime;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the normal
     */
    public boolean isNormal() {
        return normal;
    }

    /**
     * @param normal the normal to set
     */
    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    /**
     * @return the inDanger
     */
    public boolean isInDanger() {
        return inDanger;
    }

    /**
     * @param inDanger the inDanger to set
     */
    public void setInDanger(boolean inDanger) {
        this.inDanger = inDanger;
    }

    /**
     * @return the readingTime
     */
    public String getReadingTime() {
        return readingTime;
    }

    /**
     * @param readingTime the readingTime to set
     */
    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
}
