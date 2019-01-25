package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkStatus", propOrder = {
    "user",
    "password",
    "messageId"
})
public class CheckStatus {

  protected String user;
  protected String password;
  protected long messageId;

  public String getUser() {
    return user;
  }

  public void setUser(String value) {
    this.user = value;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String value) {
    this.password = value;
  }

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long value) {
    this.messageId = value;
  }
}
