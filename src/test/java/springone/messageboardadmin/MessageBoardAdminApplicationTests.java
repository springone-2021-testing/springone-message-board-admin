package springone.messageboardadmin;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@AutoConfigureWireMock( port = 8080 )
@SpringBootTest
class MessageBoardAdminApplicationTests {

	@Autowired
	private MessageBoardAdminClient messageBoardAdminClient;

	@Test
	void contextLoads() {

		var body = "{\"message\":\"Success\",\"type\":\"Delete\",\"parameter\":\"1\"}";
		WireMock.stubFor(WireMock.delete(WireMock.urlEqualTo("/message/Cora"))
			.willReturn(WireMock.aResponse().withBody(body)
			.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			)
		);

		AdminApiResponse result = this.messageBoardAdminClient.deleteMessage("Cora");
		Assertions.assertTrue(result.getMessage().equals("Success"), "the result should have the correct message");
		Assertions.assertTrue(result.getType().equals("Delete"), "the result should have the correct type");
		Assertions.assertTrue(result.getParameter().equals("1"), "the result should have the correct parameter");
	}

}
