package com.zkurdya.chapter5homework.util;

import com.zkurdya.chapter5homework.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JpaUtil {
    private final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Students");

    public void save(Object object) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Object> getAllStudents() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Object> studentList = new ArrayList<>();
        try {
            studentList = entityManager.createNamedQuery("Student.findAll").getResultList();
        } catch (NoResultException resultException) {

        }
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
        Student student = (Student) entityManager.createNamedQuery("Student.findStudentById").setParameter("id", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }

    public void update(Student student) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery("Student.updateStudent")
                .setParameter("sid", student.getId())
                .setParameter("sname", student.getName())
                .setParameter("smajor", student.getMajor())
                .setParameter("sgrade", student.getGrade())
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Object> getAllCourses() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Object> studentList = new ArrayList<>();
        try {
            studentList = entityManager.createNamedQuery("Course.findAll").getResultList();
        } catch (NoResultException resultException) {

        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return studentList.stream().toList();
    }

    public List<String> getCourses() {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<String> courses = new ArrayList<>();
        try {
            courses = entityManager.createNamedQuery("Course.findAllIds").getResultList();
        } catch (NoResultException resultException) {
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses.stream().toList();
    }

}