package springone.messageboardadmin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class AdminServiceTests {

	@Autowired
	private Environment environment;

	@Autowired
	private AdminService service;

	@BeforeEach
	public void setup() {
		this.service.setBaseUrl("http://localhost:" + this.environment.getProperty("wiremock.server.port"));
	}

	@Test
	void shouldDeleteMessageByUsername() {

		var body = "{\"message\":\"Success\",\"type\":\"Delete\",\"parameter\":\"1\"}";
		stubFor(delete(urlEqualTo("/message/Cora_Iberkleid"))
			.willReturn(aResponse()
			.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.withBody(body)
			)
		);

		Result result = this.service.deleteMessageByUsername("Cora_Iberkleid");
		Assertions.assertTrue(result.getMessage().equals("Success"), "the result should have the correct message");
		Assertions.assertTrue(result.getType().equals("Delete"), "the result should have the correct type");
		Assertions.assertTrue(result.getParameter().equals("1"), "the result should have the correct parameter");
	}

}
