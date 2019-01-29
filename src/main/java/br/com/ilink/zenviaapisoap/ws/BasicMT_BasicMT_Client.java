package br.com.ilink.zenviaapisoap.ws;

/**
 * Please modify this class to meet your needs This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

public final class BasicMT_BasicMT_Client {

  private static final QName SERVICE_NAME = new QName(
      "http://impl.webservice.gateway.springwireless.com/", "BasicSMS");

  private BasicMT_BasicMT_Client() {
  }

  public static void main(String args[]) {
    URL wsdlURL = BasicSMS.WSDL_LOCATION;
    if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
      File wsdlFile = new File(args[0]);
      try {
        if (wsdlFile.exists()) {
          wsdlURL = wsdlFile.toURI().toURL();
        } else {
          wsdlURL = new URL(args[0]);
        }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }

    BasicSMS ss = new BasicSMS(wsdlURL, SERVICE_NAME);
    BasicMT port = ss.getBasicMT();

    {
      System.out.println("Invoking checkStatus...");
      String _checkStatus_user = "";
      String _checkStatus_password = "";
      long _checkStatus_messageId = 0;
      try {
        MessageStatus _checkStatus__return = port
            .checkStatus(_checkStatus_user, _checkStatus_password, _checkStatus_messageId);
        System.out.println("checkStatus.result=" + _checkStatus__return);

      } catch (MTException e) {
        System.out.println("Expected exception: MTException has occurred.");
        System.out.println(e.toString());
      }
    }
    {
      System.out.println("Invoking checkStatusByClientsId...");
      String _checkStatusByClientsId_user = "";
      String _checkStatusByClientsId_password = "";
      String _checkStatusByClientsId_clientsMessageId = "";
      try {
        MessageStatus _checkStatusByClientsId__return = port
            .checkStatusByClientsId(_checkStatusByClientsId_user, _checkStatusByClientsId_password,
                _checkStatusByClientsId_clientsMessageId);
        System.out.println("checkStatusByClientsId.result=" + _checkStatusByClientsId__return);

      } catch (MTException e) {
        System.out.println("Expected exception: MTException has occurred.");
        System.out.println(e.toString());
      }
    }
    {
      System.out.println("Invoking send...");
      String _send_user = "";
      String _send_password = "";
      String _send_phone = "";
      String _send_messageText = "";
      String _send_clientsMessageId = "";
      try {
        long _send__return = port.send(_send_user, _send_password, _send_phone, _send_messageText,
            _send_clientsMessageId);
        System.out.println("send.result=" + _send__return);

      } catch (MTException e) {
        System.out.println("Expected exception: MTException has occurred.");
        System.out.println(e.toString());
      }
    }

    System.exit(0);
  }

}
