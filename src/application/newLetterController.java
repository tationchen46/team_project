package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class newLetterController implements Initializable {
	/*
	 * Observable MenuItem lists for courses, personal characteristics, and
	 * academics
	 */

	ObservableList<MenuItem> observableCourseList = FXCollections.observableArrayList();
	ObservableList<MenuItem> observablePersonalList = FXCollections.observableArrayList();
	ObservableList<MenuItem> observableAcademicList = FXCollections.observableArrayList();

	private Stage stage;
	private Scene scene;
	private AnchorPane root;

	/* Data entry form information */

	@FXML
	ChoiceBox<String> gender;

	public String[] genderList = { "male", "female" };

	@FXML
	ChoiceBox<String> program;

	@FXML
	ChoiceBox<String> semester;

	@FXML
	MenuButton courses;

	@FXML
	MenuButton personal;

	@FXML
	MenuButton academic;

	@FXML
	TextField first;

	@FXML
	TextField last;

	@FXML
	TextField school;

	@FXML
	TextField year;

	@FXML
	TextField grade;
	
//	//updated information
//	@FXML 
//	TextField updatedFirst;
//	
//	@FXML 
//	Button updatedSubmit;
//	
//	@FXML 
//	TextField updatedLast;
//	
//	@FXML 
//	TextField updatedSchool;
//	
//	@FXML 
//	TextField updatedGrade;
//	
//	@FXML 
//	ChoiceBox<String> updatedGender;
//	
//	@FXML 
//	DatePicker updatedDate;
//	
//	@FXML 
//	ChoiceBox<String> updatedProgram;
//	
//	@FXML 
//	ChoiceBox<String> updatedSemester;
//	
//	@FXML 
//	TextField updatedYear;
//	
//	@FXML 
//	MenuButton updatedCourses;
//	
//	@FXML 
//	MenuButton updatedPersonal;
//	
//	@FXML 
//	MenuButton updatedAcademic;
	
	/* Implemented initialize function to initialize ChoiceBox and MenuButton */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gender.getItems().setAll(genderList);
		SQLite app = new SQLite();
		try {
			ArrayList<String> al = app.getprogram();
			ArrayList<String> al1 = app.getsemster();
			ArrayList<String> courseList = app.getcourse();
			int i = 0;
			/*
			 * looping through the multiple lists from DB, then passing those Strings into
			 * CustomMenuItem, then adding them into MenuItem courseList
			 */
			for (String course : courseList) {
				MenuItem menuItemCourses = new CheckMenuItem(course);
				observableCourseList.add(menuItemCourses);
				// courses.getItems().add(menuItemCourses);

			}

			ArrayList<String> personalList = app.getpersonal();
			for (String personals : personalList) {
				MenuItem menuItemPersonal = new CheckMenuItem(personals);
				observablePersonalList.add(menuItemPersonal);
			}

			ArrayList<String> academicList = app.getcharacteristics();
			for (String academics : academicList) {
				MenuItem menuItemAcademic = new CheckMenuItem(academics);
				observableAcademicList.add(menuItemAcademic);
			}

			program.getItems().setAll(al);
			semester.getItems().setAll(al1);
			courses.getItems().setAll(observableCourseList);
			personal.getItems().setAll(observablePersonalList);
			academic.getItems().setAll(observableAcademicList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* Function to switch back to the main menu */

	public void backToMainMenu(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToComplie(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Compile.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void getInformation(ActionEvent event) throws IOException {

		List<String> selectedCourses = new ArrayList<>();
		List<String> selectedpersonal = new ArrayList<>();
		List<String> selectedacademic = new ArrayList<>();
		String gender_name = gender.getValue();
		String program_name = program.getValue();
		String semester_name = semester.getValue();
		String first_name = first.getText();
		String last_name = last.getText();
		String school_name = school.getText();
		String year_get = year.getText();
		String grade_get = grade.getText();

		for (MenuItem menuItem : observableCourseList) {
			if (menuItem instanceof CheckMenuItem) {
				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
				if (checkMenuItem.isSelected()) {
					selectedCourses.add(checkMenuItem.getText());
				}
			}
		}
		System.out.println();
		for (MenuItem menuItem : observablePersonalList) {
			if (menuItem instanceof CheckMenuItem) {
				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
				if (checkMenuItem.isSelected()) {
					selectedpersonal.add(checkMenuItem.getText());
				}
			}
		}
		System.out.println();
		for (MenuItem menuItem : observableAcademicList) {
			if (menuItem instanceof CheckMenuItem) {
				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
				if (checkMenuItem.isSelected()) {
					selectedacademic.add(checkMenuItem.getText());
				}
			}
		}
		System.out.println();
		String courseCombine = "";
		String personalCombine = "";
		String academicCombine = "";
		for (String i : selectedCourses) {
			courseCombine = courseCombine + i + ",";
		}
		for (String j : selectedpersonal) {
			personalCombine = personalCombine + j + ",";
		}
		for (String k : selectedacademic) {
			academicCombine = academicCombine + k + ",";
		}
		// Create a new LocalDate object with the current date
		LocalDate currentDate = LocalDate.now();
		SQLite app = new SQLite();
		app.insertInformation(first_name, last_name, gender_name, school_name, currentDate.toString(), program_name,
				semester_name + year_get, courseCombine, grade_get, personalCombine, academicCombine);

	}
}
	
	
	
	
	
	
	
	
	
	
	
	
//	public void getEditedInformation(ActionEvent event) throws IOException {
//
//		List<String> selectedCourses = new ArrayList<>();
//		List<String> selectedpersonal = new ArrayList<>();
//		List<String> selectedacademic = new ArrayList<>();
//		String gender_name = gender.getValue();
//		String program_name = program.getValue();
//		String semester_name = semester.getValue();
//		String first_name = first.getText();
//		String last_name = last.getText();
//		String school_name = school.getText();
//		String year_get = year.getText();
//		String grade_get = grade.getText();
//
//		for (MenuItem menuItem : observableCourseList) {
//			if (menuItem instanceof CheckMenuItem) {
//				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
//				if (checkMenuItem.isSelected()) {
//					selectedCourses.add(checkMenuItem.getText());
//				}
//			}
//		}
//		System.out.println();
//		for (MenuItem menuItem : observablePersonalList) {
//			if (menuItem instanceof CheckMenuItem) {
//				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
//				if (checkMenuItem.isSelected()) {
//					selectedpersonal.add(checkMenuItem.getText());
//				}
//			}
//		}
//		System.out.println();
//		for (MenuItem menuItem : observableAcademicList) {
//			if (menuItem instanceof CheckMenuItem) {
//				CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
//				if (checkMenuItem.isSelected()) {
//					selectedacademic.add(checkMenuItem.getText());
//				}
//			}
//		}
//		System.out.println();
//		String courseCombine = "";
//		String personalCombine = "";
//		String academicCombine = "";
//		for (String i : selectedCourses) {
//			courseCombine = courseCombine + i + ",";
//		}
//		for (String j : selectedpersonal) {
//			personalCombine = personalCombine + j + ",";
//		}
//		for (String k : selectedacademic) {
//			academicCombine = academicCombine + k + ",";
//		}
//		// Create a new LocalDate object with the current date
//		LocalDate currentDate = LocalDate.now();
//		SQLite app = new SQLite();
//		app.insertInformation(first_name, last_name, gender_name, school_name, currentDate.toString(), program_name,
//				semester_name + year_get, courseCombine, grade_get, personalCombine, academicCombine);
//
//	}
//
//}
