package com.disenofloral.backend.service;

import com.disenofloral.backend.model.Event;
import com.disenofloral.backend.model.EventLocation;
import com.disenofloral.backend.model.Location;
import com.disenofloral.backend.repository.EventLocationRepository;
import com.disenofloral.backend.repository.EventRepository;
import com.disenofloral.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventLocationService {

    private final EventLocationRepository eventLocationRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public EventLocationService(EventLocationRepository eventLocationRepository,
                                EventRepository eventRepository,
                                LocationRepository locationRepository) {
        this.eventLocationRepository = eventLocationRepository;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    public List<EventLocation> getAllEventLocations() {
        return eventLocationRepository.findAll();
    }

    public Optional<EventLocation> getEventLocationById(Long id) {
        return eventLocationRepository.findById(id);
    }

    public EventLocation createEventLocation(EventLocation eventLocation) {
        Event event = null;
        Location location = null;

        if (eventLocation.getEventId() != null) {
            event = eventRepository.findById(eventLocation.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventLocation.getEventId()));
        }

        if (eventLocation.getLocationId() != null) {
            location = locationRepository.findById(eventLocation.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location not found with id: " + eventLocation.getLocationId()));
        }

        eventLocation.setEvent(event);
        eventLocation.setLocation(location);

        return eventLocationRepository.save(eventLocation);
    }

    public EventLocation updateEventLocation(Long id, EventLocation eventLocationDetails) {
        EventLocation eventLocation = eventLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventLocation not found with id: " + id));

        Event event = null;
        Location location = null;

        if (eventLocationDetails.getEventId() != null) {
            event = eventRepository.findById(eventLocationDetails.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventLocationDetails.getEventId()));
        }

        if (eventLocationDetails.getLocationId() != null) {
            location = locationRepository.findById(eventLocationDetails.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location not found with id: " + eventLocationDetails.getLocationId()));
        }

        eventLocation.setEvent(event);
        eventLocation.setLocation(location);
        eventLocation.setDate(eventLocationDetails.getDate());
        eventLocation.setStartTime(eventLocationDetails.getStartTime());
        eventLocation.setEndTime(eventLocationDetails.getEndTime());

        return eventLocationRepository.save(eventLocation);
    }

    public void deleteEventLocation(Long id) {
        eventLocationRepository.deleteById(id);
    }
}
