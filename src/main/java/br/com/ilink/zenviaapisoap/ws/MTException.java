package br.com.ilink.zenviaapisoap.ws;

import javax.xml.ws.WebFault;

@WebFault(name = "ErrorHandlingMT", targetNamespace = "http://basic.webservice.gateway.springwireless.com/")
public class MTException extends Exception {

  private ErrorHandlingMT errorHandlingMT;

  public MTException() {
    super();
  }

  public MTException(String message) {
    super(message);
  }

  public MTException(String message, Throwable cause) {
    super(message, cause);
  }

  public MTException(String message, ErrorHandlingMT errorHandlingMT) {
    super(message);
    this.errorHandlingMT = errorHandlingMT;
  }

  public MTException(String message, ErrorHandlingMT errorHandlingMT, Throwable cause) {
    super(message, cause);
    this.errorHandlingMT = errorHandlingMT;
  }

  public ErrorHandlingMT getFaultInfo() {
    return this.errorHandlingMT;
  }
}
