package com.zkurdya.chapter5homework.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class StudentCourseId implements Serializable {
    @Column(name = "student_id")
    private Integer student;
    @Column(name = "course_id")
    private String course;
    private String semester;

    public StudentCourseId() {
    }

    public StudentCourseId(Integer student, String course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseId that = (StudentCourseId) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, semester);
    }
}
// Zaki Kurdya, 120200706