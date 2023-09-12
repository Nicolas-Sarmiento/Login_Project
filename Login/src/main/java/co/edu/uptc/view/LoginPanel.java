package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginPanel {
    LoginView parent;
    Label msg;
    VBox msgContainer;
    Button btn;
    VBox btnContainer;
    TextField username;
    PasswordField password;

    Label userNameTag;
    Label passwordTag;
    VBox loginContainer;
    VBox containerLeft;
    VBox imageContainer;

    public LoginPanel(LoginView parent){
        this.parent = parent;
    }
    public Scene login(){

        this.settingLeftContainer();
        this.settingImageContainer();

        HBox root = new HBox(containerLeft, imageContainer);
        HBox.setHgrow(containerLeft, Priority.SOMETIMES);
        HBox.setHgrow(imageContainer, Priority.ALWAYS);
        Scene scene = new Scene(root, 1000, 600);

        scene.getStylesheets().add(new File("./styles/login.css").toURI().toString());
        return scene;
    }


    private void settingUsernameField(){
        this.userNameTag = new Label("Usuario");
        userNameTag.setFont(new Font(15));
        VBox.setMargin(userNameTag, new Insets(10,0,0,10));
        this.username = new TextField();
        VBox.setMargin(username, new Insets(10));
        this.username.setPrefWidth(400);
        this.username.setFont(new Font(15));
        this.username.setPromptText("usuario");
    }

    private void settingPasswordField(){
        this.passwordTag = new Label("Contraseña");
        passwordTag.setFont(new Font(15));
        VBox.setMargin(passwordTag, new Insets(10,0,0,10));
        this.password = new PasswordField();
        VBox.setMargin(password, new Insets(10));
        this.password.setPrefWidth(400);
        this.password.setFont(new Font(15));
        this.password.setPromptText("Contraseña");
    }

    private void settingLoginButton(){
        this.btn = new Button("Ingresar");
        btn.setPrefWidth(250);
        btn.setFont(new Font(18));
        VBox.setMargin(btn, new Insets(15));
        this.btnContainer = new VBox(btn);
        this.btnContainer.setAlignment(Pos.CENTER);
        this.btnContainer.setCursor(Cursor.HAND);
        this.btn.setOnAction(this.parent);
    }

    private void settingLoginContainer(){
        this.settingUsernameField();
        this.settingPasswordField();
        this.settingLoginButton();
        this.loginContainer = new VBox(this.userNameTag, this.username, this.passwordTag, this.password, this.btnContainer);
        VBox.setMargin(loginContainer, new Insets(20,20,20,20));
        this.loginContainer.setPrefWidth(500);
        this.loginContainer.setId("loginContainer");
    }

    private void settingErrorContainer(){
        this.msg = new Label("Error");
        this.msg.setId("error");
        this.msg.setAlignment(Pos.CENTER);
        this.msg.setFont(new Font(15));
        VBox.setMargin(this.msg, new Insets(5));
        this.msgContainer = new VBox(this.msg);
        this.msgContainer.setId("errorContainer");
        this.msgContainer.setMinWidth(200);
        this.msgContainer.setMaxWidth(400);
        this.msgContainer.setVisible(false);
        VBox.setMargin(this.msgContainer, new Insets(15));
        this.msgContainer.setAlignment(Pos.CENTER);
    }

    private void settingLeftContainer(){
        this.settingLoginContainer();

        this.settingErrorContainer();

        this.containerLeft = new VBox(loginContainer, msgContainer);
        this.containerLeft.setPrefWidth(500);
        this.containerLeft.setMinWidth(300);
        this.containerLeft.setAlignment(Pos.CENTER);
        this.containerLeft.setPrefWidth(500);
        this.containerLeft.setId("left");
    }

    private void settingImageContainer(){
        this.imageContainer = new VBox();
        try {

            Image image = new Image(new FileInputStream("./imgs/logoUptc.png"));
            ImageView logo = new ImageView(image);
            logo.setFitWidth(400);
            logo.setFitHeight(200);
            this.imageContainer.setPrefWidth(500);
            this.imageContainer.getChildren().add(logo);
            this.imageContainer.setAlignment(Pos.CENTER);
        }catch (FileNotFoundException e) {}
    }

    public Scene emptyScene(){

        return new Scene(new StackPane(new Text("Loggeado")));
    }

    public Button getBtn(){
        return this.btn;
    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setMessage(String s){
        this.msg.setText(s);
    }

    public void setVisibleErrorMessage(boolean v){
        this.msgContainer.setVisible(v);
    }


}
