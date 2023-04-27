package application;

import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/*   Controller class in charge of sign up, reset, login, and main menu functions   */

public class Controller  {
	
	private Stage stage;
	private Scene scene;
	private AnchorPane root;

	/*  Sign up, Login, and Reset scene information   */
	
	@FXML
	private Button btmadd;
	
	@FXML
	private TextField tfpassword;
	
	@FXML
	private TextField tfp1;
	
	@FXML
	private Label show;
	
	@FXML
	private Button verifybtm;
	
	@FXML
	private Label show1;
	
	@FXML
	private TextField nametf;
	
	@FXML
	private TextField passwordtf;
	
	@FXML
	private Button signupbtm;
	
	@FXML
	private Label show2;	
	
	@FXML TextField reenter;

	/*  Function to switch scene to reset page  */
	
	public void switchToreset(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("reset.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/*  Function to switch scene to logged-in page   */
	
	
	public void switchTologgedin(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("logged-in.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/*   Function to show the main menu if login is successful  */
	
	public void showMenu(ActionEvent event) throws IOException {
		String password = tfp1.getText();
		SQLite app = new SQLite();
		String verify = SQLite.getpass();
		if (password.equals(verify)) {
			System.out.print(password);
			show.setText("successfully");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			show.setText("Not successfully");
		}

	}

	/*   Function to determine if old password and new password are the same when resetting password   */
	
	public void addButton(ActionEvent event) throws IOException 
	{
		SQLite app = new SQLite();
		String password = tfpassword.getText();
		String enter = reenter.getText();
		if(password.equals(enter)) 
		{
			show1.setText("successfully");
			String name = SQLite.getname();
			// System.out.print(name);
			app.delete();
			app.insert(name, password);
			switchTologgedin(event);
			
			
		}
		else 
		{
			show1.setText("password and re-enter not match");
		}
		
	}

	/*  Function for user to be able to sign up if they don't already have an account   */
	
	public void signupButton(ActionEvent event) throws IOException {
		String password = passwordtf.getText();
		if(password.equals("p")) {
			switchToreset(event);
		}
		else {
			show2.setText("Not successfully");
		}
		
//		SQLite app = new SQLite();
//		app.delete();
//		app.insert("", password);

	}
	
	/*  Function to switch back to the main menu from any of the other scenes    */
	
	public void switchtomenu(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

	/*  Function to switch to the add, delete, rename course feature page   */
	
	public void switchtoaddDeleteRenameCourse(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("addDeleteRenameCourse.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/*   Function to switch to the create new letter page   */
	
	public void switchtocreatenewLetter(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("createnewLetter.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchtoOldLetter(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("searchdraft.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	
//	public void switchtoinputtext(ActionEvent event) throws IOException{
//		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("profile.fxml"));
//		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}

	
//	public void switchTomainMenu(ActionEvent event) throws IOException {
////String password = tfpassword1.getText();
////SQLite app = new SQLite();
////String result = app.getpass();
////if(password.equals(result)) {
////	AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
////	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////	scene = new Scene(root);
////	stage.setScene(scene);
////	stage.show();
////}
////else {
////	show.setText("Wrong password");
////}
//AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
//stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//scene = new Scene(root);
//stage.setScene(scene);
//stage.show();
////System.out.print(password);
//
//
//}
	

}
