package org.launchcode.Spring.REST.API.Controllers;

import org.launchcode.Spring.REST.API.Models.Event;
import org.launchcode.Spring.REST.API.Models.EventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//finished studio


@RestController
@RequestMapping(value = "/events")
public class EventController {

    @GetMapping
    public List<Event> getEvents() {
        return Event.findAllItems();
    }
    @GetMapping(value = "/{id}")
    public Event getEventById(@PathVariable int id) {
        return Event.findItem(id);
    }
    @PostMapping
    public Event postEvent(@RequestBody EventDto eventDto) {
        return Event.createEvent(eventDto.getName(), eventDto.getDescription());
    }
    @PatchMapping(value = "/{id}")
    public Event patchEvent(@PathVariable int id, @RequestBody EventDto eventDto) {
        Event theEvent = Event.findItem(id);
        if (theEvent != null) {
            if (eventDto.getName() != null) {
                theEvent.setName(eventDto.getName());
            }
            if (eventDto.getDescription() != null) {
                theEvent.setDescription(eventDto.getDescription());
            }
        }
        return theEvent;
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id) {
        boolean deleted = Event.deleteItem(id);
        if (deleted) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
