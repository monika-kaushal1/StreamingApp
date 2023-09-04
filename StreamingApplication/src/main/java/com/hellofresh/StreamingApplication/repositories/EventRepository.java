package com.hellofresh.StreamingApplication.repositories;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;

import java.util.Map;

public interface EventRepository {

    void addEvent(Event event, long timestamp);

    Map<Long, Stats> getStatsMap();

}
