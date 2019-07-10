/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.services.implementation.UserService;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class EventListController implements Initializable {

    @FXML
    private TableView<ObservableEvent> personTable;
    @FXML
    private TableColumn<ObservableEvent, String> titleColumn;
    @FXML
    private TableColumn<ObservableEvent, String> categoryColumn;
    @FXML
    private TableColumn<ObservableEvent, String> etpColumn;
    @FXML
    private TableColumn<ObservableEvent, Date> dateColumn;
    @FXML
    private CheckBox myEvents;
    @FXML
    private TextField filterField;

    private ObservableList<ObservableEvent> allEventList = FXCollections.observableArrayList();
    private ObservableList<ObservableEvent> eventList = FXCollections.observableArrayList();
    private ObservableList<ObservableEvent> filtredEventList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.filterField.textProperty().addListener((observable, oldValue, newValue) -> this.onFilterChange());
        // TODO
        allEventList.addAll(ServiceManager.getInstance()
                .getEventService()
                .findAll()
                .stream().map(e -> new ObservableEvent(e.getId(),
                                e.getTitle(),
                                e.getOrganisator().
                                getEntreprise().getName(),
                                e.getSessions().get(0).getStartTime(),
                                e.getCategory().getName()))
                .collect(Collectors.toList())
        );
        this.eventList.addAll(allEventList);
        this.filtredEventList.addAll(eventList);
        System.out.println(filtredEventList);
        this.personTable.setItems(filtredEventList);
        this.titleColumn.setCellValueFactory(new PropertyValueFactory<ObservableEvent, String>("title"));
        this.etpColumn.setCellValueFactory(new PropertyValueFactory<ObservableEvent, String>("entrepriseName"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<ObservableEvent, Date>("eventDate"));
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory<ObservableEvent, String>("category"));
        this.personTable.getSelectionModel().selectedItemProperty().addListener(e -> {
            EventDetailsController.eventID = this.personTable.getSelectionModel().getSelectedItems().get(0).getId();
            try {
                this.container = new AnchorPane();
                AnchorPane root = FXMLLoader.load(getClass().getResource("EventDetails.fxml"));
                HomeController.instance.setContent(root);
                //this.personTable.getSelectionModel().clearSelection();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }

    private void onFilterChange() {
        this.filtredEventList.clear();
        this.filtredEventList.addAll(this.eventList.stream()
                .filter(e -> e.title.contains(this.filterField.getText())
                        || e.category.contains(this.filterField.getText())
                        || e.entrepriseName.contains(this.filterField.getText())
                        || e.eventDate.toString().contains(this.filterField.getText())
                )
                .collect(Collectors.toList())
        );
    }

    public void setFilter(String filter) {
        System.out.println("this is filter : "+filter);
        this.filterField.setText(""+filter);
    }

    @FXML
    private void onCheckboxChanged(ActionEvent event) {
        if (this.myEvents.isSelected()) {
            this.eventList.clear();
            this.eventList.addAll(
                    this.allEventList
                    .stream()
                    .filter(ev -> UserManager.getParticipation().stream().anyMatch(p -> p.getEventId() == ev.getId()))
                    .collect(Collectors.toList())
            );
        } else {
            this.eventList.clear();
            this.eventList.addAll(this.allEventList);
        }
        this.onFilterChange();

    }

    public class ObservableEvent {

        private int id;
        private String title;
        private String entrepriseName;
        private Date eventDate;
        private String category;

        public ObservableEvent(int id, String title, String entrepriseName, Date eventDate, String category) {
            this.id = id;
            this.title = title;
            this.entrepriseName = entrepriseName;
            this.eventDate = eventDate;
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getEntrepriseName() {
            return entrepriseName;
        }

        public Date getEventDate() {
            return eventDate;
        }

        public String getCategory() {
            return category;
        }

    }

}
