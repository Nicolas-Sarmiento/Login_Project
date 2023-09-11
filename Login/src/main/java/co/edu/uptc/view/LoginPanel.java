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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginPanel {
    LoginController controller;
    boolean isLogged;
    LoginView parent;

    public LoginPanel(LoginController lg, LoginView parent){
        this.controller = lg;
        this.isLogged = false;
        this.parent = parent;
    }
    public Scene login(){

        Label userNameTag = new Label("Usuario");
        userNameTag.setFont(new Font(15));
        VBox.setMargin(userNameTag, new Insets(10,0,0,10));
        TextField username = new TextField();
        VBox.setMargin(username, new Insets(10));
        username.setPrefWidth(400);
        username.setFont(new Font(15));
        username.setPromptText("usuario");

        Label passwordTag = new Label("Contraseña");
        passwordTag.setFont(new Font(15));
        VBox.setMargin(passwordTag, new Insets(10,0,0,10));
        PasswordField password = new PasswordField();
        VBox.setMargin(password, new Insets(10));
        password.setPrefWidth(400);
        password.setFont(new Font(15));
        password.setPromptText("Contraseña");

        Button btn = new Button("Ingresar");
        btn.setPrefWidth(500);
        btn.setFont(new Font(18));
        VBox.setMargin(btn, new Insets(10));
        VBox btnContainer = new VBox(btn);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.setCursor(Cursor.HAND);
        btn.setOnAction(this.parent);


        VBox loginContainer = new VBox(userNameTag, username, passwordTag, password, btnContainer);
        loginContainer.setBackground( new Background(new BackgroundFill(Color.web("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setMargin(loginContainer, new Insets(20,20,20,20));
        loginContainer.setPrefWidth(500);

        VBox containerLeft = new VBox(loginContainer);
        containerLeft.setPrefWidth(500);
        containerLeft.setMinWidth(300);
        containerLeft.setAlignment(Pos.CENTER);
        containerLeft.setPrefWidth(500);
        containerLeft.setBackground( new Background(new BackgroundFill(Color.web("#F4D03F"), CornerRadii.EMPTY, Insets.EMPTY)));

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

        return scene;
    }
    public Scene emptyScene(){

        return new Scene(new StackPane(new Text("Loggeado")));
    }
}
