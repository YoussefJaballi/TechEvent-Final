/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.Comment;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Djo
 */
public class Display_commentController implements Initializable {

    @FXML
    private ImageView Image_user;
    @FXML
    private Label User_name;
    @FXML
    private TextArea body;
    @FXML
    private Button report_comment;
    @FXML
    private Button Deleting;
    @FXML
    private HBox buttonContainer;
    @FXML
    private Button getResponcesBtn;
    @FXML
    private Button modifyBtn;
    private Comment comment;
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Report(MouseEvent event) {
    }

    @FXML
    private void Delete(MouseEvent event) {
    }

    public void displayComment(Comment c) {
        //AnchorPane content=null;  
        this.comment = c;
        Image i = new Image("https://res.cloudinary.com/ddzyat9y5/image/upload/v1561911958/" + c.getUser().getPhotoURL());
        Image_user.setImage(i);
        User_name.setText(c.getUser().getName());
        body.setText(c.getBody());
        if (UserManager.getUser().getId() == c.getUser().getId()) {
            this.buttonContainer.getChildren().remove(this.report_comment);

        } else {
            this.buttonContainer.getChildren().remove(this.Deleting);
            this.buttonContainer.getChildren().remove(this.modifyBtn);

        }

        if (c.getChildcomments().size() == 0) {
            this.buttonContainer.getChildren().remove(this.getResponcesBtn);
        }

        //content.getChildren().addAll(i,Image_user,User_name,body,Deleting,report_comment)
    }

    @FXML
    private void onGetChilds(MouseEvent event) throws IOException {
        // System.out.println("Hhh"+comment);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayChildComment.fxml"));
        Parent root = loader.load();
        DisplayChildCommentController controller = loader.<DisplayChildCommentController>getController();
        controller.setchilds(this.comment);
        Stage s = new Stage();
        Scene se = new Scene(root);
        s.setScene(se);
        s.show();

    }

    @FXML
    private void onModifyComment(MouseEvent event) {

        //   Comment commentToModify=comment;
        this.body.setEditable(true);
        saveButton = new Button("Save");
        this.buttonContainer.getChildren().remove(this.modifyBtn);
        buttonContainer.getChildren().add(saveButton);
        saveButton.setOnAction(s -> this.save_Modify_Comment());

    }

    private void save_Modify_Comment() {

        try {
            this.comment.setBody(body.getText());
            ServiceManager.getInstance().getCommentService().edit(comment);
            this.body.setEditable(false);
            this.buttonContainer.getChildren().remove(this.saveButton);
            buttonContainer.getChildren().add(modifyBtn);

        } catch (Exception d) {
            d.printStackTrace();
        }
    }
}
