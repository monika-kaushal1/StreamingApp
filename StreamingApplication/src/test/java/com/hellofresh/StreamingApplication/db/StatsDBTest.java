package com.hellofresh.StreamingApplication.db;

import com.hellofresh.StreamingApplication.dbs.StatsDB;
import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.utils.MapperUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StatsDBTest {

    private StatsDB statsDB;
    @BeforeEach
    public void setup() {
        statsDB = StatsDB.getInstance();
    }

    @Test
    public void getInstanceTest() {
        StatsDB instance = StatsDB.getInstance();
        Assertions.assertThat(instance).isEqualTo(statsDB);
    }

    @Test
    public void addEventTestWithDeletion() {
        statsDB.reset();
        statsDB.addEvent(getEvent("1607341341814,0.0442672968,1282509067"), 1607341341);
        statsDB.addEvent(getEvent("1607341341814,0.0442672968,1282509067"), 1607341341);

        Map<Long, Stats> statsMap = statsDB.getStatsMap();
        Assertions.assertThat(statsMap.get(1607341341L).getTotal()).isEqualTo(2);

        // Test for sliding window. adding a time which is 60 sec higher than last entry.
        // This should cause deletion of old data.
        statsDB.addEvent(getEvent("1607341402814,0.0442672968,1282509067"), 1607341402);
        statsMap = statsDB.getStatsMap();
        Assertions.assertThat(statsMap.size()).isEqualTo(1);
    }

    private Event getEvent(String eventStr) {
        return MapperUtil.mapStringToEventObj(eventStr);
    }
}
