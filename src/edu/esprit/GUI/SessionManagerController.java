/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import com.browniebytes.javafx.control.DateTimePicker;
import static edu.esprit.GUI.ParticipationManagerController.validatedParticipations;
import edu.esprit.models.Participation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import edu.esprit.models.Session;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class SessionManagerController implements Initializable {

    private AnchorPane Cont;
    DateTimePicker sessionBeginInput = new DateTimePicker();
    DateTimePicker sessionEndInput = new DateTimePicker();
    @FXML
    private AnchorPane beginCont;
    @FXML
    private AnchorPane endCont;
    @FXML
    private TableView<Session> sessionTable;
    @FXML
    private TableColumn<Session, String> sessionNameColumn;
    @FXML
    private TableColumn<Session, String> beginColumn;
    @FXML
    private TableColumn<Session, String> endColumn;
    public static List<Session> validatedSessions = new ArrayList<>();
    private ObservableList<Session> sessions = FXCollections.observableArrayList();
    @FXML
    private TextField nameInput;
    private Session sessionToModify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initList();
        this.initTable();
        this.beginCont.getChildren().add(sessionBeginInput);
        this.endCont.getChildren().add(sessionEndInput);

        // TODO
    }

    @FXML
    private void OnAdd(MouseEvent event) {
        Session s = new Session(0, java.sql.Timestamp.valueOf(sessionBeginInput.getDateTime()), java.sql.Timestamp.valueOf(this.sessionEndInput.getDateTime()), this.nameInput.getText(), "");
        System.out.println(s);
        this.sessions.add(s);
    }

    @FXML
    private void OnModify(MouseEvent event) {
        this.sessions.remove(sessionToModify);
        OnAdd(event);
    }

    @FXML
    private void OnDelete(MouseEvent event) {
        this.sessions.remove(sessionToModify);

    }

    @FXML
    private void OnValidate(MouseEvent event) {
        validatedSessions = this.sessions.stream().collect(Collectors.toList());
        Stage s = (Stage) this.sessionEndInput.getScene().getWindow();
        s.close();
    }

    private void initList() {
        if (validatedSessions.size() != 0) {
            this.sessions.addAll(validatedSessions);
        }
    }

    private void initTable() {
        sessionTable.setItems(sessions);
        this.sessionNameColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getName()));
        this.beginColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getStartTime().toString()));
        this.endColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEndTime().toString()));
        this.sessionTable.getSelectionModel().selectedItemProperty().addListener(e -> this.OnSelectSession());

    }

    private void OnSelectSession() {
        Session p = this.sessionTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            this.sessionToModify = p;
            this.nameInput.setText(p.getName());
            this.sessionBeginInput = new DateTimePicker(LocalDateTime.ofInstant(p.getStartTime().toInstant(), ZoneId.systemDefault()));
            this.sessionEndInput = new DateTimePicker(LocalDateTime.ofInstant(p.getEndTime().toInstant(), ZoneId.systemDefault()));

        }
    }

}
