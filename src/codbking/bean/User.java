package codbking.bean;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

/**
 * Created by codbking on 2017/2/10.
 */
public class User {

    private String emal;
    private String nickName;
    private Message.RecipientType type= MimeMessage.RecipientType.TO;

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Message.RecipientType getType() {
        return type;
    }

    public void setType(Message.RecipientType type) {
        this.type = type;
    }

    public User( Message.RecipientType type,String emal, String nickName) {
        this.emal = emal;
        this.nickName = nickName;
        this.type = type;
    }

    public User(String emal, String nickName) {
        this.emal = emal;
        this.nickName = nickName;
    }
}
