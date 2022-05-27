package com.log.assignment.service;

import com.log.assignment.model.Event;

import java.util.List;

public interface EventService {

	void saveEvent(Event event);

	List<Event> findAllEvent();
}
