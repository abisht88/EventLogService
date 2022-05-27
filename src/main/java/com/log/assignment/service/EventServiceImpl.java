package com.log.assignment.service;

import com.log.assignment.dao.EventDao;
import com.log.assignment.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao dao;
	
	public void saveEvent(Event event) {
		dao.saveEvent(event);
	}

	public List<Event> findAllEvent() {
		return dao.findAllEvents();
	}
}
