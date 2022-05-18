package com.zkurdya.chapter5homework.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r")
})
@Entity
@Table(name = "Registration")
public class Registration {
    @EmbeddedId
    private final StudentCourseId id = new StudentCourseId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @MapsId("semester")
    @Column(nullable = false, length = 50)
    private String semester;

    public Registration() {
    }

    public Registration(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public StudentCourseId getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(course, that.course) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course, semester);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                '}';
    }
}
// Zaki Kurdya, 120200706