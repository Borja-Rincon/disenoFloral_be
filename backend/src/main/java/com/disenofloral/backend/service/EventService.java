package com.disenofloral.backend.service;

import com.disenofloral.backend.model.Event;
import com.disenofloral.backend.model.User;
import com.disenofloral.backend.repository.EventRepository;
import com.disenofloral.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        if (event.getUserId() != null) {
            User user = userRepository.findById(event.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + event.getUserId()));
            event.setUser(user);
        }
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());

        if (eventDetails.getUserId() != null) {
            User user = userRepository.findById(eventDetails.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + eventDetails.getUserId()));
            event.setUser(user);
        }

        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}