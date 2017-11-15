package com.putt2gether.bean;

/**
 * Created by Ajay on 11/05/2016.
 */

/*{
  "output": {
    "user_id": 1,
    "email_id": "sachin@soms.in",
    "full_name": "Sachin Dhawan",
    "display_name": "Sachin Dhawan",
    "success": "1",
    "access_token": "MQT7Mji4OQT9NFtlF9F8A9EEyjh9EmS4RWlGRGU6yG8kSGcIS7tnwUx7BlSODVhUT7tEMGAjxVTcMmhO"
  }
}
*/

public class SignUpBean {

    private String user_id;
    private String email_id;
    private String photo_url;
    private String msg;

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    private String full_name;
    private String display_name;
    private String errorMSG;

    public String getErrorMSG() {
        return errorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        this.errorMSG = errorMSG;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }



    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    private String success;
    private String access_token;

}
