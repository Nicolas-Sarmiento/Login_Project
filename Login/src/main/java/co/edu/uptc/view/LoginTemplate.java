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
    Button changePasswordButton;
    Button confirmButton;
    HBox nameContainer;
    Label name;
    Label oldPassword;
    Label newPasswordSecond;
    Label newPassword;
    Label newPasswordErrorLabel;
    VBox optionInfoContainer;
    Label optionInfo;
    TextField newPasswordField;
    TextField newPasswordFieldSecond;
    TextField oldPasswordField;
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
        this.settingInfoContainer();
        this.passwordChangeOptions();


        this.header = new HBox();
        this.header.getChildren().addAll(this.homeButtonContainer, this.optionInfoContainer, this.nameContainer);
        this.header.setId("header");

        this.header.setAlignment(Pos.CENTER);
        this.Container = new VBox();
        this.containerPassword = new VBox();
        containerPassword.getChildren().addAll(this.oldPassword,this.oldPasswordField,this.newPassword,this.newPasswordField,this.newPasswordSecond,this.newPasswordFieldSecond,this.confirmButton);
        containerPassword.setSpacing(10);
        containerPassword.setAlignment(Pos.CENTER);
        containerPassword.setPadding(new Insets(50,0,0,0));
        Container.getChildren().addAll(containerPassword);

        VBox root = new VBox(header, this.Container);
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
    private void settingInfoContainer(){
        this.optionInfoContainer = new VBox();
        changePasswordButton = new Button("Cambiar contraseña");
        changePasswordButton.setId("changePassword");
        this.changePasswordButton.setAlignment(Pos.CENTER);
        this.changePasswordButton.setMaxWidth(Double.MAX_VALUE);

        this.optionInfoContainer.getChildren().add(this.changePasswordButton);
        this.optionInfoContainer.setAlignment(Pos.CENTER);
        this.changePasswordButton.setCursor(Cursor.HAND);

        changePasswordButton.setOnAction(event -> {
            oldPasswordField.setVisible(true);
            newPasswordField.setVisible(true);
            confirmButton.setVisible(true);
            oldPassword.setVisible(true);
            newPassword.setVisible(true);
            newPasswordSecond.setVisible(true);
            newPasswordFieldSecond.setVisible(true);
        });

        HBox.setHgrow(this.optionInfoContainer, Priority.ALWAYS);
    }
    private void passwordChangeOptions(){
        newPasswordField = new TextField();
        newPassword = new Label("Digita tu nueva contraseña:");
        newPasswordField.setId("newPasswordField");
        newPasswordField.setAlignment(Pos.CENTER);
        newPasswordField.setMaxWidth(200);

        oldPasswordField = new TextField();
        oldPassword = new Label("Digita tu anterior contraseña:");
        oldPasswordField.setId("oldPasswordField");
        oldPasswordField.setMaxWidth(200);
        oldPasswordField.setAlignment(Pos.CENTER);

        newPasswordSecond = new Label("Digita tu nueva contraseña otra vez:");
        newPasswordFieldSecond = new TextField();
        newPasswordFieldSecond.setId("newPasswordSecond");
        newPasswordFieldSecond.setMaxWidth(200);
        newPasswordFieldSecond.setAlignment(Pos.CENTER);

        confirmButton = new Button("Confirmar");
        confirmButton.setId("confirmButton");
        confirmButton.setAlignment(Pos.CENTER);

        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePasswords();
        });

        newPasswordFieldSecond.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePasswords();
        });

        confirmButton.setOnAction(event -> {
            //Esta logica la hago cuando ya se haya implementado, se me facilitara mas
            System.out.println("Por que espicho el boton?");
            oldPasswordField.getText();
            //loginController.changePassword()
            /*
            * */
        });

        newPasswordField.setVisible(false);
        oldPasswordField.setVisible(false);
        confirmButton.setVisible(false);
        oldPassword.setVisible(false);
        newPassword.setVisible(false);
        newPasswordSecond.setVisible(false);
        newPasswordFieldSecond.setVisible(false);
    }
    public void validatePasswords(){
        newPasswordErrorLabel = new Label("Las contraselas no coinciden");
        //De alguna manera conectar con la cuenta logeada
        String password1 = newPasswordField.getText();
        String password2 = newPasswordFieldSecond.getText();

        if (password1.equals(password2)) {
            newPasswordErrorLabel.setVisible(false);
            newPasswordField.setStyle("-fx-border-color: green;");
            newPasswordFieldSecond.setStyle("-fx-border-color: green;");
        } else {
            newPasswordErrorLabel.setVisible(true);
            newPasswordField.setStyle("-fx-border-color: red;");
            newPasswordFieldSecond.setStyle("-fx-border-color: red;");
        }
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
