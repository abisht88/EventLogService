package com.log.assignment.service;

import com.log.assignment.model.Event;
import com.log.assignment.model.State;
import com.log.assignment.template.FileDataMiner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.log.assignment.model.State.FINISHED;
import static com.log.assignment.model.State.STARTED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileDataMinerTest {

    FileDataMiner template = new FileDataMiner();
    List<Event> events = new ArrayList<>();

    @Before
    public void setUp() {
        events.add(createEvent("a", FINISHED, "APPLICATION LOG", "123", 13));
        events.add(createEvent("b", STARTED, "", "123", 15));
        events.add(createEvent("a", STARTED, "APPLICATION LOG", "123", 10));
        events.add(createEvent("c", FINISHED, "APPLICATION LOG", "", 12));
        events.add(createEvent("b", FINISHED, "", "123", 20));
        events.add(createEvent("c", STARTED, "APPLICATION LOG", "", 6));
    }


    @Test
    public void willValidateTheExpectedEvents() {
        Map<String, Event> map = template.calculateEventDuration(events);

        assertThat(map.size(), is(3));

        assertEquals(map.get("a"), events.get(0));
        assertEquals(map.get("b"), events.get(1));
        assertEquals(map.get("c"), events.get(3));

        assertNotEquals(map.get("a"), events.get(1));
    }

    @Test
    public void willCalculateTheEventDuration() {
        Map<String, Event> map = template.calculateEventDuration(events);

        assertEquals(map.get("a").getDuration(), 3);
        assertEquals(map.get("b").getDuration(), 5);
        assertEquals(map.get("c").getDuration(), 6);
    }

    @Test
    public void willValidateAlertFlag() {
        Map<String, Event> map = template.calculateEventDuration(events);

        assertFalse(map.get("a").isAlert());
        assertTrue(map.get("b").isAlert());
        assertTrue(map.get("c").isAlert());
    }

    Event createEvent(String id, State state, String type, String host, long timeStamp) {
        Event event = new Event();
        event.setId(id);
        event.setState(state);
        event.setType(type);
        event.setHost(host);
        event.setTimestamp(timeStamp);
        return event;
    }

}