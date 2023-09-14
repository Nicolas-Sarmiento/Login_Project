package co.edu.uptc.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginDashBoard {

    LoginView parent;
    VBox container;
    VBox containerInformationUser;
    Label msgWelcom;
    VBox imageContainer;
    Label msgUsername;
    Label msgRolUser;

    VBox containerButtoms;
    Button btnOption1;
    Button btnOption2;
    Button btnOption3;
    Button btnOption4;



    public LoginDashBoard(LoginView loginView) {
        this.parent = loginView;
    }

    public Scene dashBoard(){
        this.settingContainer();
        HBox root = new HBox(container);
        Scene scene = new Scene(root, 500, 600);

        scene.getStylesheets().add(new File("./styles/dashboard.css").toURI().toString());
        return scene;
    }

    private void settingContainer() {
        settingContainerInformation();
        settingContainerButtoms();
        container = new VBox(containerInformationUser, containerButtoms);
    }

    private void settingContainerButtoms() {
        if(parent.controller.showRol().equals("ADMINISTRATOR")){
            settingButtomsAdministrator();
            containerButtoms = new VBox(btnOption1, btnOption2, btnOption3, btnOption4);
        }else{
            settingButtomsGenerals();
            containerButtoms = new VBox(btnOption3, btnOption4);
        }

        this.containerButtoms.setCursor(Cursor.HAND);
        this.containerButtoms.setAlignment(Pos.CENTER);

    }

    private void settingButtomsGenerals(){
        this.btnOption3 = new Button("Change password");
        this.btnOption4 = new Button("Logout");

        this.btnOption3.setFont(new Font(18));
        this.btnOption4.setFont(new Font(18));
        VBox.setMargin(btnOption3, new Insets(15));
        VBox.setMargin(btnOption4, new Insets(15));
        this.btnOption3.setOnAction(this.parent);
        this.btnOption4.setOnAction(this.parent);
    }

    private void settingButtomsAdministrator() {
        settingButtomsGenerals();
        this.btnOption1 = new Button("Sing in accounts");
        this.btnOption2 = new Button("See accounts");

        VBox.setMargin(btnOption1, new Insets(15));
        VBox.setMargin(btnOption2, new Insets(15));
        this.btnOption1.setFont(new Font(18));
        this.btnOption2.setFont(new Font(18));
        this.btnOption1.setOnAction(this.parent);
        this.btnOption2.setOnAction(this.parent);

    }

    private void settingContainerInformation() {
        settingmsgWelcomField();
        settingImageContainer();
        settingUsernameField();
        settingRolUserField();

        this.containerInformationUser = new VBox(msgWelcom, imageContainer, msgUsername, msgRolUser);
        this.containerInformationUser.setAlignment(Pos.CENTER);
    }

    private void settingImageContainer() {
        this.imageContainer = new VBox();
        try {
            Image image = new Image(new FileInputStream("./imgs/fotoPerfil.png"));
            ImageView profilePicture = new ImageView(image);
            profilePicture.setFitWidth(150);
            profilePicture.setFitHeight(150);
            this.imageContainer.setPrefWidth(500);
            this.imageContainer.getChildren().add(profilePicture);
            this.imageContainer.setAlignment(Pos.CENTER);
        }catch (FileNotFoundException e) {}
    }

    private void settingmsgWelcomField() {
        this.msgWelcom = new Label("¡Bienvenido!");
        this.msgWelcom.setFont(new Font(20));
        VBox.setMargin(msgWelcom, new Insets(25,20,0,20));
    }

    private void settingRolUserField() {
        this.msgRolUser = new Label("Rol: " + parent.controller.showRol());
        this.msgRolUser.setFont(new Font(15));
    }

    private void settingUsernameField(){
        this.msgUsername = new Label("Usuario: " + parent.controller.getName());
        msgUsername.setFont(new Font(15));
    }
    
}
