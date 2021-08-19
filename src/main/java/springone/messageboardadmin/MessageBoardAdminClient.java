package springone.messageboardadmin;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageBoardAdminClient {

    private final RestTemplate restTemplate;

    public MessageBoardAdminClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    AdminApiResponse deleteMessage(String username) {
        ResponseEntity<AdminApiResponse> response = this.restTemplate.exchange(
                "http://localhost:8080/message/{username}",
                HttpMethod.DELETE,
                null,
                AdminApiResponse.class,
                username
        );
        return response.getBody();

    }

}
