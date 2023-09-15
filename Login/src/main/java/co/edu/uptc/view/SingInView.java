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

    public SingInView(LoginView parent, Button btn){
        super(btn);
        this.parent = parent;
        this.util = new InputLibrary();
    }

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

    private void settingNameField(){
        this.nameField = new HBox();
        this.nameField.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Nombres");
        nameLabel.getStyleClass().add("tag");

        this.name = new TextField();
        this.name.setPromptText("Ej: Jhon");
        this.name.getStyleClass().add("input");
        this.name.setOnAction(this);

        this.nameError = new Label("El nombre no puede contener caracteres especiales ");
        this.nameError.getStyleClass().add("errorLabel");
        this.nameError.setVisible(false);
        this.nameError.setAlignment(Pos.BASELINE_RIGHT);

        VBox labelContainer = new VBox(nameLabel);
        VBox inputContainer = new VBox(this.name, this.nameError);
        inputContainer.setAlignment(Pos.CENTER);

        this.nameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    private void settingLastNameField(){
        this.lastNameField = new HBox();
        this.lastNameField.setAlignment(Pos.CENTER);

        Label lastNameLabel = new Label("Apellidos");
        lastNameLabel.getStyleClass().add("tag");

        this.lastName = new TextField();
        this.lastName.setPromptText("Doe");
        this.lastName.getStyleClass().add("input");
        this.lastName.setOnAction(this);

        this.lastNameError = new Label("Los apellidos no puede contener caracteres especiales o números");
        this.lastNameError.getStyleClass().add("errorLabel");
        this.lastNameError.setVisible(false);

        VBox labelContainer = new VBox(lastNameLabel);
        VBox inputContainer = new VBox(this.lastName, this.lastNameError);
        inputContainer.setAlignment(Pos.CENTER);

        this.lastNameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    private void settingIdField(){
        this.idField = new HBox();
        this.idField.setAlignment(Pos.CENTER);

        Label idLabel = new Label("Identificación");
        idLabel.getStyleClass().add("tag");

        this.id = new TextField();
        this.id.getStyleClass().add("input");
        this.id.setPromptText("Ej: 202210583");
        this.id.setOnAction(this);

        this.idError = new Label("sdf");
        this.idError.getStyleClass().add("errorLabel");
        this.idError.setVisible(false);
        VBox labelContainer = new VBox(idLabel);
        VBox inputContainer = new VBox(this.id, this.idError);

        inputContainer.setAlignment(Pos.CENTER);
        this.idField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

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

    private void settingSummitButton(){
        this.summit = new Button("Registrar");
        this.summit.setId("summit");
        this.summit.setCursor(Cursor.HAND);
        this.summit.setOnAction(this);
        VBox.setMargin(this.summit, new Insets(20, 0, 0 ,0));
    }

    private void settingMessage(){
        this.messageContainer = new VBox();
        this.messageContainer.setId("messageContainer");
        this.message = new Label("Error!");
        this.message.setId("message");
        this.messageContainer.setAlignment(Pos.CENTER);
        this.messageContainer.getChildren().add(this.message);
        this.messageContainer.setVisible(false);
    }

    public boolean validateNames(String str){
        return  !str.isBlank() && !this.util.containsNums(str)  && !this.util.containSpecialCharactersNums(str) && !str.startsWith(" ");
    }

    public boolean validateId(String str){
        return  !str.isBlank() && !this.util.containSpecialCharactersNums(str) && !str.contains(" ");
    }
    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == this.summit){
            if (this.validateNames(this.name.getText()) && this.validateNames(this.lastName.getText()) && this.validateId(this.id.getText())){
                boolean respone = this.parent.controller.signin(this.name.getText(),this.lastName.getText(), this.id.getId(), this.roles.getValue());
                if (respone){
                    this.message.setText("Añadido con éxito!");
                }else{
                    this.message.setText("Ha ocurrido un error!");
                }
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
    }
}
