package springone.messageboardadmin;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdminService {

    private final RestTemplate restTemplate;

    private CircuitBreakerFactory circuitBreakerFactory;

    @Setter
    @Value("${message-board.baseUrl:http://localhost:8080}")
    private String baseUrl;

    public AdminService(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    Result deleteMessageByUsername(String username) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        return circuitBreakerFactory.create("deleteMessageByUsernameCircuitBreaker").run(() ->
                this.restTemplate.exchange(
                    baseUrl + "/message?username={username}",
                    HttpMethod.DELETE,
                    request,
                    Result.class,
                    username
                ).getBody(),
                throwable -> {
                    return new Result("Failure", "Delete", "-1");
                });

    }

    public Result deleteMessageByKeyword(String keyword) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Result> response = this.restTemplate.exchange(
                baseUrl + "/message?keyword={keyword}",
                HttpMethod.DELETE,
                request,
                Result.class,
                keyword
        );
        return response.getBody();
    }
}
