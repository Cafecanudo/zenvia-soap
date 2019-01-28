package br.com.ilink.zenviaapisoap.models;

import br.com.ilink.zenviaapisoap.BuilderValidation;
import br.com.ilink.zenviaapisoap.annotations.ListNotBlank;
import br.com.ilink.zenviaapisoap.annotations.ListOnlyNumber;
import br.com.ilink.zenviaapisoap.annotations.NotBlank;
import br.com.ilink.zenviaapisoap.annotations.Size;
import br.com.ilink.zenviaapisoap.annotations.SizeEachList;
import br.com.ilink.zenviaapisoap.annotations.SizeList;
import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SMSRequest {

  @SizeList
  @ListNotBlank
  @ListOnlyNumber
  @SizeEachList(min = 12, max = 13)
  @Default
  private Set<String> phone = new HashSet<>();

  @NotBlank
  @Size(max = 160, message = "Quantidade máxima de 160 caracteres.")
  private String messageText;

  @NotBlank
  @Size(max = 19, message = "Este número é convertido em LONG, e não pode ser maior que 19 números.")
  private String clientsMessageId;

  public SMSRequest addPhone(String phone) {
    if (phone != null) {
      this.phone.add(phone);
    }
    return this;
  }

  public static class SMSRequestBuilder extends BuilderValidation<SMSRequest> {

    private Set<String> phone = new HashSet<>();

    public SMSRequest.SMSRequestBuilder phone(Set<String> phone) {
      if (phone != null && !phone.isEmpty()) {
        this.phone.addAll(phone);
      }
      return this;
    }

    public SMSRequest.SMSRequestBuilder phone(String phone) {
      if (phone != null) {
        this.phone.add(phone);
      }
      return this;
    }

    @Override
    public SMSRequest build() throws ValidationException {
      return validarParametros(
          new SMSRequest(this.phone, this.messageText, this.clientsMessageId)
      );
    }
  }
}
