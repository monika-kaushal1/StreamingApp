package com.hellofresh.StreamingApplication.controller;

import com.hellofresh.StreamingApplication.StreamingApplication;
import com.hellofresh.StreamingApplication.dbs.StatsDB;
import com.hellofresh.StreamingApplication.domains.Event;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StreamingApplication.class)
public class StatsControllerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    int port;

    @Test
    public void getStatsTest() {
        // build data
        populateDB();
        ResponseEntity<String> response = restTemplateBuilder.build().getForEntity("http://localhost:" + port
                + "/stats", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String[] result = StringUtils.split(response.getBody(), ",");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert result != null;
        Assertions.assertThat(result[0]).isEqualTo("1000");
    }

    private void populateDB() {
        StatsDB instance = StatsDB.getInstance();
        for(int i = 0; i<1000; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            Event event = new Event(currentTimeMillis, 0.0442672968, 1282509067L);
            instance.addEvent(event, currentTimeMillis/1000);
        }
    }
}
