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
    TextField username;
    PasswordField password;

    public LoginPanel(LoginController lg, LoginView parent){
        this.parent = parent;
    }
    public Scene login(){

        Label userNameTag = new Label("Usuario");
        userNameTag.setFont(new Font(15));
        VBox.setMargin(userNameTag, new Insets(10,0,0,10));
        this.username = new TextField();
        VBox.setMargin(username, new Insets(10));
        username.setPrefWidth(400);
        username.setFont(new Font(15));
        username.setPromptText("usuario");

        Label passwordTag = new Label("Contraseña");
        passwordTag.setFont(new Font(15));
        VBox.setMargin(passwordTag, new Insets(10,0,0,10));
        this.password = new PasswordField();
        VBox.setMargin(password, new Insets(10));
        password.setPrefWidth(400);
        password.setFont(new Font(15));
        password.setPromptText("Contraseña");

        this.btn = new Button("Ingresar");
        btn.setPrefWidth(250);
        btn.setFont(new Font(18));
        VBox.setMargin(btn, new Insets(15));
        VBox btnContainer = new VBox(btn);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.setCursor(Cursor.HAND);
        btn.setOnAction(this.parent);


        VBox loginContainer = new VBox(userNameTag, username, passwordTag, password, btnContainer);
        VBox.setMargin(loginContainer, new Insets(20,20,20,20));
        loginContainer.setPrefWidth(500);
        loginContainer.setId("loginContainer");

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

        VBox containerLeft = new VBox(loginContainer, msgContainer);
        containerLeft.setPrefWidth(500);
        containerLeft.setMinWidth(300);
        containerLeft.setAlignment(Pos.CENTER);
        containerLeft.setPrefWidth(500);
        containerLeft.setId("left");

        VBox imageContainer = new VBox();
        try {

            Image image = new Image(new FileInputStream("./imgs/logoUptc.png"));
            ImageView logo = new ImageView(image);
            logo.setFitWidth(400);
            logo.setFitHeight(200);
            imageContainer.setPrefWidth(500);
            imageContainer.getChildren().add(logo);
            imageContainer.setAlignment(Pos.CENTER);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        HBox root = new HBox(containerLeft, imageContainer);
        HBox.setHgrow(containerLeft, Priority.SOMETIMES);
        HBox.setHgrow(imageContainer, Priority.ALWAYS);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/login.css").toURI().toString());
        return scene;
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
