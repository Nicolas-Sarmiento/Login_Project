package co.edu.uptc.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;

public class SingInView extends Header{
    private LoginView parent;
    private HBox nameField;
    private HBox lastNameField;
    private HBox idField;
    private HBox roleField;
    private Label nameError;
    private Label lastNameError;
    private TextField name;
    private TextField lastName;
    private TextField id;
    private ChoiceBox<String> roles;

    private Button summit;

    private static final String[] ROLES ={"Student", "Professor", "Secretary", "Administrator"};

    public SingInView(LoginView parent, Button btn){
        super(btn);
        this.parent = parent;
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

        VBox formContainer = new VBox(this.roleField,this.nameField, this.lastNameField, this.idField, this.summit);
        formContainer.setId("form");
        formContainer.setSpacing(10);
        formContainer.setAlignment(Pos.CENTER);
        VBox.setMargin(formContainer, new Insets( 10));
        VBox container = new VBox(formContainer);
        container.setId("main");
        container.setAlignment(Pos.CENTER);
        VBox root = new VBox(header, container);
        root.setId("root");
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/sing.css").toURI().toString());
        return scene;
    }

    private void settingNameField(){
        this.nameField = new HBox();
        this.nameField.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Nombres:");
        this.name = new TextField();
        this.name.setPromptText("Ej: Jhon");
        this.nameError = new Label("El nombre no puede contener caracteres especiales o números");
        this.nameError.setVisible(false);

        VBox labelContainer = new VBox(nameLabel);
        VBox inputContainer = new VBox(this.name, this.nameError);

        this.nameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    private void settingLastNameField(){
        this.lastNameField = new HBox();
        this.lastNameField.setAlignment(Pos.CENTER);
        Label lastNameLabel = new Label("Apellidos: ");
        this.lastName = new TextField();
        this.lastName.setPromptText("Ej: Doe");
        this.lastNameError = new Label("Los apellidos no puede contener caracteres especiales o números");
        this.lastNameError.setVisible(false);

        VBox labelContainer = new VBox(lastNameLabel);
        VBox inputContainer = new VBox(this.lastName, this.lastNameError);

        this.lastNameField.getChildren().addAll(labelContainer, inputContainer);
        HBox.setHgrow(inputContainer, Priority.ALWAYS);
    }

    private void settingIdField(){
        this.idField = new HBox();
        this.idField.setAlignment(Pos.CENTER);
        Label idLabel = new Label("Identificación");
        this.id = new TextField();
        this.id.setPromptText("Ej: 202210583");

        this.idField.getChildren().addAll(idLabel, this.id);
        HBox.setHgrow(this.id, Priority.ALWAYS);
    }

    private void settingRoleField( ){
        this.roleField = new HBox();
        this.roleField.setAlignment(Pos.CENTER);
        Label roleLabel = new Label("Rol: ");
        this.roles = new ChoiceBox<>();
        this.roles.getItems().addAll(ROLES);
        this.roles.setValue(ROLES[0]);

        this.roleField.getChildren().addAll(roleLabel, this.roles);
        HBox.setHgrow(this.roles, Priority.ALWAYS);
    }

    private void settingSummitButton(){
        this.summit = new Button("Registrar");
    }


}
