package co.edu.uptc.view;

import co.edu.uptc.controller.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
/**
 * This class represents the change password configuration view.
 * It allows users to change their passwords.
 */
public class ChangePasswordOptionConfig extends Header {

    Button confirmButton;
    Label oldPassword;
    Label newPasswordSecond;
    Label newPassword;
    Label changePasswordMessage;
    Label newPasswordErrorLabel;
    Label incorrectPassword;
    VBox optionInfoContainer;
    VBox container;
    private TextField newPasswordField;
    private TextField newPasswordFieldSecond;
    private TextField oldPasswordField;
    VBox root;
    HBox containerError;
    LoginView loginView;
    /**
     * Constructs a new ChangePasswordOptionConfig instance.
     *
     * @param loginView The LoginView instance associated with this view.
     * @param button    The button used to navigate back to the dashboard.
     */
    public ChangePasswordOptionConfig(LoginView loginView, Button button){
        super(button);
        this.loginView = loginView;
        this.changePasswordMessage = new Label("La contraseña se ha cambiado satisfactoriamente.");//sigue aparecinedo pegado al lado izquierdo de la pantalla
        this.newPasswordErrorLabel = new Label("Las contraselas no coinciden");
        this.incorrectPassword = new Label("Contrasela incorrecta");
        changePasswordMessage.setVisible(false);
        this.newPasswordErrorLabel.setVisible(false);
        this.incorrectPassword.setVisible(false);
    }
    /**
     * Creates and configures the UI elements for the change password view.
     *
     * @return A Scene object representing the change password view.
     */
    public Scene settingInfoContainer(){

        this.passwordChangeOptions();

        this.optionInfoContainer = new VBox();
        this.containerError = new HBox();
        this.container = new VBox();
        this.optionInfoContainer.getChildren().addAll(this.oldPassword, this.oldPasswordField,this.incorrectPassword, this.newPassword, this.newPasswordField, this.newPasswordSecond,this.newPasswordFieldSecond,this.newPasswordErrorLabel, this.confirmButton);
        this.containerError.getChildren().addAll(changePasswordMessage);//necesito que esto este centrado debejo de optionInfoContainer

        this.optionInfoContainer.setId("general");
        this.containerError.setId("error");
        this.changePasswordMessage.setId("succesfully");

        oldPasswordField.setVisible(true);
        newPasswordField.setVisible(true);
        confirmButton.setVisible(true);
        oldPassword.setVisible(true);
        newPassword.setVisible(true);
        newPasswordSecond.setVisible(true);
        newPasswordFieldSecond.setVisible(true);
        containerError.setVisible(false);

        container.getChildren().addAll(this.optionInfoContainer, this.containerError);
        container.setAlignment(Pos.CENTER);
        this.root = new VBox();
        root.getChildren().addAll(getHeader(),container);

        VBox.setMargin(root, new Insets(0, 0, 0, 0));

        this.setName(this.loginView.controller.getName());
        root.setId("root");
        Scene scene = new Scene(root,1000,600);
        scene.getStylesheets().add(new File("./styles/changePassword.css").toURI().toString());
        return scene;
    }
    // Private method to configure password change options
    private void passwordChangeOptions(){
        newPasswordField = new TextField();
        newPassword = new Label("Digita tu nueva contraseña:");
        newPasswordField.setId("newPasswordField");
        newPasswordField.setMaxWidth(200);

        oldPasswordField = new TextField();
        oldPassword = new Label("Digita tu anterior contraseña:");
        oldPasswordField.setId("oldPasswordField");
        oldPasswordField.setMaxWidth(200);

        newPasswordSecond = new Label("Digita tu nueva contraseña otra vez:");
        newPasswordFieldSecond = new TextField();
        newPasswordFieldSecond.setId("newPasswordSecond");
        newPasswordFieldSecond.setMaxWidth(200);

        confirmButton = new Button("Confirmar");
        confirmButton.setFont( new Font(14));
        confirmButton.setId("confirmButton");
        incorrectPassword.setId("incorrect");
        animation();

        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePasswords();
        });

        newPasswordFieldSecond.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePasswords();
        });

        confirmButton.setOnAction(this.loginView);
    }
    /**
     * Validates the entered passwords and updates UI accordingly.
     */
    public void validatePasswords(){
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
    public void animation(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(confirmButton.styleProperty(), "-fx-background-color: #F7DC6F;")),
                new KeyFrame(Duration.seconds(1), new KeyValue(confirmButton.styleProperty(), "-fx-background-color: #85C1E9;")),
                new KeyFrame(Duration.seconds(2), new KeyValue(confirmButton.styleProperty(), "-fx-background-color: #F1948A;"))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        confirmButton.setOnMouseEntered(event -> timeline.play());

        confirmButton.setOnMouseExited(event -> timeline.pause());
    }

    public void passwordChangeSuccesfully(){
        containerError.setVisible(true);
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

    public Label getIncorrectPassword() {
        return incorrectPassword;
    }
}
