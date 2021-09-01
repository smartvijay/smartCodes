package com.example.savelife.Utils;

public class Session {

    private static Session sessionData = null;

    private String user_email = "";
    private String user_pass = "";
    public static Session getInstance(){

        if(sessionData == null)
        {
            sessionData = new Session();
        }
        return sessionData;
    }


    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
