package br.com.ilink.zenviaapisoap.exceptions;

public class HttpClientException extends RuntimeException {

  public HttpClientException(Exception e) {
    super(e);
  }

  public HttpClientException() {
    super();
  }
}
