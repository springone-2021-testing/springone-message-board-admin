package springone.messageboardadmin;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@AutoConfigureStubRunner(ids = "springone:message-board-contracts:1.0.0",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class AdminServiceBackCompatibilityContractTests {

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
        Result result = this.service.deleteMessageByUsername("Cora");
        BDDAssertions.then(result.getMessage()).isEqualTo("Failure");
        BDDAssertions.then(result.getType()).isEqualTo("Delete");
        BDDAssertions.then(result.getParameter()).isEqualTo("-1");
    }

}
