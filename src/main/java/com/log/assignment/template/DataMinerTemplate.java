package com.log.assignment.template;

import com.log.assignment.model.Event;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log
public abstract class DataMinerTemplate {

     abstract List<Event> parseData(String path) throws IOException;
     abstract Map<String, Event> calculateEventDuration(List<Event> events);
     abstract void writeData(Map<String, Event> events);

    public final void dataMining(String path) throws IOException {
        log.info("inside dataMining");
        Map<String, Event> map = calculateEventDuration(parseData(path));
        if(!map.isEmpty()){
            writeData(map);
        }
    }
}
