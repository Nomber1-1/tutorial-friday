package ca.mcgill.ecse321.eventregistration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.EventListDto;
import ca.mcgill.ecse321.eventregistration.dto.EventRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.EventResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.InPersonEventResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.OnlineEventResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import ca.mcgill.ecse321.eventregistration.service.EventService;

@RestController
public class EventController {
    @Autowired
    private EventService service;

    @PostMapping("/events")
    public EventResponseDto createEvent(@RequestBody EventRequestDto request) {
        return switch (request.getType()) {
            case ONLINE -> {
                OnlineEvent createdEvent = service.createOnlineEvent(request.getName(), request.getDate(),
                        request.getStartTime(), request.getEndTime(), request.getRegistrationLimit(), request.getUrl());
                yield new OnlineEventResponseDto(createdEvent);
            }
            case IN_PERSON -> {
                InPersonEvent createdEvent = service.createInPersonEvent(request.getName(), request.getDate(),
                        request.getStartTime(), request.getEndTime(), request.getRegistrationLimit(),
                        request.getAddress());
                yield new InPersonEventResponseDto(createdEvent);
            }
            default -> throw new IllegalArgumentException("Invalid event type.");
        };
    }

    @GetMapping("/events/{eid}")
    public EventResponseDto findEventById(@PathVariable int eid) {
        Event event = service.findEventById(eid);
        return toDto(event);
    }

    @GetMapping("/events")
    public EventListDto findAllEvents() {
        List<EventResponseDto> dtos = new ArrayList<EventResponseDto>();
        for (Event e : service.findAllEvents()) {
            dtos.add(toDto(e));
        }
        return new EventListDto(dtos);
    }

    private EventResponseDto toDto(Event e) {
        // Normally you'd want to have a method like toDto() on the model class so you
        // don't need to do this instanceof nonsense. But in a layered architecture, we
        // don't want a layer (in this case, the models) to depend on a higher layer (in
        // this case, the DTOs).
        if (e instanceof OnlineEvent) {
            return new OnlineEventResponseDto((OnlineEvent) e);
        } else if (e instanceof InPersonEvent) {
            return new InPersonEventResponseDto((InPersonEvent) e);
        } else {
            throw new IllegalArgumentException("Unknown event type.");
        }
    }
}
