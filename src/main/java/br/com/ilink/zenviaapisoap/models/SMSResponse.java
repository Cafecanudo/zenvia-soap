package br.com.ilink.zenviaapisoap.models;

import br.com.ilink.zenviaapisoap.annotations.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSResponse {

  @NotBlank
  private Long messageId;

}
