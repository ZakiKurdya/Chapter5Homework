package com.zkurdya.chapter5homework.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
        @NamedQuery(name = "Registration.deleteByStudentId", query = "DELETE FROM Registration WHERE student = :student")
})
@Entity
@Table(name = "Registration")
@IdClass(StudentCourseId.class)
public class Registration {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Id
    @Column(nullable = false, length = 50)
    private String semester;

    public Registration() {
    }

    public Registration(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, semester);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "student=" + student +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                '}';
    }
}
// Zaki Kurdya, 120200706