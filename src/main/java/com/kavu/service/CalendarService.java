package com.kavu.service;

import com.kavu.model.CalendarEvent;
import com.kavu.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // Get all events
    public List<CalendarEvent> getAll() {
        return calendarRepository.findAll();
    }

    // Add new event
    public CalendarEvent add(CalendarEvent event) {
        return calendarRepository.save(event);
    }

    // Update existing event
    public CalendarEvent update(Long id, CalendarEvent updatedEvent) {
        CalendarEvent existingEvent = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDate(updatedEvent.getDate());

        return calendarRepository.save(existingEvent);
    }

    // Delete event
    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}
