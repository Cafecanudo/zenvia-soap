package br.com.ilink.zenviaapisoap.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://basic.webservice.gateway.springwireless.com/", name = "BasicMT")
@XmlSeeAlso({ObjectFactory.class})
public interface BasicMT {

  @WebMethod
  @RequestWrapper(localName = "checkStatus", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.CheckStatus")
  @ResponseWrapper(localName = "checkStatusResponse", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.CheckStatusResponse")
  @WebResult(name = "messageStatus")
  MessageStatus checkStatus(
      @WebParam(name = "user")
          String user,
      @WebParam(name = "password")
          String password,
      @WebParam(name = "messageId")
          long messageId
  ) throws MTException;

  @WebMethod
  @RequestWrapper(localName = "checkStatusByClientsId", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.CheckStatusByClientsId")
  @ResponseWrapper(localName = "checkStatusByClientsIdResponse", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.CheckStatusByClientsIdResponse")
  @WebResult(name = "messageStatus")
  MessageStatus checkStatusByClientsId(
      @WebParam(name = "user")
          String user,
      @WebParam(name = "password")
          String password,
      @WebParam(name = "clientsMessageId")
          String clientsMessageId
  ) throws MTException;

  @WebMethod
  @RequestWrapper(localName = "send", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.Send")
  @ResponseWrapper(localName = "sendResponse", targetNamespace = "http://basic.webservice.gateway.springwireless.com/", className = "br.com.ilink.zenviaapisoap.ws.SendResponse")
  @WebResult(name = "messageId")
  long send(
      @WebParam(name = "user")
          String user,
      @WebParam(name = "password")
          String password,
      @WebParam(name = "phone")
          String phone,
      @WebParam(name = "messageText")
          String messageText,
      @WebParam(name = "clientsMessageId")
          String clientsMessageId
  ) throws MTException;
}
