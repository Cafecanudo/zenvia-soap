package br.com.ilink.zenviaapisoap;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import br.com.ilink.zenviaapisoap.ZenviaAPI.EnviarSMS;
import br.com.ilink.zenviaapisoap.exceptions.ProcessorException;
import br.com.ilink.zenviaapisoap.exceptions.ValidationException;
import br.com.ilink.zenviaapisoap.models.SMSRequest;
import br.com.ilink.zenviaapisoap.models.SMSRequest.SMSRequestBuilder;
import br.com.ilink.zenviaapisoap.models.SMSResponse;
import br.com.ilink.zenviaapisoap.models.SMSResponseCheckProtocolo;
import br.com.ilink.zenviaapisoap.ws.MTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ZenviaAPI.class, EnviarSMS.class})
public class ZenviaAPITest {

  Properties prop;

  @Rule
  ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() throws IOException {
    MockitoAnnotations.initMocks(this);
    prop = new Properties();
    prop.load(ZenviaAPI.class.getResourceAsStream("/config.properties"));
    Whitebox.setInternalState(ZenviaAPI.class, "prop", prop);
  }

  @Test
  public void testTentaCheckSMSComCodigoClient() throws MTException {
    SMSResponseCheckProtocolo resp = ZenviaAPI.prepare("987654335")
        .check();

    assertThat(resp, is(notNullValue()));
    assertThat(resp.getCarrierDeliveryStatus(), is("TARGET_OK"));
    assertThat(resp.getDeliveryReceiptStatus(), is("SUCCESS"));
  }

  @Test
  public void testTentaCheckSMSComCodigoClientInvalido() throws MTException {
    SMSResponseCheckProtocolo resp = ZenviaAPI.prepare(randomAlphanumeric(19))
        .check();

    assertThat(resp, is(notNullValue()));
    assertThat(resp.getCarrierDeliveryStatus(), is("NOT_FOUND"));
    assertThat(resp.getDeliveryReceiptStatus(), is("UNKNOWN"));
  }

  @Test
  public void testTentaCheckSMSComProtocoloInvalido() throws MTException {
    SMSResponseCheckProtocolo resp = ZenviaAPI.prepare(Long.valueOf(randomNumeric(19)))
        .check();

    assertThat(resp, is(notNullValue()));
    assertThat(resp.getCarrierDeliveryStatus(), is("NOT_FOUND"));
    assertThat(resp.getDeliveryReceiptStatus(), is("UNKNOWN"));
  }

  @Test
  public void testCheckProtocoloSMSEnviado() throws MTException {
    SMSResponseCheckProtocolo resp = ZenviaAPI.prepare(2019012500037253854L)
        .check();

    assertThat(resp, is(notNullValue()));
    assertThat(resp.getCarrierDeliveryStatus(), is("TARGET_OK"));
    assertThat(resp.getDeliveryReceiptStatus(), is("SUCCESS"));
  }

  @Test
  public void testDeEnvioSucesso() throws Exception {
    SMSRequest req = SMSRequest.builder()
        .phone("553498" + randomNumeric(7))
        .phone("553498" + randomNumeric(7))
        .phone("553499" + randomNumeric(7))
        .phone("553499" + randomNumeric(7))
        .messageText("Mensagem")
        .clientsMessageId(randomNumeric(5))
        .build();

    EnviarSMS enviarSMS = ZenviaAPI.prepare(req);
    enviarSMS = spy(enviarSMS);

    doReturn(4564L)
        .when(enviarSMS, "send", anyString());

    List<SMSResponse> resp = enviarSMS.enviar();

    assertThat(resp, is(notNullValue()));
    assertThat(resp, not(IsEmptyCollection.empty()));
    assertThat(resp, is(hasSize(4)));
    assertThat(resp, contains(new SMSResponse(4564L)
        , new SMSResponse(4564L), new SMSResponse(4564L), new SMSResponse(4564L)));
  }

  @Test
  public void testErroNoEnvioPhoneVazioValidacaoServidor() throws Exception {
    expectedException.expect(ProcessorException.class);
    expectedException.expectMessage("status=[INVALID_PARAMETER]: Invalid phone");

    SMSRequest req = new SMSRequest();
    req.addPhone("");
    req.setMessageText(randomAlphabetic(20));
    req.setClientsMessageId(randomNumeric(10));

    SMSRequestBuilder reqBuild = spy(SMSRequest.builder());
    doReturn(req).when(reqBuild, "build");

    ZenviaAPI.prepare(reqBuild.build()).enviar();
  }

  @Test
  public void testErroNoEnvioPhoneInvalidoValidacaoServidor() throws Exception {
    expectedException.expect(ProcessorException.class);
    expectedException.expectMessage(
        "br.com.ilink.zenviaapisoap.ws.MTException: status=[REFUSED]: The phone number 556465 is not a valid cellphone number!");

    SMSRequest req = new SMSRequest();
    req.addPhone("556465");
    req.setMessageText(randomAlphabetic(20));
    req.setClientsMessageId(randomNumeric(10));

    SMSRequestBuilder reqBuild = spy(SMSRequest.builder());
    doReturn(req).when(reqBuild, "build");

    ZenviaAPI.prepare(reqBuild.build()).enviar();
  }

  @Test
  public void testErroNoEnvioMensagemVaziaValidacaoServidor() throws Exception {
    expectedException.expect(ProcessorException.class);
    expectedException.expectMessage(
        "br.com.ilink.zenviaapisoap.ws.MTException: status=[MISSING_PARAMETER]: Missing messageText");

    SMSRequest req = new SMSRequest();
    req.addPhone(randomNumeric(12));
    req.setClientsMessageId(randomNumeric(10));

    SMSRequestBuilder reqBuild = spy(SMSRequest.builder());
    doReturn(req).when(reqBuild, "build");

    ZenviaAPI.prepare(reqBuild.build()).enviar();
  }

  @Test
  public void testErroNoEnvioPhoneVazio() {
    expectedException.expect(ValidationException.class);
    expectedException.expectMessage(
        "(phone) Quantidade mínima da lista é 1 e máxima de [SEM LIMITES] itens, informou [0].");

    SMSRequest req = SMSRequest.builder()
        .messageText("Mensagem")
        .clientsMessageId(randomNumeric(5))
        .build();

    ZenviaAPI.prepare(req).enviar();
  }

  @Test
  public void testErroNoEnvioPhoneInvalido() {
    expectedException.expect(ValidationException.class);
    expectedException
        .expectMessage("(phone) Quantidade mínimo 12 e máxima de 13 caracteres, informou [5].");

    SMSRequest req = SMSRequest.builder()
        .phone(randomNumeric(5))
        .messageText("Mensagem")
        .clientsMessageId(randomNumeric(5))
        .build();

    ZenviaAPI.prepare(req).enviar();
  }

  @Test
  public void testErroNoEnvioMensagemVazia() {
    expectedException.expect(ValidationException.class);
    expectedException.expectMessage("(messageText) Não pode ser vazio.");

    SMSRequest req = SMSRequest.builder()
        .phone(randomNumeric(12))
        .clientsMessageId(randomNumeric(5))
        .build();

    ZenviaAPI.prepare(req).enviar();
  }

  @Test
  public void testEnvioComLoginInvalido() throws Exception {
    prop = spy(prop);
    when(prop, "getProperty", "config.user")
        .thenReturn("usuario-invalido");
    Whitebox.setInternalState(ZenviaAPI.class, "prop", prop);

    SMSRequest req = SMSRequest.builder()
        .phone("5562996020304")
        .messageText("Mensagem")
        .clientsMessageId(randomNumeric(5))
        .build();

    expectedException.expect(ProcessorException.class);
    expectedException.expectMessage(
        "br.com.ilink.zenviaapisoap.ws.MTException: status=[ACCESS_DENIED]: Invalid user and/or password!");

    ZenviaAPI.prepare(req).enviar();
  }
}