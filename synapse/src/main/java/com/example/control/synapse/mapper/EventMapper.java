package com.example.control.synapse.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.example.control.synapse.dto.response.EventPageResponseDto;
import com.example.control.synapse.dto.response.EventResponseDto;
import com.example.control.synapse.models.Event;

public class EventMapper {
    public static EventResponseDto toDto(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .name(event.getName())
                .datetime(event.getDateTime())
                .category(event.getCategory())
                .description(event.getDescription())
                .minPrice(event.getMinPrice()) // Ensure Event entity has this field!
                
                // Handle Null Safety for Stadium
                .stadiumName(event.getStadium() != null ? event.getStadium().getName() : "Unknown Stadium")
                .city(event.getStadium() != null ? event.getStadium().getCity() : "Unknown City")
                .build();
    }

    // 2. Convert Page<Entity> -> PageResponseDto
    public static EventPageResponseDto toPageResponseDto(Page<Event> eventPage) {
        // Convert the list of Entities to a list of DTOs
        List<EventResponseDto> dtos = eventPage.getContent().stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());

        return EventPageResponseDto.builder()
                .content(dtos)
                .pageNo(eventPage.getNumber())
                .pageSize(eventPage.getSize())
                .totalElements(eventPage.getTotalElements())
                .totalPages(eventPage.getTotalPages())
                .last(eventPage.isLast())
                .build();
    }
}
