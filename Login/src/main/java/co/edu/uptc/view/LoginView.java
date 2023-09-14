package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginView  extends Application implements EventHandler<ActionEvent> {
    LoginPanel loginPanel;
    LoginDashBoard loginDashBoard;
    SingInView singInView;
    Stage stage;
    LoginController controller;
    LoginTemplate loginTemplate;

    public LoginView (){
        this.controller = new LoginController();
        this.loginPanel = new LoginPanel(this);
        this.singInView = new SingInView();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(this.loginPanel.login());
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

        if (e.getSource() == this.loginPanel.getBtn()){

            boolean response = this.controller.login(this.loginPanel.getUsername().getText(), this.loginPanel.getPassword().getText());
            if (response){
                this.loginDashBoard = new LoginDashBoard(this);
                this.stage.setTitle("Dashboard UPTC");
                this.setScene(loginDashBoard.dashBoard());

            }else {
                this.loginPanel.getUsername().setText("");
                this.loginPanel.getPassword().setText("");
                this.loginPanel.setMessage("Invalid user or password login");
                this.loginPanel.setVisibleErrorMessage(true);
            }
        }

        if (e.getSource() == this.loginDashBoard.btnOption1){
           // Incluir la pantalla de crear nuevas cuentas
        }

        if(e.getSource() == this.loginDashBoard.btnOption2){
            // Incluir la pantalla de ver todas las cuentas
        }

        if(e.getSource() == this.loginDashBoard.btnOption3){
            loginTemplate = new LoginTemplate();

            this.stage.setTitle("Cambiar contraseña");
            this.stage.setScene(loginTemplate.template());
        }

        if(e.getSource() == this.loginDashBoard.btnOption4){
            this.stage.setTitle("Login UPTC");
            this.stage.setScene(loginPanel.login());
        }


    }
}
