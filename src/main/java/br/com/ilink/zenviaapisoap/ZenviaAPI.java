package br.com.ilink.zenviaapisoap;

import br.com.ilink.zenviaapisoap.exceptions.ProcessorException;
import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import br.com.ilink.zenviaapisoap.models.SMSRequest;
import br.com.ilink.zenviaapisoap.models.SMSResponse;
import br.com.ilink.zenviaapisoap.models.SMSResponseCheckProtocolo;
import br.com.ilink.zenviaapisoap.models.ZenviaConfig;
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

  public static Config config(ZenviaConfig zenviaConfig) {
    return new ZenviaAPI.Config(zenviaConfig);
  }

  public static EnviarSMS prepare(SMSRequest req) {
    return new ZenviaAPI.EnviarSMS(req, null);
  }

  public static CheckSMS prepare(long messageId) {
    return new ZenviaAPI.CheckSMS(messageId, null);
  }

  public static CheckSMS prepare(String clientsMessageId) {
    return new ZenviaAPI.CheckSMS(clientsMessageId, null);
  }

  public static class Config {

    private ZenviaConfig zenviaConfig;

    public Config(ZenviaConfig zenviaConfig) {
      this.zenviaConfig = zenviaConfig;
    }

    public EnviarSMS prepare(SMSRequest req) {
      return new ZenviaAPI.EnviarSMS(req, zenviaConfig);
    }

    public CheckSMS prepare(long messageId) {
      return new ZenviaAPI.CheckSMS(messageId, zenviaConfig);
    }

    public CheckSMS prepare(String clientsMessageId) {
      return new ZenviaAPI.CheckSMS(clientsMessageId, zenviaConfig);
    }

  }

  /**
   * Classe para checar status SMS por protocolo
   */
  public static class CheckSMS {

    private long messageId;
    private String clientsMessageId;
    private ZenviaConfig zenviaConfig;

    public CheckSMS(long messageId, ZenviaConfig zenviaConfig) {
      this.messageId = messageId;
    }

    public CheckSMS(String clientsMessageId, ZenviaConfig zenviaConfig) {
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
            ((zenviaConfig != null && zenviaConfig.getUsuario() != null) ? zenviaConfig.getUsuario()
                : prop.getProperty("config.user")),
            ((zenviaConfig != null && zenviaConfig.getSenha() != null) ? zenviaConfig.getSenha()
                : prop.getProperty("config.password")),
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
    private ZenviaConfig zenviaConfig;

    public EnviarSMS(SMSRequest req, ZenviaConfig zenviaConfig) {
      this.req = req;
      this.zenviaConfig = zenviaConfig;
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
            ((zenviaConfig != null && zenviaConfig.getUsuario() != null) ? zenviaConfig.getUsuario()
                : prop.getProperty("config.user")),
            ((zenviaConfig != null && zenviaConfig.getSenha() != null) ? zenviaConfig.getSenha()
                : prop.getProperty("config.password")),
            phone, this.req.getMessageText(), this.req.getClientsMessageId()
        );
      } catch (MTException e) {
        throw new ProcessorException(e);
      }
    }
  }

}
