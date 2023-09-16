package co.edu.uptc.view;

import co.edu.uptc.utilities.InputLibrary;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;
/**
 * The SingInView class represents the view for user registration.
 * It extends the Header class and implements the EventHandler interface for handling events.
 */
public class SingInView extends Header implements EventHandler<ActionEvent> {
    private InputLibrary util;
    private LoginView parent;
    private HBox nameField;
    private HBox lastNameField;
    private HBox idField;
    private HBox roleField;
    private Label nameError;
    private Label lastNameError;
    private Label idError;
    private TextField name;
    private TextField lastName;
    private TextField id;
    private ChoiceBox<String> roles;

    private VBox messageContainer;
    private Label message;

    private Button summit;

    private static final String[] ROLES ={"Student", "Professor", "Secretary", "Administrator"};
    private Label idLabel;

    /**
     * Constructs a SingInView instance.
     *
     * @param parent The parent LoginView instance.
     * @param btn    The Button instance to be set as home button in the header.
     */
    public SingInView(LoginView parent, Button btn){
        super(btn);
        this.parent = parent;
        this.util = new InputLibrary();
    }

    /**
     * Creates the scene for user registration.
     *
     * @return The Scene for user registration.
     */
    public Scene singIn(){
        HBox header = this.getHeader();
        this.setName(this.parent.controller.getName());
        this.setOption("Crear Usuario");

        this.settingNameField();
        this.settingLastNameField();
        this.settingIdField();
        this.settingRoleField();
        this.settingSummitButton();
        this.settingMessage();

        VBox formContainer = new VBox(this.roleField,this.nameField, this.lastNameField, this.idField, this.summit);
        formContainer.setId("form");
        formContainer.setSpacing(10);
        formContainer.setAlignment(Pos.CENTER);
        VBox.setMargin(formContainer, new Insets( 10));
        VBox container = new VBox(formContainer,this.messageContainer);
        container.setId("main");
        container.setAlignment(Pos.CENTER);
        VBox root = new VBox(header, container);
        root.setId("root");
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/signin.css").toURI().toString());
        return scene;
    }

    /**
     * Sets up the UI elements for entering the user's first name.
     * Configures a label for the field, a text input for the first name,
     * and an error label to display validation messages.
     */
    private void settingNameField(){
        this.nameField = new HBox();
        this.nameField.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Nombres");
        nameLabel.getStyleClass().add("tag");

        this.name = new TextField();
        this.name.setPromptText("Jhon");
        this.name.getStyleClass().add("input");
        this.name.textProperty().addListener(((observable, oldValue, newValue) -> {
            validateNumbers(this.name, this.nameError, newValue);
        }));


        this.nameError = new Label("");
        this.nameError.getStyleClass().add("errorLabel");
        this.nameError.setVisible(false);
        this.nameError.setAlignment(Pos.BASELINE_RIGHT);

        VBox labelContainer = new VBox(nameLabel);
        VBox inputContainer = new VBox(this.name, this.nameError);
        inputContainer.setAlignment(Pos.CENTER);

        this.nameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    /**
     * Sets up the UI elements for entering the user's last name.
     * Configures a label for the field, a text input for the last name,
     * and an error label to display validation messages.
     */
    private void settingLastNameField(){
        this.lastNameField = new HBox();
        this.lastNameField.setAlignment(Pos.CENTER);

        Label lastNameLabel = new Label("Apellidos");
        lastNameLabel.getStyleClass().add("tag");

        this.lastName = new TextField();
        this.lastName.setPromptText("Doe");
        this.lastName.getStyleClass().add("input");
        this.lastName.textProperty().addListener(((observable, oldValue, newValue) -> {
            validateNumbers(this.lastName, this.lastNameError, newValue);
        }));;

        this.lastNameError = new Label("");
        this.lastNameError.getStyleClass().add("errorLabel");
        this.lastNameError.setVisible(false);

        VBox labelContainer = new VBox(lastNameLabel);
        VBox inputContainer = new VBox(this.lastName, this.lastNameError);
        inputContainer.setAlignment(Pos.CENTER);

        this.lastNameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    /**
     * Sets up the UI elements for entering the user's identification.
     * Configures a label for the field, a text input for the identification,
     * and an error label to display validation messages.
     */
    private void settingIdField(){
        this.idField = new HBox();
        this.idField.setAlignment(Pos.CENTER);

        this.idLabel = new Label();
        this.idLabel.setText("código");
        this.idLabel.getStyleClass().add("tag");

        this.id = new TextField();
        this.id.getStyleClass().add("input");
        this.id.setPromptText("Ej: 202210583");
        this.id.textProperty().addListener(((observable, oldValue, newValue) -> {
            validateIdStyle(this.id, this.idError, newValue);
        }));;

        this.idError = new Label();
        this.idError.getStyleClass().add("errorLabel");
        this.idError.setVisible(false);
        VBox labelContainer = new VBox(idLabel);
        VBox inputContainer = new VBox(this.id, this.idError);

        inputContainer.setAlignment(Pos.CENTER);
        this.idField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    /**
     * Sets up the UI elements for selecting a role.
     * Configures a label for the role selection, a choice box to select a role,
     * and sets up event handling for role selection changes.
     */
    private void settingRoleField( ){
        this.roleField = new HBox();
        this.roleField.setAlignment(Pos.CENTER);
        Label roleLabel = new Label("Rol");
        roleLabel.getStyleClass().add("tag");

        this.roles = new ChoiceBox<>();
        this.roles.getStyleClass().add("input");
        this.roles.getItems().addAll(ROLES);
        this.roles.setValue(ROLES[0]);
        this.roles.setOnAction(this);
        VBox labelContainer = new VBox(roleLabel);
        VBox inputContainer = new VBox(this.roles);
        VBox.setMargin(this.roles, new Insets(0,0,10,0));
        inputContainer.setAlignment(Pos.CENTER);

        this.roleField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    /**
     * Configures the "Registrar" button for user registration.
     * Sets the button label, CSS ID, cursor style, and event handling for button clicks.
     */
    private void settingSummitButton(){
        this.summit = new Button("Registrar");
        this.summit.setId("summit");
        this.summit.setCursor(Cursor.HAND);
        this.summit.setOnAction(this);
        VBox.setMargin(this.summit, new Insets(20, 0, 0 ,0));
    }

    /**
     * Configures the message display container and label for displaying status messages.
     * Sets the initial message, container ID for styling, alignment, and visibility.
     */
    private void settingMessage(){
        this.messageContainer = new VBox();
        this.messageContainer.setId("messageContainer");
        this.message = new Label("Error!");
        this.message.setId("message");
        this.messageContainer.setAlignment(Pos.CENTER);
        this.messageContainer.getChildren().add(this.message);
        this.messageContainer.setVisible(false);
    }

    /**
     * Validates the input style and displays appropriate error messages for the ID field.
     *
     * @param ob    The TextField for the ID
     * @param error The Label to display error messages
     * @param value The input value to be validated
     */
    private void validateIdStyle(TextField ob, Label error, String value){
        if (value.isBlank() ||  this.util.containSpecialCharactersId(value)){
            ob.getStyleClass().add("errorInput");
            error.setVisible(true);
        }

        if (value.isBlank()){
            if (!error.getText().contains(" Obligatorio*")){
                error.setText(error.getText() + " Obligatorio*");
            }
        }

        if ( this.util.containSpecialCharactersId(value)){
            if (!error.getText().contains(" Sin caracteres especiales")){
                error.setText(error.getText() + " Sin caracteres especiales");
            }
        }

        if (!this.util.containSpecialCharactersId(ob.getText()) && !ob.getText().isBlank()){
            error.setText("");
            ob.getStyleClass().remove("errorInput");
        }
    }

    /**
     * Validates the input style and displays appropriate error messages for fields that should not contain numbers.
     *
     * @param ob    The TextField for which the validation is performed
     * @param error The Label to display error messages
     * @param value The input value to be validated
     */
    private void validateNumbers(TextField ob, Label error, String value){
        if (this.util.containsNums(ob.getText()) || this.util.containSpecialCharactersNums(value) || value.isBlank() || value.startsWith(" ")){
            if (!ob.getStyleClass().contains("errorInput")){
                ob.getStyleClass().add("errorInput");
            }
            error.setVisible(true);

            if (value.isBlank()){
                if (!error.getText().contains(" Obligatorio*")){
                    error.setText(error.getText() + " Obligatorio*");
                }
            }
            if (value.startsWith(" ")){
                if (!error.getText().contains("No espacios iniciales.")){
                    error.setText(error.getText() + "No espacios iniciales.");
                }
            }
            if (this.util.containsNums(value)){
                if (!error.getText().contains(" Sin Números")){
                    error.setText(error.getText() + " Sin Números");
                }
            }

            if ( this.util.containSpecialCharactersNums(value)){
                if (!error.getText().contains(" Sin caracteres especiales")){
                    error.setText(error.getText() + " Sin caracteres especiales");
                }
            }
        }

        if (!this.util.containsNums(ob.getText()) && !this.util.containSpecialCharactersNums(ob.getText()) && !ob.getText().isBlank() && !value.startsWith(" ")){
            error.setText("");
            ob.getStyleClass().remove("errorInput");
        }
    }

    /**
     * Validates a string to ensure it does not contain numbers, special characters, is not blank, and does not start with a space.
     *
     * @param str The string to be validated
     * @return True if the string is valid (meets the specified criteria), false otherwise
     */
    public boolean validateNames(String str){
        return  !str.isBlank() && !this.util.containsNums(str)  && !this.util.containSpecialCharactersNums(str) && !str.startsWith(" ");
    }

    /**
     * Validates an identification string to ensure it is not blank, does not contain special characters, and does not contain spaces.
     *
     * @param str The identification string to be validated
     * @return True if the string is valid (meets the specified criteria), false otherwise
     */
    public boolean validateId(String str){
        return  !str.isBlank() && !this.util.containSpecialCharactersId(str) && !str.contains(" ");
    }

    /**
     * Event handler for handling actions triggered by the user.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == this.summit){
            this.roles.requestFocus();
            if (this.validateNames(this.name.getText()) && this.validateNames(this.lastName.getText()) && this.validateId(this.id.getText())){
                boolean respone = this.parent.controller.signin(this.name.getText(),this.lastName.getText(), this.id.getText(), this.roles.getValue());
                if (respone){
                    this.parent.loginListUsers.addAccount(this.parent.controller.getPersonController().findPersonById(this.id.getText()).getAccount());
                    this.message.setText("Añadido con éxito!");
                }else{
                    this.message.setText("Ha ocurrido un error!");
                }
                this.messageContainer.setVisible(true);
            }else {
                this.message.setText("Los nombres no deben contener espacios al iniciar\n números o caracteres especiales.");
                this.messageContainer.setVisible(true);
            }


            if (this.name.getText().isBlank()){
                this.nameError.setText("Obligatorio*");
                this.nameError.setVisible(true);
            }
            if (this.lastName.getText().isBlank()){
                this.lastNameError.setText("Obligatorio*");
                this.lastNameError.setVisible(true);
            }
            if (this.id.getText().isBlank()){
                this.idError.setText("Obligatorio*");
                this.idError.setVisible(true);
            }
        }
        if (e.getSource() == this.roles){
            if (!this.roles.getValue().equals(ROLES[0])){
                this.idLabel.setText("Identificación");
                this.id.setPromptText("1053893289");
            }else {
                this.idLabel.setText("código");
                this.id.setPromptText("202216034");
            }
        }
    }
}
