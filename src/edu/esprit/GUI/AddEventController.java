/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import edu.esprit.models.Category;
import edu.esprit.models.Comment;
import edu.esprit.models.Event;
import edu.esprit.models.Location;
import edu.esprit.models.Rating;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceMail;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author azer
 */
public class AddEventController implements Initializable, MapComponentInitializedListener {

    public static boolean modification = false;
    public static Event event;
    @FXML
    private AnchorPane mapCont;
    GoogleMapView mapComponent;
    protected GoogleMap map;
    private MarkerOptions markerOptions2;
    private Marker myMarker2;

    private File photo;
    @FXML
    private Label photoPathLabel;
    @FXML
    private TextField TitleInput;
    @FXML
    private TextArea DescInput;
    @FXML
    private ComboBox<Category> CategoryCmbo;
    @FXML
    private HBox PriceContainer;
    @FXML
    private TextField priceInput;
    @FXML
    private CheckBox freeCheck;

    private ObservableList<Category> categorys = FXCollections.observableArrayList();
    private Location location;
    @FXML
    private Button submitButton;
    @FXML
    private Button session;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.initMap();
        this.initForm();
        this.initCategory();

    }

    private void initCategory() {
        categorys.addAll(ServiceManager
                .getInstance()
                .getCategoryService()
                .findAll());

        this.CategoryCmbo.setItems(categorys);
        this.CategoryCmbo.setConverter(new StringConverter<Category>() {

            @Override
            public String toString(Category object) {
                return object.getName();
            }

            @Override
            public Category fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.CategoryCmbo.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {

            @Override
            public ListCell<Category> call(ListView<Category> param) {
                final ListCell<Category> cell = new ListCell<Category>() {

                    @Override
                    protected void updateItem(Category t, boolean bln) {
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

    private void initMap() {
        mapComponent = new GoogleMapView();
        mapComponent.addMapInializedListener(this);
        mapComponent.setDisableDoubleClick(false);
        mapComponent.getWebview().getEngine().setOnAlert((WebEvent<String> event) -> {
            System.out.println("Event event: " + event);
        });
        mapComponent.setMaxWidth(200);
        mapComponent.setMaxHeight(200);

        this.mapCont.getChildren().add(mapComponent);
    }

    private void OnClickMap(LatLong cord) {
        System.out.println(cord);
        this.location = new Location("ll", cord.getLatitude(), cord.getLongitude(), 1);
        this.map.clearMarkers();
        markerOptions2 = new MarkerOptions();
        markerOptions2.position(cord);
        markerOptions2.title("qsdqsd");
        myMarker2 = new Marker(markerOptions2);

        myMarker2 = new Marker(markerOptions2);
        this.map.addMarker(myMarker2);
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
        LatLong center = new LatLong(34.782259, 9.7005960);
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
        });
        if (modification) {
            center = new LatLong(event.getLocation().getLatitude(), event.getLocation().getLongitude());
        }
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

        map = mapComponent.createMap(options);
        if (modification) {
            this.OnClickMap(center);
        }

        map.addUIEventHandler(UIEventType.click, e -> {
            OnClickMap(new LatLong((JSObject) e.getMember("latLng")));
        });
        map.setHeading(123.2);
//        System.out.println("Heading is: " + map.getHeading() );

        //map.showDirectionPane();
    }

    @FXML
    private void OnBrowseClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        photo = fileChooser.showOpenDialog(new Stage());
        this.photoPathLabel.setText(photo.getAbsolutePath());

    }

    @FXML
    private void OnFreeChecked(MouseEvent event) {
        if (this.freeCheck.isSelected()) {
            this.PriceContainer.setVisible(true);
        } else {
            this.PriceContainer.setVisible(false);

        }
    }

    @FXML
    private void OnClickPartisipation(MouseEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ParticipationManager.fxml"));
            Stage s = new Stage();
            Scene se = new Scene(root);
            s.setScene(se);
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void OnClickSession(MouseEvent event) throws IOException {

        Parent root;
        root = FXMLLoader.load(getClass().getResource("SessionManager.fxml"));
        Stage s = new Stage();
        Scene se = new Scene(root);
        s.setScene(se);
        s.show();
    }

    @FXML
    private void OnCreateEvent(MouseEvent ev) {
        if (modification) {
            ServiceManager.getInstance().getEventService().delete(event);
        }
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        //  alert.setContentText("il faut remplir tous les champs ");
        if (this.CategoryCmbo.getValue() == null) {
            alert.setContentText("Categorie obligatoire  !");
            alert.showAndWait();
            this.CategoryCmbo.requestFocus();
        } else if (this.location == null) {
            alert.setContentText("adresse obligatoire ! ");
            alert.showAndWait();

        } else if (this.TitleInput.getText().equals("")) {
            alert.setContentText("Titre obligatoire ! ");
            alert.showAndWait();
            this.TitleInput.requestFocus();
        } else if (SessionManagerController.validatedSessions == null || SessionManagerController.validatedSessions.size() == 0) {
            alert.setContentText("session vide ! ");
            alert.showAndWait();
            this.session.requestFocus();

        }

        Map uploadResult = null;
        Map<Object, Object> CONFIG = new HashMap<>();
        CONFIG.put("cloud_name", "ddzyat9y5");
        CONFIG.put("api_key", "337446516883967");
        CONFIG.put("api_secret", "u1IQp__loijm2yxlwvLAsbj7ER4");

        Cloudinary cloudinary = new Cloudinary(CONFIG);

        try {
            if (photo != null) {
                uploadResult = cloudinary.uploader().upload(this.photo, ObjectUtils.emptyMap());

            }

            Event e = new Event(
                    this.TitleInput.getText(),
                    this.DescInput.getText(),
                    uploadResult == null ? "" : (String) uploadResult.get("public_id"),
                    UserManager.getUser(),
                    this.location,
                    this.CategoryCmbo.getValue(),
                    Float.parseFloat(this.priceInput.getText()),
                    new ArrayList<Rating>(),
                    ParticipationManagerController.validatedParticipations,
                    SessionManagerController.validatedSessions,
                    new ArrayList<Comment>()
            );
            String message = "Vous ette invité a l evennement " + e.getTitle() + "creer par l'entreprise " + e.getOrganisator().getEntreprise().getName();

            
            e.getParticipations().forEach(p -> {
                try {
                    ServiceMail.sendMail(
                            ServiceManager.getInstance().getUserService().find(p.getUserId()).getEmail(),
                            "Invitation a un evennement",
                            message
                    );
                } catch (Exception ex) {
                    Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            System.out.println(e);
            ServiceManager.getInstance().getEventService().create(e);
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initForm() {
        if (modification) {
            this.submitButton.setText("Modifier");
            this.fillForm(event);
        }
    }

    private void fillForm(Event event) {
        this.TitleInput.setText(event.getTitle());
        this.DescInput.setText(event.getDescription());
        this.priceInput.setText("" + event.getPrice());
        this.CategoryCmbo.setValue(event.getCategory());
        try {
            event = ServiceManager.getInstance().getEventService().find(EventDetailsController.eventID);
            this.photoPathLabel.setText(event.getPhotoURL());
            SessionManagerController.validatedSessions = event.getSessions();
            ParticipationManagerController.validatedParticipations = event.getParticipations();

        } catch (ComposedIDExeption ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
