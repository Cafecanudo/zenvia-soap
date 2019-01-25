package br.com.ilink.zenviaapisoap.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSResponseCheckProtocolo {

  private String carrierDeliveryStatus;
  private String deliveryReceiptStatus;
}
