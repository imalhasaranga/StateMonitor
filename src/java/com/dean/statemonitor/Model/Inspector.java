package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imal
 */
public class Inspector {

    private int InspectorID;
    private String InspectorName;
    private String post;
    private String email;
    private String phone;
    private String Actives;

    public ArrayList<Inspector> getAllInspectors() {

        ArrayList<Inspector> inspect = new ArrayList<Inspector>();
        try {
            ResultSet inspectors = DB.getData("SELECT 	inspectorID, "
                    + "inspectorName, "
                    + "post,  "
                    + "email,  "
                    + "phone,is_active "
                    + "FROM "
                    + "inspectors  "
                    + "");

            while (inspectors.next()) {
                Inspector inspec = new Inspector();
                inspec.setInspectorID(inspectors.getInt("inspectorID"));
                inspec.setInspectorName(inspectors.getString("inspectorName"));
                inspec.setPost(inspectors.getString("post"));
                inspec.setEmail(inspectors.getString("email"));
                inspec.setPhone(inspectors.getString("phone"));
                inspec.setActives(inspectors.getInt("is_active") == 1 ? "Active" : "InActive");

                inspect.add(inspec);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return inspect;
    }

    public Inspector getInspector() {

        Inspector inspec = new Inspector();
        try {
            ResultSet inspectors = DB.getData("SELECT 	inspectorID, "
                    + "inspectorName, "
                    + "post,  "
                    + "email,  "
                    + "phone,is_active "
                    + "FROM "
                    + "inspectors  "
                    + " where inspectorID='" + getInspectorID() + "'");
            if (inspectors.next()) {
                inspec.setInspectorID(inspectors.getInt("inspectorID"));
                inspec.setInspectorName(inspectors.getString("inspectorName"));
                inspec.setPost(inspectors.getString("post"));
                inspec.setEmail(inspectors.getString("email"));
                inspec.setPhone(inspectors.getString("phone"));
                inspec.setActives(inspectors.getInt("is_active") == 1 ? "Active" : "InActive");
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return inspec;
    }

    public void AddInspector() {
        Connection con = null;
        try {

            con = DB.getCon();
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO inspectors "
                    + "(inspectorName,  "
                    + "post,  "
                    + "email,  "
                    + "phone "
                    + ") "
                    + "VALUES "
                    + "('" + getInspectorName() + "',  "
                    + "'" + getPost() + "',  "
                    + "'" + getEmail() + "',  "
                    + "'" + getPhone() + "' "
                    + ")", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            int id0 = rs.getInt(1);

            Sensor sen = new Sensor();
            sensors_inspectors senins = new sensors_inspectors();
            Iterator i = sen.getAllSensors().iterator();
            while (i.hasNext()) {
                sen = (Sensor) i.next();
                if (sen.getActiveState().equals("Active")) {
                    senins.setInspectorID(id0);
                    senins.setSensorID(sen.getSensor_id());
                    senins.addSensor_inspector();
                }
            }
            
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Inspector.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(e);
        }

    }

    /**
     * @return the InspectorID
     */
    public int getInspectorID() {
        return InspectorID;
    }

    /**
     * @param InspectorID the InspectorID to set
     */
    public void setInspectorID(int InspectorID) {
        this.InspectorID = InspectorID;
    }

    /**
     * @return the InspectorName
     */
    public String getInspectorName() {
        return InspectorName;
    }

    /**
     * @param InspectorName the InspectorName to set
     */
    public void setInspectorName(String InspectorName) {
        this.InspectorName = InspectorName;
    }

    /**
     * @return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the Actives
     */
    public String getActives() {
        return Actives;
    }

    /**
     * @param Actives the Actives to set
     */
    public void setActives(String Actives) {
        this.Actives = Actives;
    }
}
