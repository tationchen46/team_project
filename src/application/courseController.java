package application;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class courseController implements Initializable {
	
	private Stage stage;
    private Scene scene;
    private AnchorPane root;

    /*  Adding, renaming, and deleting course information     */
     
    @FXML ChoiceBox<String> delete;
    @FXML ChoiceBox<String> rename;
    @FXML TextField add;
    @FXML TextField newname;
     
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    	SQLite app = new SQLite();
    	try 
    	{
			ArrayList<String> al=app.getcourse();
			delete.getItems().setAll(al);
			rename.getItems().setAll(al);
			
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	
    }
    
    /*  Function to add course to list in DB    */
    
    public void addcourse(ActionEvent event) throws IOException
    {
    	String course = add.getText();
		SQLite app = new SQLite();
		app.addcourse(course);
    }
    
    /*  Function to delete course in list in DB    */
    
    public void deletecourse(ActionEvent event) throws IOException
    {
    	String course = delete.getValue();
    	SQLite app = new SQLite();
    	app.removecourse(course); 	
    }
    
    /*   Function to rename course in list in DB    */
    
    public void rename() 
    {
    	String course = rename.getValue();
    	SQLite app = new SQLite();
    	app.removecourse(course);
    	String course1 = newname.getText();
		app.addcourse(course1);    	
    }

    /* Function to go back to the main menu from the add course scene   */
    
    public void backToMainMenu(ActionEvent event) throws IOException 
    {
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
