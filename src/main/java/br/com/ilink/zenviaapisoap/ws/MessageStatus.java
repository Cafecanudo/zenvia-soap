
package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageStatus", propOrder = {
    "carrierDeliveryStatus",
    "deliveryReceiptStatus"
})
public class MessageStatus {

  protected String carrierDeliveryStatus;
  protected String deliveryReceiptStatus;

  public String getCarrierDeliveryStatus() {
    return carrierDeliveryStatus;
  }

  public void setCarrierDeliveryStatus(String value) {
    this.carrierDeliveryStatus = value;
  }

  public String getDeliveryReceiptStatus() {
    return deliveryReceiptStatus;
  }

  public void setDeliveryReceiptStatus(String value) {
    this.deliveryReceiptStatus = value;
  }

}
