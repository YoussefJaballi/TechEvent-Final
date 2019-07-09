/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.models.Comment;
import edu.esprit.models.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Djo
 */
public class DisplayChildCommentController implements Initializable {

    @FXML
    private VBox container;

    private Comment comment;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void setchilds(Comment c){
    this.comment=c;
    System.out.println(c.getChildcomments());
    c.getChildcomments().forEach(e->{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Display_comment.fxml"));
            AnchorPane root;
        try {
            root = loader.load();
            Display_commentController controllerComment= loader.<Display_commentController>getController();
            controllerComment.displayComment(e);
            container.getChildren().addAll(root);
        } catch (IOException ex) {
            Logger.getLogger(DisplayChildCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    });
    
}    
    
}
