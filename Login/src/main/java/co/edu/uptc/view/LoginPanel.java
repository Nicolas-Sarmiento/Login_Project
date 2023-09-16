package co.edu.uptc.view;

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
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The LoginPanel class represents the login interface.
 * It includes UI elements such as labels, text fields, and a login button.
 *
 * This class provides methods to set up and customize the login interface, including
 * setting the username and password fields, configuring the login button, and
 * handling error messages.
 */
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

    /**
     * Constructor for the LoginPanel.
     *
     * @param parent The parent LoginView to handle actions from this panel.
     */
    public LoginPanel(LoginView parent){
        this.parent = parent;
    }

    /**
     * Generates the login scene with the configured interface components.
     *
     * @return The Scene representing the login interface.
     */
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

    /**
     * Configures the username field in the login interface.
     * It creates and configures a Label for the username field with the text "Usuario",
     * sets its font, and adds margin. It also creates and configures a TextField for
     * entering the username, setting its preferred width, font, and prompt text.
     *
     * The username field allows users to input their username.
     */
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

    /**
     * Configures the password field in the login interface.
     * It creates and configures a Label for the password field with the text "Contraseña",
     * sets its font, and adds margin. It also creates and configures a PasswordField for
     * entering the password, setting its preferred width, font, and prompt text.
     *
     * The password field allows users to input their password securely.
     */
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

    /**
     * Configures the login button in the login interface.
     * It creates and configures a Button with the label "Ingresar," sets its preferred width,
     * font size, and adds margin. Additionally, it creates a VBox container for the button,
     * aligns it to the center, sets a cursor style to indicate it's clickable, and attaches
     * an action event handler to the button.
     *
     * The login button allows users to submit their login credentials.
     */
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

    /**
     * Configures the login container in the login interface.
     * It calls helper methods to set up the username field, password field, and login button.
     * Then, it creates a VBox container to hold the username field, password field,
     * and login button in a vertical layout. Margins and width are set for proper alignment.
     * The container is also assigned an ID for styling purposes.
     *
     * The login container organizes the UI elements related to user login.
     */
    private void settingLoginContainer(){
        this.settingUsernameField();
        this.settingPasswordField();
        this.settingLoginButton();
        this.loginContainer = new VBox(this.userNameTag, this.username, this.passwordTag, this.password, this.btnContainer);
        VBox.setMargin(loginContainer, new Insets(20,20,20,20));
        this.loginContainer.setPrefWidth(500);
        this.loginContainer.setId("loginContainer");
    }

    /**
     * Configures the error message container in the login interface.
     * It sets up a label to display error messages, adjusts its appearance,
     * and places it in a VBox container with specific properties such as ID, width,
     * visibility, alignment, and margins for proper display.
     *
     * The error container is used to show error messages to the user during login attempts.
     */
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

    /**
     * Configures the left container in the login interface which contains the login form and error message components.
     * It sets up the login container and the error message container as VBox children,
     * adjusts the appearance and alignment of the container, and assigns an ID for styling purposes.
     *
     * The left container includes the login form (username, password, and login button) and error messages to be displayed.
     */
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

    /**
     * Configures the image container in the login interface to display the UPTC logo.
     * It sets up a VBox to hold the logo image, adjusts its dimensions, and centers it within the container.
     *
     * The image container displays the UPTC logo to provide a visual element in the login interface.
     */
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
