package com.log.assignment.dao;

import com.log.assignment.model.Event;

import java.util.List;

public interface EventDao {

	void saveEvent(Event event);
	
	List<Event> findAllEvents();
}
