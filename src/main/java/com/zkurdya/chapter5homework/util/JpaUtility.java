package com.zkurdya.chapter5homework.util;

import com.zkurdya.chapter5homework.model.Course;
import com.zkurdya.chapter5homework.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaUtility {
    private final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Students");

    public void save(Object object) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<?> getAllStudents() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<?> studentList = new ArrayList<>();
        try {
            studentList = entityManager.createNamedQuery("Student.findAll").getResultList();
        } catch (NoResultException ignored) {}
        entityManager.getTransaction().commit();
        entityManager.close();
        return studentList.stream().toList();
    }

    public void deleteStudentByID(Integer id) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("Student.deleteStudentById")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Student getStudentById(Integer id) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = new Student();
        try {
            student = (Student) entityManager.createNamedQuery("Student.findStudentById").setParameter("id", id).getSingleResult();
        } catch (NoResultException ignored){}
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }

    public void updateStudent(Student student) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("Student.updateStudent")
                .setParameter("id", student.getId())
                .setParameter("name", student.getName())
                .setParameter("major", student.getMajor())
                .setParameter("grade", student.getGrade())
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<?> getAllCourses() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<?> courses = new ArrayList<>();
        try {
            courses = entityManager.createNamedQuery("Course.findAll").getResultList();
        } catch (NoResultException ignored) {}
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses.stream().toList();
    }

    public List<?> getCourses() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<?> courses = new ArrayList<>();
        try {
            courses = entityManager.createNamedQuery("Course.findCourseIds").getResultList();
        } catch (NoResultException ignored) {}
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses.stream().toList();
    }

    public Course getCourseById(String id) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = (Course) entityManager.createNamedQuery("Course.findCourseById").setParameter("id", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return course;
    }

    public void deleteByStudentId(Student student) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("Registration.deleteByStudentId")
                .setParameter("student", student)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
// Zaki Kurdya, 120200706