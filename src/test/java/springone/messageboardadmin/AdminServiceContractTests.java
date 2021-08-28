package springone.messageboardadmin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "springone:message-board-contracts:1.0.0")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class AdminServiceContractTests {

    @Autowired
    private AdminService service;

    @Autowired
    StubFinder stubFinder;

    @BeforeEach
    public void setup() {
        this.service.setBaseUrl("http://localhost:" + stubFinder.findStubUrl("message-board-contracts").getPort());
    }

    @Test
    void shouldDeleteMessageByUsername() {
        Result result = this.service.deleteMessageByUsername("Cora_Iberkleid");
        Assertions.assertTrue(result.getMessage().equals("Success"), "the result should have the correct message");
        Assertions.assertTrue(result.getType().equals("Delete"), "the result should have the correct type");
        Assertions.assertTrue(result.getParameter().equals("1"), "the result should have the correct parameter");
    }

    @Test
    void shouldFailDeleteMessageByUsername() {
        Result result = this.service.deleteMessageByUsername("anamethat_doesnotexist");
        Assertions.assertTrue(result.getMessage().equals("Failure"), "the result should have the correct message");
        Assertions.assertTrue(result.getType().equals("Delete"), "the result should have the correct type");
        Assertions.assertTrue(result.getParameter().equals("-1"), "the result should have the correct parameter");
    }

}
