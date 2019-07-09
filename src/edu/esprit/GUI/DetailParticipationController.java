/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.Participation;
import edu.esprit.models.User;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Service;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class DetailParticipationController implements Initializable {

    public static List<Participation> partisipations = new ArrayList<>();
    @FXML
    private TableView<Participation> participationTable;
    @FXML
    private TableColumn<Participation, String> UserColumn;
    @FXML
    private TableColumn<Participation, String> RoleColumn;
    private ObservableList<Participation> participationsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }

    private void initTable() {
        this.participationsList.addAll(partisipations);
        this.participationTable.setItems(participationsList);
        this.UserColumn.setCellValueFactory(e -> {
            try {
                User u = ServiceManager.getInstance().getUserService().find(e.getValue().getUserId());
                return new ReadOnlyStringWrapper(u.getName()+" "+u.getLastName());

            } catch (ComposedIDExeption ex) {
                Logger.getLogger(DetailParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new ReadOnlyStringWrapper("");

        });
        this.RoleColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getRole().getDescription()));
    }
}
