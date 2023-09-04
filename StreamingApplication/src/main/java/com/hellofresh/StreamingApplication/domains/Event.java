package com.hellofresh.StreamingApplication.domains;

import lombok.Data;

@Data
public class Event {

    private Long timestamp;
    private Double x;
    private Long y;

    public Event(Long timestamp, Double x, Long y) {
        this.timestamp = timestamp;
        this.x = x;
        this.y = y;
    }
}

