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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private VBox RegistrationVbox;
    @FXML
    private TitledPane RegistrationTitledPane;
    @FXML
    private GridPane RegistrationGridPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegistrationAnchropane.setStyle("-fx-background-image: url(\"/edu/esprit/images/Events.jpg\");-fx-background-repeat: stretch;   \n"
                + "    -fx-background-position: center center;\n"
                + " -fx-background-size: cover, auto;");
        //RegistrationVbox.setStyle("-fx-background-color:#34495e;");
        RegistrationVbox.setSpacing(15);
        RegistrationVbox.setPadding(new Insets(20));
        RegistrationVbox.setAlignment(Pos.BOTTOM_CENTER);
        RegistrationVbox.setSpacing(15);
        RegistrationVbox.setPrefWidth(500);
        //RegistrationVbox.setOpacity(0.5);
        //RegistrationTitledPane.setText(LanguageToolBar.BUNDLE.getString("userform"));
        RegistrationTitledPane.getStyleClass().add("primary");
        //RegistrationTitledPane.setOpacity(1);
        RegistrationGridPane.setVgap(4);
        RegistrationGridPane.setPadding(new Insets(5, 5, 5, 5));
        button.getStyleClass().add("primary");
        this.initRoles();
    }    

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        if(RoleComboBox.getValue() != null)
        {
            //instancier un user vide ensuite remplir le role de user par la valeur de RoleComboBox ensuite remplir le registerUser definie dans la classe singleton UserManager
            //effacer tous les elements de Anchorpane nomme RegistrationAnchropane et creer une variable de type Anchorpane nomme content pour ensuite remplir RegistrationAnchropane par content
            User u = new User();
            u.setRole(RoleComboBox.getValue());
            UserManager.setRegisterUser(u);
            RegistrationAnchropane.getChildren().clear();
            AnchorPane content = null;
            //charger la page RegistrationForm2.fxml dans content
            content = FXMLLoader.load(getClass().getResource("RegistrationForm2.fxml"));
            RegistrationAnchropane.getChildren().add(content);
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            //remplir le title d'une alerte
            alert.setTitle("Information Dialog");
            //remplir le header d'une alerte
            alert.setHeaderText("user");
            //remplir le contenu d'une alerte
            alert.setContentText("veuillez choisir un role");
            //affichage d'alerte et attendre jusqu la fermeture de alerte
                alert.showAndWait();
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
