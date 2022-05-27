package com.log.assignment.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.log.assignment.model.Event;
import com.log.assignment.service.EventService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@Log
@Component
public class FileDataMiner extends DataMinerTemplate {

    @Autowired
    EventService eventService;

    @Override
    public List<Event> parseData(String path) throws IOException {
        return Arrays.asList(new ObjectMapper().readValue(Paths.get(path).toFile(), Event[].class));
    }

    @Override
    public Map<String, Event> calculateEventDuration(List<Event> events) {
        log.info("calculating event duration");
        Map<String, Event> map = new HashMap<>();
        events.parallelStream().forEach(e1 -> {
            Event e3 = events.parallelStream().filter(e2 -> e1.getId().equals(e2.getId()) && e1.getState() != e2.getState()).findFirst().orElse(null);
            if(e3 != null && map.get(e3.getId()) == null){
                long duration = abs(e1.getTimestamp() - e3.getTimestamp());
                e1.setDuration(duration);
                e1.setAlert(duration > 4);
                map.put(e1.getId(), e1);
            }
        });
        return map;
    }

    @Override
    public void writeData(Map<String, Event> events) {
        events.values().forEach(event -> eventService.saveEvent(event));
    }
}
