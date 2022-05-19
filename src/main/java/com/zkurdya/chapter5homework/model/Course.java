package com.zkurdya.chapter5homework.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "Course.findCourseById", query = "SELECT c FROM Course c WHERE c.id = :id"),
        @NamedQuery(name = "Course.findCourseIds", query = "SELECT c.id FROM Course c")
})
@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(nullable = false, length = 10)
    private String id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(length = 5)
    private String room;

    public Course() {
    }

    public Course(String id, String name, String room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(room, course.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, room);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
// Zaki Kurdya, 120200706