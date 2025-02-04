package com.disenofloral.backend.service;

import com.disenofloral.backend.model.EventLocation;
import com.disenofloral.backend.repository.EventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventLocationService {

    private final EventLocationRepository eventLocationRepository;

    @Autowired
    public EventLocationService(EventLocationRepository eventLocationRepository) {
        this.eventLocationRepository = eventLocationRepository;
    }

    public List<EventLocation> getAllEventLocations() {
        return eventLocationRepository.findAll();
    }

    public Optional<EventLocation> getEventLocationById(Long id) {
        return eventLocationRepository.findById(id);
    }

    public EventLocation createEventLocation(EventLocation eventLocation) {
        return eventLocationRepository.save(eventLocation);
    }

    public EventLocation updateEventLocation(Long id, EventLocation eventLocationDetails) {
        EventLocation eventLocation = eventLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventLocation not found with id: " + id));

        eventLocation.setEvent(eventLocationDetails.getEvent());
        eventLocation.setLocation(eventLocationDetails.getLocation());
        eventLocation.setDate(eventLocationDetails.getDate());
        eventLocation.setTime(eventLocationDetails.getTime());

        return eventLocationRepository.save(eventLocation);
    }

    public void deleteEventLocation(Long id) {
        eventLocationRepository.deleteById(id);
    }
}
