package application;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class ComplieControler {
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	@FXML
	private TextArea draftshow;
	@FXML
	TextField lastname;
	@FXML
	private TextArea lettershow;
	String letter;
	@FXML
	private Label check;

	public void backTonewletterpage(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("createnewLetter.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void backToMainpage(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void gotoeditold(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("searchdraft.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void printletter(ActionEvent event) throws IOException, SQLException {
		SQLite sql = new SQLite();
		ArrayList<String> information = sql.getInformation(null);
		String[] courselist = information.get(7).split(",");
		String[] gradeist = information.get(8).split(",");
		ArrayList<String> faculty = sql.getfaculty();
		File file = new File("lib/template.pdf");
		PDDocument document = Loader.loadPDF(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		text = text.replaceAll("<Student's Full Name>", information.get(0) + " " + information.get(1));
		text = text.replaceAll("<Today's Date>", information.get(4));
		text = text.replaceAll("<program name>", information.get(5));
		text = text.replaceAll("<Student's First Name>", information.get(0));
		text = text.replaceAll("<First Semester>", information.get(6));
		text = text.replaceAll("<First Course Taken>", courselist[0]);
		text = text.replace("<letter grade>", gradeist[0]);
		if (information.get(2) == "male") {
			text = text.replaceAll("<He/She>", "He");
			text = text.replaceAll("<he/she>", "he");
		}

		else {
			text = text.replaceAll("<He/She>", "She");
			text = text.replaceAll("<he/she>", "she");
		}

		text = text.replaceAll("<Coma separated Academic Characteristics>", information.get(10));
		text = text.replaceAll("<Coma separated Personal Characteristics>", information.get(9));

		text = text.replaceAll("<Professor's Full Name>", faculty.get(0));
		text = text.replaceAll("<Professor's title>", faculty.get(1));
		text = text.replaceAll("<School's name, department's name>", faculty.get(2) + "," + faculty.get(3));
		text = text.replaceAll("<Professor's email address>", faculty.get(4));
		text = text.replaceAll("<Professor's phone number>", faculty.get(5));
		if (courselist.length > 1) {
			for (int i = 1; i < courselist.length; i++) {
				text = text.replace("<course name>", courselist[i]);
			}
		}
		if (gradeist.length > 1) {
			for (int i = 1; i < courselist.length; i++) {
				text = text.replace("<letter grade>", gradeist[i]);
			}
		}
		draftshow.setText(text);
//		System.out.println(information.get(0));
		// System.out.println(text);
		document.close();
		String letter1 = draftshow.getText();
		getletter(letter1);

	}

	public void getletter(String letter1) {
		letter = letter1;
	}

	public String setletter() {
		return letter;
	}

	@FXML
	private void handleDeleteButtonAction(ActionEvent event) {
		lettershow.setText(""); // set text area content to an empty string
		printletter2(event);

	}

	public void printletter1(ActionEvent event) {
		String content = setletter();
		String filePath = "lib/output.txt";

		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(content);
			writer.close();
			System.out.println("String successfully exported to file: " + filePath);
		} catch (IOException e) {
			System.out.println("An error occurred while exporting the string to file.");
			e.printStackTrace();
		}
	}

	public void printletter2(ActionEvent event) {
		String content = "";
		String filePath = "lib/output.txt";

		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(content);
			writer.close();
			System.out.println("String successfully exported to file: " + filePath);
		} catch (IOException e) {
			System.out.println("An error occurred while exporting the string to file.");
			e.printStackTrace();
		}

	}

	public void showdraftwithlast(ActionEvent event) throws IOException, SQLException {
		String last = lastname.getText();
		System.out.print(last);
		SQLite sql = new SQLite();
		ArrayList<String> information = sql.getInformation(last);
		if (information.size()==0) {
			check.setText("invalid last name");
		} else {
			String[] courselist = information.get(7).split(",");
			String[] gradeist = information.get(8).split(",");
			ArrayList<String> faculty = sql.getfaculty();
			File file = new File("lib/template.pdf");
			PDDocument document = Loader.loadPDF(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			text = text.replaceAll("<Student's Full Name>", information.get(0) + " " + information.get(1));
			text = text.replaceAll("<Today's Date>", information.get(4));
			text = text.replaceAll("<program name>", information.get(5));
			text = text.replaceAll("<Student's First Name>", information.get(0));
			text = text.replaceAll("<First Semester>", information.get(6));
			text = text.replaceAll("<First Course Taken>", courselist[0]);
			text = text.replace("<letter grade>", gradeist[0]);
			if (information.get(2) == "male") {
				text = text.replaceAll("<He/She>", "He");
				text = text.replaceAll("<he/she>", "he");
			}

			else {
				text = text.replaceAll("<He/She>", "She");
				text = text.replaceAll("<he/she>", "she");
			}

			text = text.replaceAll("<Coma separated Academic Characteristics>", information.get(10));
			text = text.replaceAll("<Coma separated Personal Characteristics>", information.get(9));

			text = text.replaceAll("<Professor's Full Name>", faculty.get(0));
			text = text.replaceAll("<Professor's title>", faculty.get(1));
			text = text.replaceAll("<School's name, department's name>", faculty.get(2) + "," + faculty.get(3));
			text = text.replaceAll("<Professor's email address>", faculty.get(4));
			text = text.replaceAll("<Professor's phone number>", faculty.get(5));

			if (courselist.length > 1) {
				for (int i = 1; i < courselist.length; i++) {
					text = text.replace("<course name>", courselist[i]);
				}
			}
			if (gradeist.length > 1) {
				for (int i = 1; i < courselist.length; i++) {
					text = text.replace("<letter grade>", gradeist[i]);
				}
			}

			lettershow.setText(text);
//			System.out.println(information.get(0));
			System.out.println(courselist[0]);
			System.out.println(courselist[1]);
			document.close();
			String letter1 = lettershow.getText();
			getletter(letter1);

		}

	}
//	public static void main(String[] args) throws IOException {
//		
//	}

	public void switchtocreatenewLetter(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("createnewLetter.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
