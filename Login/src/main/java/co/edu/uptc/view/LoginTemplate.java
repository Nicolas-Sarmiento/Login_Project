package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import co.edu.uptc.model.Person;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class LoginTemplate {
    HBox header;
    VBox homeButtonContainer;
    Button home;
    HBox nameContainer;
    Label name;
    VBox optionInfoContainer;
    Label optionInfo;
    VBox Container;
    VBox containerPassword;
    LoginController loginController;
    Person loggedPerson;
    public LoginTemplate(){
        loginController = new LoginController();
        loggedPerson = loginController.getLoggedPerson();
    }

    public Scene template(){

        this.settingHomeContainer();
        this.settingNameContainer();

        this.header = new HBox();
        this.header.getChildren().addAll(this.homeButtonContainer, this.optionInfoContainer, this.nameContainer);
        this.header.setId("header");

        this.header.setAlignment(Pos.CENTER);
        this.Container = new VBox();

        VBox root = new VBox(header);
        Scene scene = new Scene(root,1000,600);
        scene.getStylesheets().add(new File("./styles/header.css").toURI().toString());
        return scene;
    }

    private void settingHomeContainer(){
        this.homeButtonContainer = new VBox();
        ImageView homeIcon = new ImageView(new File("./imgs/home.png").toURI().toString());
        this.home = new Button("",homeIcon);
        this.home.setId("home");
        this.home.setAlignment(Pos.CENTER_LEFT);
        this.homeButtonContainer.getChildren().add(this.home);
        this.homeButtonContainer.setAlignment(Pos.CENTER);
        this.home.setCursor(Cursor.HAND);
        home.setOnAction(event -> {
            for (Node node : Container.getChildren()) {
                if (node instanceof VBox) {
                    VBox vbox = (VBox) node;
                    // Itera sobre los elementos en el VBox secundario
                    for (Node subNode : vbox.getChildren()) {
                        if (subNode instanceof TextField || subNode instanceof Label) {
                            subNode.setVisible(false);
                        } else if (subNode instanceof Button) {
                            ((Button) subNode).setVisible(false);
                        }
                    }
                }
            }
        });

        HBox.setHgrow(this.homeButtonContainer, Priority.ALWAYS);
    }

    private void settingNameContainer(){
        ImageView userIcon = new ImageView(new File("./imgs/user.png").toURI().toString());
        userIcon.setFitHeight(20);
        userIcon.setFitWidth(20);

        this.nameContainer = new HBox();
        this.name = new Label("Nino");
        this.name.setId("name");

        this.nameContainer.getChildren().addAll(userIcon, this.name);
        this.nameContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(this.nameContainer, Priority.ALWAYS);
    }

    public void setOption(String option){
        this.optionInfo.setText(option);
    }

    public void setName(String name){
        this.name.setText(name);
    }

    public Button getHome() {
        return home;
    }
}
