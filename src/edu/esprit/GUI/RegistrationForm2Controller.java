/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import academiccalendar.ui.listcalendars.ListCalendarsController;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import edu.esprit.utils.UserManager;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class RegistrationForm2Controller implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextField NomTxt;
    @FXML
    private Label NomLabel;
    @FXML
    private TextField PrenomTxt;
    @FXML
    private Label PrenomLabel;
    @FXML
    private DatePicker DateNaissanceDate;
    @FXML
    private Label DateNaissanceLabel;
    @FXML
    private Button PhotoButton;
    @FXML
    private Label PhotoLabel;

    private File photo;
    
    private String FileName;
    
    public static final String URL = "http://localhost/pidev/services/uploadImage.php";
    @FXML
    private AnchorPane RegistrationForm2AnchorPane;
    
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        if(NomTxt.getText() !=  null && PrenomTxt.getText() !=  null && DateNaissanceDate.getValue() != null)
        {
            
            UserManager.getRegisterUser().setName(NomTxt.getText());
            UserManager.getRegisterUser().setLastName(PrenomTxt.getText());
            UserManager.getRegisterUser().setBirthday(java.sql.Date.valueOf(DateNaissanceDate.getValue()));
            UserManager.getRegisterUser().setPhotoURL(FileName);
            RegistrationForm2AnchorPane.getChildren().clear();
            AnchorPane content = null;
            content = FXMLLoader.load(getClass().getResource("RegistrationForm3.fxml"));
            RegistrationForm2AnchorPane.getChildren().add(content);
            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RegistrationForm3.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL); 

           
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();*/
            
        }
    }

    @FXML
    private void handlePhotoButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        photo = fileChooser.showOpenDialog(new Stage());
        FileName = photo.getAbsolutePath();
        //System.out.println(photo.getAbsolutePath());
        sendFileToHTTP(photo);
    }
    public void sendFileToHTTP(File file){

        
         Map uploadResult = null;
        Map<Object, Object> CONFIG = new HashMap<>();
        CONFIG.put("cloud_name", "ddzyat9y5");
        CONFIG.put("api_key", "337446516883967");
        CONFIG.put("api_secret", "u1IQp__loijm2yxlwvLAsbj7ER4");

        Cloudinary cloudinary = new Cloudinary(CONFIG);


       
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException ex) {
            System.out.println("cant upload");
            Logger.getLogger(RegistrationForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

          FileName = (String)uploadResult.get("public_id");
          System.out.println("FileName : "+FileName);
        
        
       /* String newName = fileName + file.getName().substring(file.getName().indexOf("."));

        String charset = "UTF-8";

        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.

        URLConnection connection = new URL(URL).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (
                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);) {

            // Send  file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"" + "image" + "\"; filename=\"" + newName + "\"").append(CRLF);
            writer.append("Content-Type: text/html; charset=UTF-8 ").append(CRLF);

            writer.append(CRLF).flush();
            Files.copy(file.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

// Request is lazily fired whenever you need to obtain information about response.
        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        System.out.println(responseCode); // Should be 200
        return (newName);*/
    }
    
}
