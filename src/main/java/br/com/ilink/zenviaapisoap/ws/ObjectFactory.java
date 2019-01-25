package br.com.ilink.zenviaapisoap.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

  private final static QName _CheckStatus_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "checkStatus");
  private final static QName _CheckStatusByClientsId_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "checkStatusByClientsId");
  private final static QName _CheckStatusByClientsIdResponse_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "checkStatusByClientsIdResponse");
  private final static QName _CheckStatusResponse_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "checkStatusResponse");
  private final static QName _Send_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "send");
  private final static QName _SendResponse_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "sendResponse");
  private final static QName _ErrorHandlingMT_QNAME = new QName(
      "http://basic.webservice.gateway.springwireless.com/", "ErrorHandlingMT");

  public ObjectFactory() {
  }

  public CheckStatus createCheckStatus() {
    return new CheckStatus();
  }

  public CheckStatusByClientsId createCheckStatusByClientsId() {
    return new CheckStatusByClientsId();
  }

  public CheckStatusByClientsIdResponse createCheckStatusByClientsIdResponse() {
    return new CheckStatusByClientsIdResponse();
  }

  public CheckStatusResponse createCheckStatusResponse() {
    return new CheckStatusResponse();
  }

  public Send createSend() {
    return new Send();
  }

  public SendResponse createSendResponse() {
    return new SendResponse();
  }

  public ErrorHandlingMT createErrorHandlingMT() {
    return new ErrorHandlingMT();
  }

  public MessageStatus createMessageStatus() {
    return new MessageStatus();
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "checkStatus")
  public JAXBElement<CheckStatus> createCheckStatus(CheckStatus value) {
    return new JAXBElement<>(_CheckStatus_QNAME, CheckStatus.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "checkStatusByClientsId")
  public JAXBElement<CheckStatusByClientsId> createCheckStatusByClientsId(
      CheckStatusByClientsId value) {
    return new JAXBElement<>(_CheckStatusByClientsId_QNAME,
        CheckStatusByClientsId.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "checkStatusByClientsIdResponse")
  public JAXBElement<CheckStatusByClientsIdResponse> createCheckStatusByClientsIdResponse(
      CheckStatusByClientsIdResponse value) {
    return new JAXBElement<>(_CheckStatusByClientsIdResponse_QNAME,
        CheckStatusByClientsIdResponse.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "checkStatusResponse")
  public JAXBElement<CheckStatusResponse> createCheckStatusResponse(CheckStatusResponse value) {
    return new JAXBElement<>(_CheckStatusResponse_QNAME,
        CheckStatusResponse.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "send")
  public JAXBElement<Send> createSend(Send value) {
    return new JAXBElement<>(_Send_QNAME, Send.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "sendResponse")
  public JAXBElement<SendResponse> createSendResponse(SendResponse value) {
    return new JAXBElement<>(_SendResponse_QNAME, SendResponse.class, null, value);
  }

  @XmlElementDecl(namespace = "http://basic.webservice.gateway.springwireless.com/", name = "ErrorHandlingMT")
  public JAXBElement<ErrorHandlingMT> createErrorHandlingMT(ErrorHandlingMT value) {
    return new JAXBElement<>(_ErrorHandlingMT_QNAME, ErrorHandlingMT.class, null,
        value);
  }
}
