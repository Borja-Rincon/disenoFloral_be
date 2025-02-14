package com.disenofloral.backend.controller;

import com.disenofloral.backend.model.EventLocation;
import com.disenofloral.backend.service.EventLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-locations")
@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes solo desde este origen
public class EventLocationController {

    private final EventLocationService eventLocationService;

    @Autowired
    public EventLocationController(EventLocationService eventLocationService) {
        this.eventLocationService = eventLocationService;
    }

    @GetMapping
    public List<EventLocation> getAllEventLocations() {
        return eventLocationService.getAllEventLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventLocation> getEventLocationById(@PathVariable Long id) {
        return eventLocationService.getEventLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EventLocation createEventLocation(@RequestBody EventLocation eventLocation) {
        return eventLocationService.createEventLocation(eventLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventLocation> updateEventLocation(@PathVariable Long id, @RequestBody EventLocation eventLocationDetails) {
        EventLocation updatedEventLocation = eventLocationService.updateEventLocation(id, eventLocationDetails);
        return ResponseEntity.ok(updatedEventLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventLocation(@PathVariable Long id) {
        eventLocationService.deleteEventLocation(id);
        return ResponseEntity.noContent().build();
    }
}
