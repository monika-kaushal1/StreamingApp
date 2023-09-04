package com.hellofresh.StreamingApplication.dbs;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton class of DB to make sure only one instance of DB in the system.
 */
public class StatsDB {

    private static StatsDB statsDB;

    private Map<Long, Stats> statsMap;

    private StatsDB() {
        this.statsMap = new ConcurrentHashMap<>(60);
    }

    /**
     * Get an instance of DB
     *
     * @return StatsDB
     */
    public static synchronized StatsDB getInstance() {
        if (statsDB == null) {
            statsDB = new StatsDB();
        }

        return statsDB;
    }

    /**
     * function to store Stats per events using Seconds as slot.
     * This will hold only last 60 buckets. This rolling window
     * is updated if a new entry comes.
     *
     * @param event
     * @param timestamp
     */
    public synchronized void addEvent(Event event, long timestamp) {
        if (statsMap.get(timestamp) != null) {
            Stats stats = statsMap.get(timestamp);
            stats.addEvent(event);
        } else {
            Stats stats = new Stats();
            stats.addEvent(event);
            statsMap.put(timestamp, stats);
            // free unwanted slots.
            fixupMap(timestamp);
        }
    }

    /**
     * This function will reset the db. This will be used in testing.
     */
    public synchronized void reset() {
        this.statsMap = new ConcurrentHashMap<>(60);
    }

    private void fixupMap(long timestamp) {
        //removing all slots older than 60 sec to save the map from growing forever.
        Map<Long, Stats> tempStatsMap = new ConcurrentHashMap<>();
        // filling a temp Map of past 60 secs slots.
        for (long t = timestamp; t > timestamp - 60; t--) {
            statsMap.computeIfPresent(t, tempStatsMap::put);
        }
        // pointing to map of past 60 secs data.
        statsMap = tempStatsMap;
    }

    /**
     * Returns new object of StatsMap to avoid race conditions and
     * Computation while modification.
     *
     * @return StatsMap
     */
    public synchronized Map<Long, Stats> getStatsMap() {
        return new HashMap<>(statsMap);
    }
}
