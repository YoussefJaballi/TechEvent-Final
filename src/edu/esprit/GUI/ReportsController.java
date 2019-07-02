/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import edu.esprit.models.Report;
import edu.esprit.models.User;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.esprit.services.implementation.ReportService;
import edu.esprit.utils.ServiceManager;
import java.awt.Dialog;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class ReportsController implements Initializable {

    @FXML
    private TableView<Report> reportsTable;
    @FXML
    private TableColumn<Report, String> reporter_id;
    @FXML
    private TableColumn<Report, String> target;
    @FXML
    private TableColumn<Report, String> target_id;

    private ObservableList<Report> allReports = FXCollections.observableArrayList();
    private Stage popupwindow = new Stage();
    @FXML
    private AnchorPane reportsAnchorpane;
    @FXML
    private AnchorPane rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initialize_report();
    }

    public void initialize_report() {

        allReports.addAll(ServiceManager.getInstance().getReportService().findAll());

        System.out.println("My reports" + allReports);

        reportsTable.setItems(allReports);

       /* reporter_id.setCellValueFactory(e -> {
            try {
                return new ReadOnlyStringWrapper(ServiceManager.getInstance().getUserService().find(e.getValue().getReporterId()).getName());

            } catch (Exception ex) {
                e.printStackTrace();
            }
            return ""
        });*/
        target.setCellValueFactory(new PropertyValueFactory<>("targettype"));
        target_id.setCellValueFactory(new PropertyValueFactory<>("targetId"));
        reportsTable.getSelectionModel().selectedItemProperty().addListener(e -> this.getSelectedreport());

    }

    public void getSelectedreport() {
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Ce que contient la réclamation");

        Label body = new Label(reportsTable.getSelectionModel().getSelectedItem().getBody());

        Button valider = new Button("Valider");
        Button ignorer = new Button("Ignorer");
        Button fermer = new Button("Fermer");
        fermer.setOnAction(e -> popupwindow.close());
        valider.setOnAction(p -> this.Sendmail());
        ignorer.setOnAction(e -> this.deleteReprot());

        VBox containerlayout = new VBox();
        containerlayout.setSpacing(20);
        containerlayout.setPadding(new Insets(50, 50, 50, 50));

        HBox bodylayout = new HBox();
        bodylayout.setSpacing(10);
        bodylayout.setPadding(new Insets(15, 20, 10, 10));
        bodylayout.getChildren().addAll(body);
        bodylayout.setAlignment(Pos.BASELINE_LEFT);
        containerlayout.getChildren().add(bodylayout);

        HBox buttonlayout = new HBox();
        buttonlayout.setSpacing(5);
        buttonlayout.setPadding(new Insets(0, 0, 0, 0));
        buttonlayout.setAlignment(Pos.BASELINE_LEFT);
        buttonlayout.getChildren().addAll(valider, ignorer, fermer);

        containerlayout.getChildren().add(buttonlayout);

        Scene scene1 = new Scene(containerlayout, 300, 200);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

    private void Sendmail() {
        //Né9sa l'intégration mta3 lmailing

    }

    private void deleteReprot() {
        try {
            Report reportToDelete = ServiceManager.getInstance().getReportService().find(reportsTable.getSelectionModel().getSelectedItem().getId());
            ServiceManager.getInstance().getReportService().delete(reportToDelete);
            popupwindow.close();
            reportsAnchorpane.getChildren().clear();
            AnchorPane content = null;
            content = FXMLLoader.load(getClass().getResource("Reports.fxml"));
            reportsAnchorpane.getChildren().add(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
