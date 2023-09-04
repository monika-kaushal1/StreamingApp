package com.hellofresh.StreamingApplication.controllers;

import com.hellofresh.StreamingApplication.exceptions.InternalServerException;
import com.hellofresh.StreamingApplication.exceptions.ValidationException;
import com.hellofresh.StreamingApplication.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody String event) {
        try {
            eventService.addEvent(event);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatusCode.valueOf(e.getErrorCode()));
        } catch (InternalServerException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatusCode.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
