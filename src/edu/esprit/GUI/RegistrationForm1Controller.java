/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.RoleUser;
import edu.esprit.models.User;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author yjaballi
 */
public class RegistrationForm1Controller implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Label RoleLabel;
    @FXML
    private ComboBox<RoleUser> RoleComboBox;

    private ObservableList<RoleUser> roles = FXCollections.observableArrayList();
    @FXML
    private AnchorPane RegistrationAnchropane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initRoles();
    }    

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        if(RoleComboBox.getValue() != null)
        {
            User u = new User();
            u.setRoleID(RoleComboBox.getValue());
            UserManager.setRegisterUser(u);
            RegistrationAnchropane.getChildren().clear();
            AnchorPane content = null;
            content = FXMLLoader.load(getClass().getResource("RegistrationForm2.fxml"));
            RegistrationAnchropane.getChildren().add(content);
        }
    }
    
    
    private void initRoles() {
        roles.addAll(ServiceManager
                .getInstance().getRoleUserService()
                .findAll());

        this.RoleComboBox.setItems(roles);
        this.RoleComboBox.setConverter(new StringConverter<RoleUser>() {
            @Override
            public String toString(RoleUser object) {
                return object.getDescription();
            }

            @Override
            public RoleUser fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

           
        });
        this.RoleComboBox.setCellFactory(new Callback<ListView<RoleUser>, ListCell<RoleUser>>() {
            @Override
            public ListCell<RoleUser> call(ListView<RoleUser> param) {
               final ListCell<RoleUser> cell = new ListCell<RoleUser>(){
                 @Override
                    protected void updateItem(RoleUser t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getDescription());
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
