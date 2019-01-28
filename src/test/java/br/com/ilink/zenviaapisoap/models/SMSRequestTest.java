package br.com.ilink.zenviaapisoap.models;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SMSRequestTest {

  @Rule
  public ExpectedException exceptionGrabber = ExpectedException.none();

  @Test
  public void testMessageVazio() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber.expectMessage("Não pode ser vazio.");

    SMSRequest.builder()
        .phone(randomNumeric(13))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testMessageMax() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber.expectMessage(
        "(messageText) Quantidade máxima de 160 caracteres.");

    SMSRequest.builder()
        .phone(randomNumeric(13))
        .messageText(randomAlphabetic(161))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testPhoneMin12() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber
        .expectMessage("(phone) Quantidade mínimo 12 e máxima de 13 caracteres, informou [10].");

    SMSRequest.builder()
        .phone(randomNumeric(10))
        .messageText(randomAlphabetic(160))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testPhoneMax13() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber
        .expectMessage("(phone) Quantidade mínimo 12 e máxima de 13 caracteres, informou [14].");

    SMSRequest.builder()
        .phone(randomNumeric(14))
        .messageText(randomAlphabetic(160))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testPhoneVazio() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber.expectMessage("(phone) Quantidade mínima da lista é 1 e máxima de [SEM LIMITES] itens, informou [0]");

    SMSRequest.builder()
        .messageText(randomAlphabetic(160))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testPhoneSomenteNumero() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber.expectMessage("(phone) Somente números.");

    SMSRequest.builder()
        .phone(randomAlphabetic(13))
        .messageText(randomAlphabetic(160))
        .clientsMessageId(randomNumeric(19))
        .build();
  }

  @Test
  public void testClientsMessageIdMax() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber
        .expectMessage("Este número é convertido em LONG, e não pode ser maior que 19 números.");

    SMSRequest.builder()
        .phone(randomNumeric(13))
        .messageText(randomAlphabetic(160))
        .clientsMessageId("90432316492050020268")
        .build();
  }

  @Test
  public void testClientsMessageIdVazio() throws ValidationException {
    exceptionGrabber.expect(ValidationException.class);
    exceptionGrabber.expectMessage("Não pode ser vazio.");

    SMSRequest.builder()
        .phone(randomNumeric(13))
        .messageText(randomAlphabetic(160))
        .build();
  }
}