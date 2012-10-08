package com.dean.statemonitor.Model;

import com.dean.statemonitor.Properties.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class User {

    private int userId;
    private int type;
    private String username;
    private String password;
    private String name;
    private String email;
    private String postion;
    private String activeState;
    private boolean avilable;

    public void getLoggedIN() {

        try {
            ResultSet rs = DB.getData("SELECT 	user_id,TYPE,name,email,POSITION FROM USER where username='" + getUsername() + "' AND PASSWORD ='" + getPassword() + "' AND is_active='1' ");
            if (rs.next()) {
                setname(rs.getString("name"));
                setUserId(rs.getInt("user_id"));
                setType(rs.getInt("TYPE"));
                setemail(rs.getString("email"));
                setPostion(rs.getString("POSITION"));
                setAvilable(true);
            } else {
                setAvilable(false);
            }

        } catch (Exception e) {
            System.out.println(e + "error in login");
        }

    }

    public User getUser() {
        User u = null;
        try {
            ResultSet rs = DB.getData("SELECT 	username,user_id,TYPE,name,email,POSITION,password FROM USER where user_id='" + getUserId() + "' ");
            if (rs.next()) {
                u = new User();
                u.setUsername(rs.getString("username"));
                u.setname(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setUserId(rs.getInt("user_id"));
                u.setType(rs.getInt("TYPE"));
                u.setemail(rs.getString("email"));
                u.setPostion(rs.getString("POSITION"));
                u.setAvilable(true);
            } else {
                u.setAvilable(false);
            }

        } catch (Exception e) {
            System.out.println(e + "error in login");
        }
        return u;
    }

    public int addUserToDB() {
        int id = 0;
        try {

            Connection con = DB.getCon();
            Statement st = con.createStatement();

            String sql = "INSERT INTO USER "
                    + "(TYPE, "
                    + "username,  "
                    + "PASSWORD,  "
                    + "NAME,  "
                    + "email,  "
                    + "POSITION "
                    + ") "
                    + "VALUES "
                    + "('" + getType() + "',  "
                    + "'" + getUsername() + "',  "
                    + "'" + getPassword() + "',  "
                    + "'" + getname() + "',  "
                    + "'" + getemail() + "',  "
                    + "'" + getPostion() + "' "
                    + ")";

            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            return id;
        } catch (Exception e) {
            System.out.println(e);
            return id;
        }

    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet rs = DB.getData("SELECT 	is_active,user_id,TYPE,name,email,POSITION FROM USER");
            while (rs.next()) {
                User u = new User();
                u.setname(rs.getString("name"));
                u.setUserId(rs.getInt("user_id"));
                u.setType(rs.getInt("TYPE"));
                u.setemail(rs.getString("email"));
                u.setPostion(rs.getString("POSITION"));
                u.setActiveState(rs.getInt("is_active") == 1 ? "Active" : "InActive");
                users.add(u);
            }

        } catch (Exception e) {
            System.out.println(e + "error in login");
        }
        return users;
    }
    
    
    public void SetAvailability(){
        try {
            
            DB.setData("UPDATE USER SET is_active = !is_active WHERE user_id = '"+getUserId()+"' ");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fname
     */
    public String getname() {
        return name;
    }

    /**
     * @param fname the fname to set
     */
    public void setname(String fname) {
        this.name = fname;
    }

    /**
     * @return the lname
     */
    public String getemail() {
        return email;
    }

    /**
     * @param lname the lname to set
     */
    public void setemail(String lname) {
        this.email = lname;
    }

    /**
     * @return the postion
     */
    public String getPostion() {
        return postion;
    }

    /**
     * @param postion the postion to set
     */
    public void setPostion(String postion) {
        this.postion = postion;
    }

    /**
     * @return the avilable
     */
    public boolean isAvilable() {
        return avilable;
    }

    /**
     * @param avilable the avilable to set
     */
    public void setAvilable(boolean avilable) {
        this.avilable = avilable;
    }

    /**
     * @return the activeState
     */
    public String getActiveState() {
        return activeState;
    }

    /**
     * @param activeState the activeState to set
     */
    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }
}
