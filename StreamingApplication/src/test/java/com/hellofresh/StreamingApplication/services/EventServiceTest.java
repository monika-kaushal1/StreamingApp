package com.hellofresh.StreamingApplication.services;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.repositories.EventRepository;
import com.hellofresh.StreamingApplication.services.Impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    public void addEvent() {
        Mockito.doNothing().when(eventRepository).addEvent(Mockito.any(Event.class), Mockito.anyLong());
        eventService.addEvent(System.currentTimeMillis() + ","+ 0.11 + "," + 11L);
        Mockito.verify(eventRepository, Mockito.times(1))
                .addEvent(Mockito.any(Event.class), Mockito.anyLong());
    }
}
