
package br.com.ilink.zenviaapisoap.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "BasicSMS", targetNamespace = "http://impl.webservice.gateway.springwireless.com/", wsdlLocation = "http://webservice.zenvia360.com.br:50983/BasicMT?wsdl")
public class BasicSMS
    extends Service {

  private final static URL BASICSMS_WSDL_LOCATION;
  private final static WebServiceException BASICSMS_EXCEPTION;
  private final static QName BASICSMS_QNAME = new QName(
      "http://impl.webservice.gateway.springwireless.com/", "BasicSMS");

  static {
    URL url = null;
    WebServiceException e = null;
    try {
      url = new URL("http://webservice.zenvia360.com.br:50983/BasicMT?wsdl");
    } catch (MalformedURLException ex) {
      e = new WebServiceException(ex);
    }
    BASICSMS_WSDL_LOCATION = url;
    BASICSMS_EXCEPTION = e;
  }

  public BasicSMS() {
    super(getWsdlLocation(), BASICSMS_QNAME);
  }

  public BasicSMS(WebServiceFeature... features) {
    super(getWsdlLocation(), BASICSMS_QNAME, features);
  }

  public BasicSMS(URL wsdlLocation) {
    super(wsdlLocation, BASICSMS_QNAME);
  }

  public BasicSMS(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, BASICSMS_QNAME, features);
  }

  public BasicSMS(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public BasicSMS(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
  }

  @WebEndpoint(name = "BasicMT")
  public BasicMT getBasicMT() {
    return super.getPort(new QName("http://impl.webservice.gateway.springwireless.com/", "BasicMT"),
        BasicMT.class);
  }

  @WebEndpoint(name = "BasicMT")
  public BasicMT getBasicMT(WebServiceFeature... features) {
    return super.getPort(new QName("http://impl.webservice.gateway.springwireless.com/", "BasicMT"),
        BasicMT.class, features);
  }

  private static URL getWsdlLocation() {
    if (BASICSMS_EXCEPTION != null) {
      throw BASICSMS_EXCEPTION;
    }
    return BASICSMS_WSDL_LOCATION;
  }

}