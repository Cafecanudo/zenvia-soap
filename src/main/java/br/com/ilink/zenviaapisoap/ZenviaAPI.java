package br.com.ilink.zenviaapisoap;

import br.com.ilink.zenviaapisoap.exceptions.ProcessorException;
import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import br.com.ilink.zenviaapisoap.models.SMSRequest;
import br.com.ilink.zenviaapisoap.models.SMSResponse;
import br.com.ilink.zenviaapisoap.models.SMSResponseCheckProtocolo;
import br.com.ilink.zenviaapisoap.ws.BasicMT;
import br.com.ilink.zenviaapisoap.ws.BasicSMS;
import br.com.ilink.zenviaapisoap.ws.MTException;
import br.com.ilink.zenviaapisoap.ws.MessageStatus;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ZenviaAPI {

  private static Properties prop;

  static {
    try {
      prop = new Properties();
      prop.load(ZenviaAPI.class.getResourceAsStream("/config.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static EnviarSMS prepare(SMSRequest req) {
    return new ZenviaAPI.EnviarSMS(req);
  }

  public static CheckSMS prepare(long messageId) {
    return new ZenviaAPI.CheckSMS(messageId);
  }

  public static CheckSMS prepare(String clientsMessageId) {
    return new ZenviaAPI.CheckSMS(clientsMessageId);
  }

  /**
   * Classe para checar status SMS por protocolo
   */
  public static class CheckSMS {

    private long messageId;
    private String clientsMessageId;

    public CheckSMS(long messageId) {
      this.messageId = messageId;
    }

    public CheckSMS(String clientsMessageId) {
      this.clientsMessageId = clientsMessageId;
    }

    public SMSResponseCheckProtocolo check() throws MTException {
      MessageStatus check = send();
      return SMSResponseCheckProtocolo.builder()
          .carrierDeliveryStatus(check.getCarrierDeliveryStatus())
          .deliveryReceiptStatus(check.getDeliveryReceiptStatus())
          .build();
    }

    private MessageStatus send() throws MTException {
      BasicMT basic = new BasicSMS().getBasicMT();
      if (this.clientsMessageId != null) {
        return basic.checkStatusByClientsId(
            prop.getProperty("config.user"), prop.getProperty("config.password"),
            this.clientsMessageId
        );
      }
      return basic.checkStatus(prop.getProperty("config.user"), prop.getProperty("config.password"),
          this.messageId);
    }
  }

  /**
   * Classe de envio SMS
   */
  public static class EnviarSMS {

    private SMSRequest req;

    private EnviarSMS(SMSRequest req) {
      this.req = req;
    }

    public List<SMSResponse> enviar() {
      return processar();
    }

    private List<SMSResponse> processar() {
      if (req == null) {
        throw new ValidationException("Requisição está fazia");
      }
      return this.req.getPhone().stream()
          .map(phone -> SMSResponse.builder().messageId(send(phone)).build())
          .collect(Collectors.toList());
    }

    private Long send(String phone) {
      try {
        return new BasicSMS().getBasicMT().send(
            prop.getProperty("config.user"), prop.getProperty("config.password"),
            phone, this.req.getMessageText(), this.req.getClientsMessageId()
        );
      } catch (MTException e) {
        throw new ProcessorException(e);
      }
    }
  }

}
