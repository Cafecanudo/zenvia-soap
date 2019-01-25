package br.com.ilink.zenviaapisoap.models;

import br.com.ilink.zenviaapisoap.BuilderValidation;
import br.com.ilink.zenviaapisoap.annotations.NotBlank;
import br.com.ilink.zenviaapisoap.annotations.OnlyNumber;
import br.com.ilink.zenviaapisoap.annotations.Size;
import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SMSRequest {

  @NotBlank
  @OnlyNumber
  @Size(min = 12, max = 13)
  private String phone;

  @NotBlank
  @Size(max = 160, message = "Quantidade máxima de 160 caracteres.")
  private String messageText;

  @NotBlank
  @Size(max = 19, message = "Este número é convertido em LONG, e não pode ser maior que 19 números.")
  private String clientsMessageId;

  public static class SMSRequestBuilder extends BuilderValidation<SMSRequest> {

    @Override
    public SMSRequest build() throws ValidationException {
      return validarParametros(
          new SMSRequest(this.phone, this.messageText, this.clientsMessageId)
      );
    }
  }
}
