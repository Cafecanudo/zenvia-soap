package br.com.ilink.zenviaapisoap.models;

import lombok.Data;

@Data
public class ZenviaConfig {

  private String usuario;
  private String senha;

  public ZenviaConfig(String usuario, String senha) {
    this.usuario = usuario;
    this.senha = senha;
  }

  public static ZenviaConfig build(String usuario, String senha) {
    return new ZenviaConfig(usuario, senha);
  }
}
