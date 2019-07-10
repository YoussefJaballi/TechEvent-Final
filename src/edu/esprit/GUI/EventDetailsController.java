/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import edu.esprit.models.Event;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import edu.esprit.models.Comment;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class EventDetailsController implements Initializable, MapComponentInitializedListener {

    public static int eventID;

    private static Event event;

    public static Event getEvent() {
        return event;
    }

    public static void setEvent(Event event) {
        EventDetailsController.event = event;

    }
    GoogleMapView mapComponent;
    protected GoogleMap Gmap;
    private MarkerOptions markerOptions2;
    private Marker myMarker2;
    private Stage popupreport = new Stage();
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label organisateur;
    @FXML
    private Label categorie;
    @FXML
    private AnchorPane map;
    @FXML
    private TableView<ObservableSession> table;
    @FXML
    private TableColumn<ObservableSession, String> tnom;
    @FXML
    private TableColumn<ObservableSession, Date> tdate;
    private ObservableList<ObservableSession> sessionlist = FXCollections.observableArrayList();
    private ObservableList<ObservableSession> allSessionList = FXCollections.observableArrayList();
    private ObservableList<ObservableSession> filtredSessionList = FXCollections.observableArrayList();
    @FXML
    private ImageView img;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button reportButton;
    @FXML
    private HBox buttonContainer;
    @FXML
    private VBox CommentPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
           
            

            event = ServiceManager.getInstance().getEventService().find(eventID);
            if (event.getOrganisator().getId() == UserManager.getUser().getId()) {
                this.buttonContainer.getChildren().remove(this.reportButton);
            } else {
                this.buttonContainer.getChildren().remove(this.modifier);
                if (UserManager.getUser().getRole().getId() != 1) {
                    this.buttonContainer.getChildren().remove(this.supprimer);
                }
            }
            Image i = new Image("https://res.cloudinary.com/ddzyat9y5/image/upload/v1561911958/" + event.getPhotoURL());
            this.img.setImage(i);
            this.titre.setText(event.getTitle());
            this.description.setText(event.getDescription());
            this.organisateur.setText(event.getOrganisator().getName());
            this.categorie.setText(event.getCategory().getName());
            this.initMap();

            allSessionList.addAll(event.getSessions()
                    .stream().map(e -> new ObservableSession(e.getEventId(),
                    e.getName(),
                    e.getStartTime()
            ))
                    .collect(Collectors.toList())
            );

            this.table.setItems(allSessionList);
            this.tnom.setCellValueFactory(new PropertyValueFactory<ObservableSession, String>("name"));

            this.tdate.setCellValueFactory(new PropertyValueFactory<ObservableSession, Date>("Sdate"));
            
            this.chargeComment();

        } catch (ComposedIDExeption ex) {
            Logger.getLogger(EventDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
  
    }

    private void initMap() {
        mapComponent = new GoogleMapView();
        mapComponent.addMapInializedListener(this);
        mapComponent.setDisableDoubleClick(false);
        mapComponent.getWebview().getEngine().setOnAlert((WebEvent<String> event) -> {
            System.out.println("Event event: " + event);
        });
        mapComponent.setMaxWidth(200);
        mapComponent.setMaxHeight(200);

        this.map.getChildren().add(mapComponent);
    }

    private void OnClickMap(LatLong cord) {
        System.out.println(cord);
        this.Gmap.clearMarkers();
        markerOptions2 = new MarkerOptions();
        markerOptions2.position(cord);
        markerOptions2.title("qsdqsd");
        myMarker2 = new Marker(markerOptions2);

        myMarker2 = new Marker(markerOptions2);
        this.Gmap.addMarker(myMarker2);
        myMarker2.setVisible(true);
    }

    @Override
    public void mapInitialized() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Calling showDirections from Java");
                Platform.runLater(() -> mapComponent.getMap().hideDirectionsPane());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        t.start();
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong center = new LatLong(event.getLocation().getLatitude(), event.getLocation().getLongitude());
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
        });
        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(5)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        Gmap = mapComponent.createMap(options);
        this.OnClickMap(new LatLong(event.getLocation().getLatitude(), event.getLocation().getLongitude()));
        Gmap.setHeading(123.2);
//        System.out.println("Heading is: " + map.getHeading() );

        //map.showDirectionPane();
    }

    @FXML
    private void onModifyClicked(MouseEvent ev) {
        try {
            AddEventController.modification = true;
            AddEventController.event = event;
            Parent root = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            s.show();
            //this.personTable.getSelectionModel().clearSelection();

        } catch (IOException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerEvent(MouseEvent ev) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("voulez vous supprimer l'evenement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceManager.getInstance().getEventService().delete(event);

        } else {
            alert.close();
        }

    }

    @FXML
    private void OnClickReclam(MouseEvent event) {

        try {
            popupreport.initModality(Modality.APPLICATION_MODAL);
        popupreport.setTitle("Ce que contient la réclamation");

        TextField body=new TextField() ;
        body.setDisable(false);
                 
        Button valider = new Button("Valider");
       // Button annuler = new Button("Annuler");
        Button fermer = new Button("Fermer");
        fermer.setOnAction(e -> popupreport.close());
        valider.setOnAction(p -> this.addReport());
        VBox containerlayout = new VBox();
        containerlayout.setSpacing(20);
        containerlayout.setPadding(new Insets(50, 50, 50, 50));

        HBox bodylayout = new HBox();
        bodylayout.setSpacing(10);
        bodylayout.setPadding(new Insets(10, 5, 3, 3));
        bodylayout.getChildren().addAll(body);
        bodylayout.setAlignment(Pos.BASELINE_LEFT);
        containerlayout.getChildren().add(bodylayout);

        HBox buttonlayout = new HBox();
        buttonlayout.setSpacing(5);
        buttonlayout.setPadding(new Insets(0, 0, 0, 0));
        buttonlayout.setAlignment(Pos.BASELINE_LEFT);
        buttonlayout.getChildren().addAll(valider,fermer);

        containerlayout.getChildren().add(buttonlayout);

        Scene scene1 = new Scene(containerlayout, 300, 200);

        popupreport.setScene(scene1);

        popupreport.showAndWait();
            
        } catch (Exception exx) {
            System.out.println("I don't know what fuck is happen");
        }
    }

    private void addReport() {
       
        
     
    }
    
    public void chargeComment()
    {
        System.out.println(event.getComments());
        for (Comment c :event.getComments())
        {
        try{
             final Rating rating = new Rating();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Display_comment.fxml"));
            AnchorPane root=loader.load();
            Display_commentController controllerComment= loader.<Display_commentController>getController();
            controllerComment.displayComment(c);
            CommentPart.getChildren().addAll(root);
            System.out.println("Merde de merde" +c.getBody());
        }
        catch(Exception x)
        {
            System.out.println("Fuck off");
        }
        }
        
    }
    
    

    public class ObservableSession {

        private int id;
        private String name;
        private Date Sdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getSdate() {
            return Sdate;
        }

        public void setSdate(Date Ssate) {
            this.Sdate = Ssate;
        }

        public ObservableSession(int id, String name, Date Sdate) {
            this.id = id;
            this.name = name;
            this.Sdate = Sdate;
        }

    }

}
