package com.log.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    private String id;
    @Transient
    private State state;
    @Transient
    private long timestamp;
    private String type;
    private String host;
    @JsonIgnore
    private long duration;
    private boolean alert;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", duration='" + duration + '\'' +
                ", alert='" + alert + '\'' +
                '}';
    }
}
