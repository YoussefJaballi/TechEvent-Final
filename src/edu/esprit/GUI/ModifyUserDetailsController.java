/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;


import edu.esprit.utils.CodeGeneration;
import edu.esprit.utils.ServiceMail;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ModifyUserDetailsController implements Initializable {

    
    @FXML
    private TextField emailTxt;
    @FXML
    private TextArea adresseTxt;
    @FXML
    private Hyperlink changePass;
    @FXML
    private TextField telTxt;
    @FXML
    private Button validerModif;
    @FXML
    private Button changePhone;
    @FXML
    private Button changeMail;
    @FXML
    private AnchorPane screen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailTxt.setText(UserManager.getUser().getEmail());
        adresseTxt.setText(UserManager.getUser().getAdress());
        telTxt.setText(UserManager.getUser().getPhone());
       
    }    

    @FXML
    private void onChangePassword(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("ChangePasswordConfirmation.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se); 
            s.show();
    }

    @FXML
    private void onValidModify(MouseEvent event) throws IOException {
        UserManager.getUser().setAdress(adresseTxt.getText());
        ServiceManager.getInstance().getUserService().edit(UserManager.getUser());
                    AnchorPane root = FXMLLoader.load(getClass().getResource("UserDetails.fxml"));
                    HomeController.instance.setContent(root);
                               
    }

    @FXML
    private void onChangePhone(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePhone.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    s.show();
        
    }

    @FXML
    private void onChangeMail(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangeMail.fxml"));
                    Stage s = new Stage();
                    Scene se = new Scene(root);
                    s.setScene(se);
                    s.show();
    }

    
    
}
