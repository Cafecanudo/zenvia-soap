package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkStatusByClientsId", propOrder = {
    "user",
    "password",
    "clientsMessageId"
})
public class CheckStatusByClientsId {

  protected String user;
  protected String password;
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

  public String getClientsMessageId() {
    return clientsMessageId;
  }

  public void setClientsMessageId(String value) {
    this.clientsMessageId = value;
  }

}
