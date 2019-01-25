package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send", propOrder = {
    "user",
    "password",
    "phone",
    "messageText",
    "clientsMessageId"
})
public class Send {

  protected String user;
  protected String password;
  protected String phone;
  protected String messageText;
  protected String clientsMessageId;

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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String value) {
    this.phone = value;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String value) {
    this.messageText = value;
  }

  public String getClientsMessageId() {
    return clientsMessageId;
  }

  public void setClientsMessageId(String value) {
    this.clientsMessageId = value;
  }
}
