/**
 * @file ChatData.java
 * @author Lee, Dongho - 502646032
 * @date Feb/14/2017
 * @brief
 * @copyright (c) 2017. GE Appliances, a Haier Company (Confidential) All rights reserved.
 */
package gea.com.patternchat.vo;

public class ChatData {
    private String userName;
    private String message;
    private String creationTime;

    public ChatData() { }

    public ChatData(String userName, String creationTime, String message) {
        this.userName = userName;
        this.message = message;
        this.creationTime = creationTime;
    }

    public String getCreationTime() {

        return creationTime;
    }

    public void setCreationTime(String creationTime) {

        this.creationTime = creationTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
