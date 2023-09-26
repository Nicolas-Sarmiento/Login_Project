package co.edu.uptc.view.forum;

import javafx.application.Platform;
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
    private VBox generalContainer;
    private Button confirmButton;
    public Scene confirmAction(){

        this.msgDone = new Label("¿Estas seguro?\nEsta accion no se podra revertir.");
        msgDone.setId("msg");

        this.confirmButton = new Button("Aceptar");
        confirmButton.getStyleClass().add("confirm-button");
        actionButton();

        this.generalContainer = new VBox();
        generalContainer.getChildren().addAll(this.msgDone,this.confirmButton);
        generalContainer.setId("containerDelete");
        generalContainer.setSpacing(10);

        this.scene = new Scene(generalContainer, 300, 200);
        scene.getStylesheets().add(new File("./styles/forum/ConfirmAction.css").toURI().toString());
        return scene;
    }

    public void actionButton(){
        confirmButton.setOnAction(event -> {
            System.out.println("funcionan");
        });
    }
}
