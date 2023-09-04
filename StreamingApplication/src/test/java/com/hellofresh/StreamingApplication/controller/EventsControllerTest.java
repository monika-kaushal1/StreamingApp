package com.hellofresh.StreamingApplication.controller;

import com.hellofresh.StreamingApplication.StreamingApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StreamingApplication.class)
public class EventsControllerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    int port;

    @Test
    void shouldAcceptEvent(){
        ResponseEntity<String> response = restTemplateBuilder.build().postForEntity("http://localhost:" + port
                + "/event", "1607341341814,0.0442672968,1282509067", String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    void shouldAcceptEventExceptionForX(){
        try {
            restTemplateBuilder.build().postForEntity("http://localhost:" + port
                    + "/event", "1607341341814,,1282509067", String.class);

        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("400 : \"Parameter missing - x\"");
        }

    }

    @Test
    void shouldAcceptEventExceptionForFormat(){
        try {
            restTemplateBuilder.build().postForEntity("http://localhost:" + port
                    + "/event", "1607341341814,0.0442672968,", String.class);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("400 : \"Event format not proper\"");
        }

    }
}
