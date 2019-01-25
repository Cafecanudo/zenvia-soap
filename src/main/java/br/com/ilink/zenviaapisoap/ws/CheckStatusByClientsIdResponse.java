package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkStatusByClientsIdResponse", propOrder = {
    "messageStatus"
})
public class CheckStatusByClientsIdResponse {

  protected MessageStatus messageStatus;

  public MessageStatus getMessageStatus() {
    return messageStatus;
  }

  public void setMessageStatus(MessageStatus value) {
    this.messageStatus = value;
  }

}
