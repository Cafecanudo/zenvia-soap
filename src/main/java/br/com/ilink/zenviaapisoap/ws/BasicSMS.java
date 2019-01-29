package br.com.ilink.zenviaapisoap.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "BasicSMS",
    wsdlLocation = "http://webservice.zenvia360.com.br:50983/BasicMT?wsdl",
    targetNamespace = "http://impl.webservice.gateway.springwireless.com/")
public class BasicSMS extends Service {

  public final static URL WSDL_LOCATION;

  public final static QName SERVICE = new QName(
      "http://impl.webservice.gateway.springwireless.com/", "BasicSMS");
  public final static QName BasicMT = new QName(
      "http://impl.webservice.gateway.springwireless.com/", "BasicMT");

  static {
    URL url = null;
    try {
      url = new URL("http://webservice.zenvia360.com.br:50983/BasicMT?wsdl");
    } catch (MalformedURLException e) {
      Logger.getLogger(BasicSMS.class.getName())
          .log(Level.INFO,
              "Can not initialize the default wsdl from {0}",
              "http://webservice.zenvia360.com.br:50983/BasicMT?wsdl");
    }
    WSDL_LOCATION = url;
  }

  public BasicSMS(URL wsdlLocation) {
    super(wsdlLocation, SERVICE);
  }

  public BasicSMS(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public BasicSMS() {
    super(WSDL_LOCATION, SERVICE);
  }

  public BasicSMS(WebServiceFeature... features) {
    super(WSDL_LOCATION, SERVICE, features);
  }

  public BasicSMS(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, SERVICE, features);
  }

  public BasicSMS(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
  }

  @WebEndpoint(name = "BasicMT")
  public BasicMT getBasicMT() {
    return super.getPort(BasicMT, BasicMT.class);
  }

  @WebEndpoint(name = "BasicMT")
  public BasicMT getBasicMT(WebServiceFeature... features) {
    return super.getPort(BasicMT, BasicMT.class, features);
  }

}
