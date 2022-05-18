package com.zkurdya.chapter5homework.controller;

import com.zkurdya.chapter5homework.model.Student;
import com.zkurdya.chapter5homework.util.JpaUtil;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class MainScreenController implements Initializable {
    @FXML
    private TableView<Object> tableView = new TableView<>();

    @FXML
    private TextField id, name, major, grade;
    @FXML
    private TextField editSearch, idE, nameE, majorE, gradeE;
    @FXML
    private TextField removeSearch, removedId;
    @FXML
    private TextField registerSearch, studentId;

    @FXML
    private Button addButton, editButton, editSearchButton, removeButton, removeSearchButton,
                   registerButton, registerSearchButton, showCourses;

    @FXML
    private ComboBox<String> courseId, semester;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab addTab, editTab, removeTab, courseRegistrationTab;

    private final JpaUtil jpaUtil = new JpaUtil();

    @FXML
    private void addNewStudent() {
        if (!id.getText().equals("") && !name.getText().equals("") &&
            !major.getText().equals("") && !grade.getText().equals("")) {
            Student newStudent = new Student();
            newStudent.setId(Integer.parseInt(id.getText()));
            newStudent.setName(name.getText());
            newStudent.setMajor(major.getText());
            try {
                newStudent.setGrade(Double.parseDouble(grade.getText()));
            } catch (NumberFormatException formatException) {
                id.setText("");
                name.setText("");
                major.setText("");
                grade.setText("");
                return;
            }
            jpaUtil.save(newStudent);
            tableView.getItems().clear();
            tableView.getItems().addAll(jpaUtil.getAllStudents());
        }
        id.setText("");
        name.setText("");
        major.setText("");
        grade.setText("");
    }

    @FXML
    private void editSearch() {
        if (!editSearch.getText().equals("")) {
            tableView.getItems().clear();
            try {
                tableView.getItems().add(jpaUtil.getStudentById(Integer.parseInt(editSearch.getText())));
            } catch (NumberFormatException ignored) {}
        }
    }

    @FXML
    private void updateStudentData() {
        if (!idE.getText().equals("") && !nameE.getText().equals("") &&
            !majorE.getText().equals("") && !gradeE.getText().equals("")) {
            Student newStudent = new Student();
            try {
                newStudent.setId(Integer.parseInt(idE.getText()));
            } catch (NumberFormatException formatException) {
                idE.setText("");
                nameE.setText("");
                majorE.setText("");
                gradeE.setText("");
                return;
            }
            newStudent.setName(nameE.getText());
            newStudent.setMajor(majorE.getText());
            try {
                newStudent.setGrade(Double.parseDouble(gradeE.getText()));
            } catch (NumberFormatException formatException) {
                idE.setText("");
                nameE.setText("");
                majorE.setText("");
                gradeE.setText("");
                return;
            }
            jpaUtil.update(newStudent);
            tableView.getItems().clear();
            tableView.getItems().addAll(jpaUtil.getAllStudents());
        }
        idE.setText("");
        nameE.setText("");
        majorE.setText("");
        gradeE.setText("");
    }

    @FXML
    private void removeSearch() {
        if (!removeSearch.getText().equals("")) {
            tableView.getItems().clear();
            try {
                tableView.getItems().addAll(jpaUtil.getStudentById(Integer.parseInt(removeSearch.getText())));
            } catch (NumberFormatException ignored) {}
        }
    }

    @FXML
    private void deleteStudentById() {
        if (!removedId.getText().equals("")) {
            try {
                jpaUtil.deleteStudentByID(Integer.parseInt(removedId.getText()));
                tableView.getItems().clear();
                tableView.getItems().addAll(jpaUtil.getAllStudents());
            } catch (NumberFormatException ignored) {}
        }
    }

    @FXML
    private void registerSearch() {
        if (!registerSearch.getText().equals("")) {
            getStudentTableSchema();
            tableView.getItems().clear();
            try {
                tableView.getItems().addAll(jpaUtil.getStudentById(Integer.parseInt(registerSearch.getText())));
            } catch (NumberFormatException ignored) {}
        }
    }

    @FXML
    private void register() {
        /*if (!studentId.getText().equals("") &&
            courseId.getSelectionModel().getSelectedItem() != null &&
            semester.getSelectionModel().getSelectedItem() != null) {
            String registration = studentId.getText() + "#@" +
                                  courseId.getSelectionModel().getSelectedItem() + "#@" +
                                  semester.getSelectionModel().getSelectedItem();
            DatabaseConnection.registerCourse(registration);
        }
        studentId.setText("");
        courseId.getSelectionModel().clearSelection();
        semester.getSelectionModel().clearSelection();*/
    }

    @FXML
    private void showAllCourses() {
        TableColumn<Object, String> id = new TableColumn<>("Course Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Object, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Object, String> room = new TableColumn<>("Room");
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        tableView.getColumns().clear();
        tableView.getColumns().addAll(id, name, room);
        tableView.getItems().clear();
        tableView.getItems().addAll(jpaUtil.getAllCourses());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getStudentTableSchema();
       // tableView.getItems().addAll(jpaUtil.getAllStudents());

        semester.getItems().addAll("First semester 2022/2023", "Second semester 2022/2023");

        InvalidationListener listener = observable -> {
            Student student = (Student) tableView.getSelectionModel().getSelectedItem();
            if (student != null) {
                idE.setText(String.valueOf(student.getId()));
                nameE.setText(student.getName());
                majorE.setText(student.getMajor());
                gradeE.setText(String.valueOf(student.getGrade()));
            }
        };

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldTab, newTab) -> {
                    if (newTab == editTab)
                        tableView.getSelectionModel().selectedItemProperty().addListener(listener);
                    else
                        tableView.getSelectionModel().selectedItemProperty().removeListener(listener);

                    getStudentTableSchema();

                    if (newTab == addTab || newTab == removeTab || newTab == editTab) {
                        tableView.getItems().clear();
          //              tableView.getItems().addAll(jpaUtil.getAllStudents());
                    } else if (newTab == courseRegistrationTab) {
                        tableView.getItems().clear();
              //          tableView.getItems().addAll(jpaUtil.getAllStudents());
                        courseId.getItems().clear();
            //            courseId.getItems().addAll(jpaUtil.getCourses());
                    }
                }
        );
    }

    private void getStudentTableSchema() {
        TableColumn<Object, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Object, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Object, String> major = new TableColumn<>("Major");
        major.setCellValueFactory(new PropertyValueFactory<>("major"));
        TableColumn<Object, Double> grade = new TableColumn<>("Grade");
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tableView.getColumns().clear();
        tableView.getColumns().addAll(id, name, major, grade);
    }
}
// Zaki Kurdya, 120200706