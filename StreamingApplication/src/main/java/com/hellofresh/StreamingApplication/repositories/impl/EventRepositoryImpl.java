package com.hellofresh.StreamingApplication.repositories.impl;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.dbs.StatsDB;
import com.hellofresh.StreamingApplication.repositories.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final StatsDB statsDB = StatsDB.getInstance();

    /**
     * Adds events to DB
     *
     * @param event
     * @param timestamp
     */
    @Override
    public void addEvent(Event event, long timestamp) {
        statsDB.addEvent(event, timestamp);
    }

    /**
     * Fetches all stats from DB
     *
     * @return
     */
    @Override
    public Map<Long, Stats> getStatsMap() {
        return statsDB.getStatsMap();
    }


}
