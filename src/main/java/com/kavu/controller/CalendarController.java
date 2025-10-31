package com.kavu.controller;

import com.kavu.model.CalendarEvent;
import com.kavu.service.CalendarService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "http://localhost:5500") // match your frontend port
public class CalendarController {

    private final CalendarService service;

    public CalendarController(CalendarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CalendarEvent> getAllEvents() {
        return service.getAll();
    }

    @PostMapping
    public CalendarEvent addEvent(@RequestBody CalendarEvent event) {
        return service.add(event);
    }

    @PutMapping("/{id}")
    public CalendarEvent updateEvent(@PathVariable Long id, @RequestBody CalendarEvent updatedEvent) {
        return service.update(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.delete(id);
    }
}
