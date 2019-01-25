package br.com.ilink.zenviaapisoap.models;

import br.com.ilink.zenviaapisoap.annotations.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSResponse {

  @NotBlank
  private Long messageId;

}
