package com.zkurdya.chapter5homework.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
        @NamedQuery(name = "Student.updateStudent", query = "UPDATE Student SET id = :id, name = :name, major = :major, grade = :grade WHERE id = :id"),
        @NamedQuery(name = "Student.findStudentById", query = "SELECT s FROM Student s WHERE s.id = :id"),
        @NamedQuery(name = "Student.deleteStudentById", query = "DELETE FROM Student WHERE id = :id")
})
@Entity
@Table(name = "Student")
public class Student {
    @Id
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 150)
    private String major;
    @Column(nullable = false)
    private double grade;

    public Student() {
    }

    public Student(Integer id,String name, String college, double gpa) {
        this.id = id;
        this.name = name;
        this.major = college;
        this.grade = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(student.grade, grade) == 0 && Objects.equals(name, student.name) && Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, major, grade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                '}';
    }
}
// Zaki Kurdya, 120200706