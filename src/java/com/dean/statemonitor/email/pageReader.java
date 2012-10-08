
package com.dean.statemonitor.email;

import java.io.InputStream;

/**
 *
 * @author Imal
 */
public class pageReader {

    private String content;
    private String page;
    private String parameters;
    private String message;
    private String username;

    public pageReader() {

        try {

            InputStream stream = pageReader.class.getResourceAsStream("changepass.html");
            content = new java.util.Scanner(stream).useDelimiter("\\A").next();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

   

    /**
     * @return the content
     */
    public String getContent() {
        String url = getPage()+"?"+getParameters();
        String content1 = content.replace("@@@@", url).replace("###", getUsername());
        return content1.replace("@#@", getMessage());
    }

    /**
     * @param content the content to set
     */
    

    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return the parameters
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
}
