package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;

public class ChangePasswordOptionConfig extends Header {

    Button confirmButton;
    Label oldPassword;
    Label newPasswordSecond;
    Label newPassword;
    Label changePasswordMessage;
    Label newPasswordErrorLabel;
    VBox optionInfoContainer;
    TextField newPasswordField;
    TextField newPasswordFieldSecond;
    TextField oldPasswordField;
    VBox root;
    LoginView loginView;
    public ChangePasswordOptionConfig(LoginView loginView, Button button){
        super(button);
        this.loginView = loginView;
        this.changePasswordMessage = new Label("La contraseña se ha cambiado satisfactoriamente.");
        this.newPasswordErrorLabel = new Label("Las contraselas no coinciden");
        this.changePasswordMessage.setVisible(false);
        this.newPasswordErrorLabel.setVisible(false);
    }
    public Scene settingInfoContainer(){

        this.passwordChangeOptions();

        this.optionInfoContainer = new VBox();
        this.optionInfoContainer.setAlignment(Pos.CENTER);
        optionInfoContainer.getChildren().addAll(this.oldPassword, this.newPasswordErrorLabel, this.oldPasswordField, this.newPassword, this.newPasswordField, this.newPasswordSecond,this.newPasswordFieldSecond, this.confirmButton, this.changePasswordMessage);//aqui esta el error
        oldPasswordField.setVisible(true);
        newPasswordField.setVisible(true);
        confirmButton.setVisible(true);
        oldPassword.setVisible(true);
        newPassword.setVisible(true);
        newPasswordSecond.setVisible(true);
        newPasswordFieldSecond.setVisible(true);

        root = new VBox();
        root.getChildren().addAll(getHeader(), this.optionInfoContainer);
        this.setName(this.loginView.controller.getName());
        Scene scene = new Scene(root,1000,600);
        scene.getStylesheets().add(new File("./styles/header.css").toURI().toString());
        return scene;
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

        confirmButton.setOnAction(this.loginView);
    }
    public void validatePasswords(){
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

    public void passwordChangeSuccesfully(){
        changePasswordMessage.setVisible(true);
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public void setNewPasswordField(String newPasswordField) {
        this.newPasswordField.setText(newPasswordField);
    }

    public TextField getNewPasswordFieldSecond() {
        return newPasswordFieldSecond;
    }

    public void setNewPasswordFieldSecond(String newPasswordFieldSecond) {
        this.newPasswordFieldSecond.setText(newPasswordFieldSecond);
    }

    public TextField getOldPasswordField() {
        return oldPasswordField;
    }

    public void setOldPasswordField(String oldPasswordField) {
        this.oldPasswordField.setText(oldPasswordField);
    }
}
