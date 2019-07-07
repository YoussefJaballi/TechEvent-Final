/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.utils.UserManager;
import edu.esprit.models.Entreprise;
import edu.esprit.models.RoleUser;
import edu.esprit.utils.ServiceManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class RegistrationForm3Controller implements Initializable {

    @FXML
    private AnchorPane RegistrationForm3AnchorPane;
    @FXML
    private Button button;
    @FXML
    private TextField AdresseTxt;
    @FXML
    private Label AdresseLabel;
    @FXML
    private TextField EmailTxt;
    @FXML
    private Label EmailLabel;
    @FXML
    private TextField TelephoneTxt;
    @FXML
    private Label TelephoneLabel;
    @FXML
    private ComboBox<Entreprise> EnterpriseComboBox;

    private ObservableList<Entreprise> enterprises = FXCollections.observableArrayList();
    @FXML
    private Label EnterpriseLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(UserManager.getRegisterUser().getRole().getDescription().equals("Event_Creator"))
        {
        this.initEnterprises();
        }
        else
        {
        RegistrationForm3AnchorPane.getChildren().remove(EnterpriseLabel);
        RegistrationForm3AnchorPane.getChildren().remove(EnterpriseComboBox);
        }
    }    

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        if(AdresseTxt.getText() !=  null && EmailTxt.getText() !=  null && TelephoneTxt.getText() !=  null && EnterpriseComboBox.getValue() != null)
        {
            System.out.println("avent set phone number :"+TelephoneTxt.getText());
            Entreprise e = EnterpriseComboBox.getValue();
            System.out.println("enterprise from combobox : "+e);
            UserManager.getRegisterUser().setAdress(AdresseTxt.getText());
            UserManager.getRegisterUser().setEmail(EmailTxt.getText());
            UserManager.getRegisterUser().setPhone(TelephoneTxt.getText());
            UserManager.getRegisterUser().setEntreprise(e);
            System.out.println("apres set phone number :"+UserManager.getRegisterUser().getPhone());
            RegistrationForm3AnchorPane.getChildren().clear();
            AnchorPane content = null;
            content = FXMLLoader.load(getClass().getResource("RegistrationForm4.fxml"));
            RegistrationForm3AnchorPane.getChildren().add(content);
            System.out.println("user enterprise:  "+UserManager.getRegisterUser().getEntreprise());
            System.out.println("user details interface 3:   "+UserManager.getRegisterUser());
        }
    }
    
    
    private void initEnterprises() {
        enterprises.addAll(ServiceManager
                .getInstance().getEntrepriseService().findAll());

        this.EnterpriseComboBox.setItems(enterprises);
        this.EnterpriseComboBox.setConverter(new StringConverter<Entreprise>() {
            @Override
            public String toString(Entreprise object) {
                return object.getName();
            }

            @Override
            public Entreprise fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
           

           
        });
        this.EnterpriseComboBox.setCellFactory(new Callback<ListView<Entreprise>, ListCell<Entreprise>>() {
            @Override
            public ListCell<Entreprise> call(ListView<Entreprise> param) {
               final ListCell<Entreprise> cell = new ListCell<Entreprise>(){
                 @Override
                    protected void updateItem(Entreprise t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getName());
                        } else {
                            setText(null);
                        }
                    }

                };
                return cell;
            }

            
        });

    }
    
}
