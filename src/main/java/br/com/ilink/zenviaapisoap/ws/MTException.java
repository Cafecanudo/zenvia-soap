package br.com.ilink.zenviaapisoap.ws;

import javax.xml.ws.WebFault;

@WebFault(name = "ErrorHandlingMT", targetNamespace = "http://basic.webservice.gateway.springwireless.com/")
public class MTException
    extends Exception {

  private ErrorHandlingMT faultInfo;

  public MTException(String message, ErrorHandlingMT faultInfo) {
    super(message);
    this.faultInfo = faultInfo;
  }

  public MTException(String message, ErrorHandlingMT faultInfo, Throwable cause) {
    super(message, cause);
    this.faultInfo = faultInfo;
  }

  public ErrorHandlingMT getFaultInfo() {
    return faultInfo;
  }

}
