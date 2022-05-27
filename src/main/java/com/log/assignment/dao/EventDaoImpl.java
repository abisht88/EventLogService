package com.log.assignment.dao;

import com.log.assignment.model.Event;
import lombok.extern.java.Log;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log
@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	SessionFactory sessionFactory;

	public void saveEvent(Event event) {
		sessionFactory.getCurrentSession().save(event);
		log.info(event.getId() +" is persisted");
	}

	public List<Event> findAllEvents() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class);
		return (List<Event>) criteria.list();
	}
}
