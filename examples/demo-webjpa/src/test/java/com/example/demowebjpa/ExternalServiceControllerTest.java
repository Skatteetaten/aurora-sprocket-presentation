package com.example.demowebjpa;

import static java.lang.String.format;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RunWith(SpringRunner.class)
@RestClientTest({ ExternalServiceController.class, ApplicationConfiguration.class })
public class ExternalServiceControllerTest {

    @Autowired
    ExternalServiceController externalServiceController;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void verifyForwardsHttpbinResponse() {

        this.server.expect(requestTo("https://httpbin.org/anything/test"))
            .andRespond(withSuccess(new ClassPathResource("com/example/demowebjpa/echo_response.json"), APPLICATION_JSON));

        Map response = externalServiceController.echo("test");
        assertThat(response.get("url")).isEqualTo("https://httpbin.org/anything/test");
    }
}
