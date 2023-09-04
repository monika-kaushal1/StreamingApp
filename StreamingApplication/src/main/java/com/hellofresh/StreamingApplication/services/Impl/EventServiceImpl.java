package com.hellofresh.StreamingApplication.services.Impl;

import com.hellofresh.StreamingApplication.domains.Event;
import com.hellofresh.StreamingApplication.services.EventService;
import com.hellofresh.StreamingApplication.exceptions.ErrorCodes;
import com.hellofresh.StreamingApplication.exceptions.InternalServerException;
import com.hellofresh.StreamingApplication.repositories.EventRepository;
import com.hellofresh.StreamingApplication.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles CRUD operation related to Event.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    /**
     * This function adds Event to DB.
     *
     * @param event
     */
    @Override
    public void addEvent(String event) {
        Event eventObj = MapperUtil.mapStringToEventObj(event);
        try {
            eventRepository.addEvent(eventObj, getSecondsTimestamp(eventObj.getTimestamp()));
        } catch (Exception e) {
            throw new InternalServerException(ErrorCodes.INTERNAL_SERVER_ERROR, e);
        }
    }

    /**
     * Function to convert timestamp from milli to second.
     *
     * @param timestamp
     * @return long
     */
    private long getSecondsTimestamp(long timestamp) {
        return timestamp / 1000;
    }
}

