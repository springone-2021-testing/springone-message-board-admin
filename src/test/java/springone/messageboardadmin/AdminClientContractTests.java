package springone.messageboardadmin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@AutoConfigureStubRunner(ids = "springone:message-board-service:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@SpringBootTest
public class AdminClientContractTests {

    @Autowired
    private MessageBoardAdminClient messageBoardAdminClient;

    @Test
    void testContract() {

        AdminApiResponse result = this.messageBoardAdminClient.deleteMessage("Cora");
        Assertions.assertTrue(result.getMessage().equals("Success"), "the result should have the correct message");
        Assertions.assertTrue(result.getType().equals("Delete"), "the result should have the correct type");
        Assertions.assertTrue(result.getParameter().equals("1"), "the result should have the correct parameter");

    }
}
