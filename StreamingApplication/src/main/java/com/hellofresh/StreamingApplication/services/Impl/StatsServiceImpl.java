package com.hellofresh.StreamingApplication.services.Impl;

import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.services.StatsService;
import com.hellofresh.StreamingApplication.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Class to provide Stats calculations.
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Computes stats from the window StatsMap.
     * As StatsMap contains only last 60 seconds slots,
     * we just need to calculate StatsMap and return in o(1).
     *
     * @return Stats
     */
    @Override
    public Stats getStats() {
        Map<Long, Stats> statsMap = eventRepository.getStatsMap();
        final Stats resultStats = new Stats();
        long currentTimeInSec = System.currentTimeMillis() / 1000;

        // Computing slots for past 60 secs.
        for (long t = currentTimeInSec; t >= currentTimeInSec - 60; t--) {
            if (statsMap.get(t) != null) {
                resultStats.addStats(statsMap.get(t));
            }
        }

        return resultStats;
    }
}
