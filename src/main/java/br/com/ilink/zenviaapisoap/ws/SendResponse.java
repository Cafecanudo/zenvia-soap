package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendResponse", propOrder = {
    "messageId"
})
public class SendResponse {

  protected long messageId;

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long value) {
    this.messageId = value;
  }

}
