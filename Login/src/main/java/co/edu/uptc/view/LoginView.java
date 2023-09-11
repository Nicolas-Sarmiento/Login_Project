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
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginView  extends Application implements EventHandler<ActionEvent> {
    LoginPanel login;
    Stage stage;
    LoginController controller;
    public LoginView (){
        this.controller = new LoginController();
        this.login =new LoginPanel(this.controller, this);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(this.login.login());
        this.stage.setTitle("Login UPTC");
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

   public void setScene(Scene sc){
        this.stage.setScene(sc);
   }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.stage.setScene(this.login.emptyScene());
    }
}
