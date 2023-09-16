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

    /**
     * Constructs a LoginDashboard instance.
     *
     * @param loginView The parent LoginView instance.
     */
    public LoginDashBoard(LoginView loginView) {
        this.parent = loginView;
    }

    /**
     * Creates the scene of dashboard.
     * @return The Scene of dashboard
     */
    public Scene dashBoard(){
        this.settingContainer();
        HBox root = new HBox(container);
        root.setId("root");
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/dashboard.css").toURI().toString());
        return scene;
    }

    /**
     * Information containers and buttons are configured.
     * Buttons and information are inserted into the main container.
     * Margins and alignment are set.
     */
    private void settingContainer() {
        settingContainerInformation();
        settingContainerButtoms();
        container = new VBox(containerInformationUser, containerButtoms);
        container.setAlignment(Pos.CENTER);
        VBox.setMargin(container, new Insets(10));
        container.setId("container");
    }

    /**
     * Validates if the role is different from administrator to show certain buttons on the dashboard.
     * Buttons are included on the container depending on the role and alignment are set.
     */
    private void settingContainerButtoms() {
        if(parent.controller.showRol().equals("ADMINISTRATOR")){
            settingButtomsAdministrator();
            containerButtoms = new VBox(btnOption1, btnOption2, btnOption3, btnOption4);
        }else{
            settingButtomsGenerals();
            containerButtoms = new VBox(btnOption3, btnOption4);
        }

        this.containerButtoms.setAlignment(Pos.CENTER);
    }

    /**
     *The general options buttons are configured to appear regardless
     * of the user's role. Margins are set.
     */
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

    /**
     *  The buttons of the options that will only appear when the
     *  user is the administrator are configured.
     */
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

    /**
     * Method for setting up a user information container
     * containing welcome labels, an image, user name and user role,
     * and aligning the content in the centre.
     */
    private void settingContainerInformation() {
        settingmsgWelcomField();
        settingImageContainer();
        settingUsernameField();
        settingRolUserField();

        this.containerInformationUser = new VBox(msgWelcom, imageContainer, msgUsername, msgRolUser);
        this.containerInformationUser.setAlignment(Pos.CENTER);
    }

    /**
     * Method to configure the user image via a container.
     * The role image is configured, establishing measurements
     * and alignment. Possible exceptions are foreseen.
     */
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

    /**
     * Method for setting the welcome label field.
     * A label with the text "Welcome!" is created
     * and a font size of 20 is set.
     */
    private void settingmsgWelcomField() {
        this.msgWelcom = new Label("¡Bienvenido!");
        this.msgWelcom.setFont(new Font(20));
        VBox.setMargin(msgWelcom, new Insets(25,20,0,20));
    }

    /**
     * Method for setting the user role label field.
     * The user role is obtained from the controller
     * and displayed in a label with a font size of 15.
     */
    private void settingRolUserField() {
        this.msgRolUser = new Label("Rol: " + parent.controller.showRol());
        this.msgRolUser.setFont(new Font(15));
    }

    /**
     * Method for setting the user name label field.
     * The user name is retrieved from the controller
     * and displayed in a label with a font size of 15.
     */
    private void settingUsernameField(){
        this.msgUsername = new Label("Usuario: " + parent.controller.getName());
        msgUsername.setFont(new Font(15));
    }
    
}
