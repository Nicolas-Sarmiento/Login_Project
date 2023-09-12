package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    public void handle(ActionEvent e) {

        if (e.getSource() == this.login.getBtn()){

            boolean response = this.controller.login(this.login.getUsername().getText(), this.login.getPassword().getText());
            if (response){
                this.setScene(this.login.emptyScene());
            }else {
                this.login.getUsername().setText("");
                this.login.getPassword().setText("");
                this.login.setMessage("Invalid user or password login");
                this.login.setVisibleErrorMessage(true);
            }
        }
    }
}
