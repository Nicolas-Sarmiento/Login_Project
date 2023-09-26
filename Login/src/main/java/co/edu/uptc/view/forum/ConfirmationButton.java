package co.edu.uptc.view.forum;

import co.edu.uptc.view.LoginView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmationButton {
    private Scene scene;
    private Label msgDone;
    private Label hecho;
    private VBox generalContainer;
    private Button confirmButton;
    private ForumView forumView;
    public ConfirmationButton(ForumView forumView){
        this.forumView = forumView;
    }
    public Scene confirmAction(){
        this.hecho = new Label("Hecho");
        hecho.setId("msgD");
        hecho.setVisible(false);

        this.msgDone = new Label("¿Estas seguro?\nEsta accion no se podra revertir.");
        msgDone.setId("msg");

        this.confirmButton = new Button("Aceptar");
        confirmButton.getStyleClass().add("confirm-button");
        actionButton();

        this.generalContainer = new VBox();
        generalContainer.getChildren().addAll(this.msgDone,this.confirmButton, this.hecho);
        generalContainer.setId("containerDelete");
        generalContainer.setSpacing(10);

        this.scene = new Scene(generalContainer, 300, 200);
        scene.getStylesheets().add(new File("./styles/forum/ConfirmAction.css").toURI().toString());
        return scene;
    }

    public void actionButton(){
        confirmButton.setOnAction(event -> {
            forumView.handle(new ActionEvent(confirmButton, confirmButton));
            hecho.setVisible(true);
        });
    }
    public Button getConfirmButton() {
        return confirmButton;
    }
}
