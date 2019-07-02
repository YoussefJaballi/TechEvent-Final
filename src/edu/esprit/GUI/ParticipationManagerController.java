/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.Category;
import edu.esprit.models.Participation;
import edu.esprit.models.RoleParticipation;
import edu.esprit.models.User;
import edu.esprit.utils.ServiceManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class ParticipationManagerController implements Initializable {

    public static List<Participation> validatedParticipations = new ArrayList<>();

    @FXML
    private ComboBox<User> userCombo;
    @FXML
    private ComboBox<RoleParticipation> roleCombo;

    private ObservableList<User> users = FXCollections.observableArrayList();
    private ObservableList<RoleParticipation> roles = FXCollections.observableArrayList();
    private ObservableList<Participation> participations = FXCollections.observableArrayList();
    @FXML
    private TableView<Participation> participationTable;
    @FXML
    private TableColumn<Participation, String> UserColumn;
    @FXML
    private TableColumn<Participation, String> RoleColumn;
    private Participation participationToModify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.initList();
        this.initRolesCombo();
        this.initUsersCombo();
        this.initTable();
    }

    private void initTable() {
        this.participationTable.setItems(participations);
        this.UserColumn.setCellValueFactory(new PropertyValueFactory<Participation, String>("userName"));
        this.RoleColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getRole().getDescription()));
        this.participationTable.addEventHandler(EventType.ROOT, e -> this.OnSelectParticipation());
    }

    private void initList() {
        if (validatedParticipations.size() != 0) {
            this.participations.addAll(validatedParticipations);
        }
    }

    private void initUsersCombo() {
        users.addAll(ServiceManager
                .getInstance()
                .getUserService()
                .findAll());

        this.userCombo.setItems(users);
        this.userCombo.setConverter(new StringConverter<User>() {

            @Override
            public String toString(User object) {
                return object.getName();
            }

            @Override
            public User fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.userCombo.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(ListView<User> param) {
                final ListCell<User> cell = new ListCell<User>() {

                    @Override
                    protected void updateItem(User t, boolean bln) {
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

    private void initRolesCombo() {
        roles.addAll(ServiceManager
                .getInstance()
                .getRoleParticipationService()
                .findAll());

        this.roleCombo.setItems(roles);
        this.roleCombo.setConverter(new StringConverter<RoleParticipation>() {

            @Override
            public String toString(RoleParticipation object) {
                return object.getDescription();
            }

            @Override
            public RoleParticipation fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.roleCombo.setCellFactory(new Callback<ListView<RoleParticipation>, ListCell<RoleParticipation>>() {

            @Override
            public ListCell<RoleParticipation> call(ListView<RoleParticipation> param) {
                final ListCell<RoleParticipation> cell = new ListCell<RoleParticipation>() {

                    @Override
                    protected void updateItem(RoleParticipation t, boolean bln) {
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

    @FXML
    private void OnAdd(MouseEvent event) {
        User u = this.userCombo.getValue();
        RoleParticipation r = this.roleCombo.getValue();
        Participation p = new Participation(u.getId(), 0, r);
        p.setUserName(u.getName() + " " + u.getLastName());
        this.participations.add(p);
        this.userCombo.setValue(null);
        this.roleCombo.setValue(null);

    }

    private void OnSelectParticipation() {
        Participation p = this.participationTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            this.participationToModify = p;
            this.roleCombo.setValue(p.getRole());
            this.userCombo.setValue(
                    this.users
                    .stream()
                    .filter(u -> u.getId() == p.getUserId()).findFirst().get()
            );
        }

    }

    @FXML
    private void OnModify(MouseEvent event) {
        this.participations.remove(participationToModify);
        OnAdd(event);
    }

    @FXML
    private void OnDelete(MouseEvent event) {
        this.participations.remove(participationToModify);

    }

    @FXML
    private void OnValidate(MouseEvent event) {
        validatedParticipations = this.participations.stream().collect(Collectors.toList());
        Stage s = (Stage) this.roleCombo.getScene().getWindow();
        s.close();

    }

}
