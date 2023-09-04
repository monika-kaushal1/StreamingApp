package com.hellofresh.StreamingApplication.services;

import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.repositories.EventRepository;
import com.hellofresh.StreamingApplication.services.Impl.StatsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private StatsServiceImpl statsService;

    @Test
    public void getStatsTest() throws InterruptedException {
        Mockito.when(eventRepository.getStatsMap()).thenReturn(getStatsMap());
        Stats stats = statsService.getStats();
        Assertions.assertThat(stats.getTotal()).isEqualTo(2);
    }

    private Map<Long, Stats> getStatsMap() throws InterruptedException {
        Map<Long, Stats> statsMap = new ConcurrentHashMap<>();
        statsMap.put(System.currentTimeMillis()/1000, new Stats(1, 0.11, 0.11, 1, 11, 11, 1));
        Thread.sleep(1000);
        statsMap.put(System.currentTimeMillis()/1000, new Stats(1, 0.12, 0.12, 1, 12, 12, 1));
        return statsMap;
    }



}
