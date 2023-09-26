package co.edu.uptc.view.forum;

import co.edu.uptc.model.Course;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * The CreateForum class represents a user interface for creating a new forum in the application.
 * It allows the user to input the necessary details for forum creation, such as name and description,
 * and associates the forum with a specific course.
 */
public class CreateForum {
    private Label nameForum;
    private Label descriptionForum;
    private Label msgGeneral;
    private Label msgCourse;
    private TextField nameForumField;
    private TextArea decriptionForumField;
    private VBox containerCreate;
    private VBox containerMsg;
    private Button searchInfo;
    private ForumView forumView;
    private ChoiceBox<String> choiceBox;
    private String idG = "";

    /**
     * Constructor for the CreateForum class.
     */
    public CreateForum(){

    }
    /**
     * Constructor for the CreateForum class that associates it with a ForumView.
     *
     * @param forumView The ForumView to associate with this CreateForum instance.
     */
    public CreateForum(ForumView forumView){
        this.forumView = forumView;
    }

    /**
     * Creates the main user interface for forum creation, including input fields and controls.
     *
     * @return The VBox containing the forum creation interface.
     */
    public VBox principalCreateForum(){
        this.fieldControllGeneral();
        this.setChoiceBox();
        this.msgCourse = new Label("Por favor selecciona a que curso petenecera el foro");
        msgCourse.setId("msgCourse");
        this.msgGeneral = new Label();
        msgGeneral.setId("msg");
        this.containerMsg = new VBox();
        containerMsg.setId("containerMsg");
        this.nameForum = new Label("Digita el nombre del nuevo foro");
        nameForum.setId("name");
        this.descriptionForum = new Label("Digita la descripcion del foro");
        descriptionForum.setId("description");
        this.searchInfo = new Button("Crear");
        searchInfo.setOnAction(this.forumView);
        searchInfo.setId("search");
        searchInfo.setFont(new Font(14));

        searchInfo.setOnAction(event -> {
            if (nameForumField.getText().isEmpty() || decriptionForumField.getText().isEmpty()) {
                msgGeneralM("1");
                return;
            } else if (nameForumField.getText().length() > 34) {
                msgGeneralM("2");
                return;
            } else {
                forumView.handle(new ActionEvent(searchInfo, searchInfo));
            }
        });

        containerMsg.getChildren().addAll(msgGeneral);
        containerMsg.setVisible(false);

        this.containerCreate = new VBox();
        containerCreate.getChildren().addAll(this.msgCourse,this.choiceBox, this.nameForum, this.nameForumField,this.descriptionForum, this.decriptionForumField, this.searchInfo, this.containerMsg);
        containerCreate.setAlignment(Pos.CENTER);
        containerCreate.setVisible(false);
        containerCreate.setId("general");
        this.containerCreate.getStylesheets().add(new File("./styles/forum/CreateForum.css").toURI().toString());
        return containerCreate;
    }
    /**
     * Sets up the ChoiceBox to display available courses for forum association.
     */
    private void setChoiceBox(){
        List<Course> courses = forumView.forumController.getCoursesByProfessorId(forumView.getLoggedId());
        List<String> coursesNames = courses.stream().map(Course::getName).collect(Collectors.toList());
        this.choiceBox = new ChoiceBox();
        choiceBox.setId("choiceBox");
        try {
            choiceBox.setValue(coursesNames.get(0));
            choiceBox.getItems().addAll(coursesNames);
        }catch (IndexOutOfBoundsException e){
            choiceBox.setValue("No tienes cursos");
        }
    }
    /**
     * Displays appropriate messages based on given error codes.
     *
     * @param errorCode The error code specifying the type of message to display.
     */
    public void msgGeneralM(String u){

        String errorVoid = "Tienes casillas vacias\nPor favor llena las casillas";
        String errorExtension = "El nombre del foro es demasiado largo\nSe mas breve";
        String errorEquals = "Ya existe un foro con este titulo\nPara evitar confusiones\nusa titulos diferentes";
        String correct = "Foro creado correctamente";

        switch (u){
            case "1":
                msgGeneral.setText(errorVoid);
                containerMsg.setVisible(true);
                break;
            case "2":
                msgGeneral.setText(errorExtension);
                containerMsg.setVisible(true);
                break;
            case "3":
                msgGeneral.setText(errorEquals);
                containerMsg.setVisible(true);
                break;
            case "0":
                msgGeneral.setText(correct);
                containerMsg.setVisible(true);
                break;
        }
    }
    /**
     * Sets up and configures the input fields for forum creation and controls for empty input.
     */
    public void fieldControllGeneral(){
        this.nameForumField = new TextField();
        nameForumField.setId("nameForum");
        nameForumField.setMaxWidth(200);
        this.decriptionForumField = new TextArea();
        decriptionForumField.setId("descriptionForum");
        decriptionForumField.setMaxWidth(200);
        decriptionForumField.setMaxHeight(100);
        decriptionForumField.setWrapText(true);

        nameForumField.textProperty().addListener((observable, oldValue, newValue) -> {
            controlVoid();
        });
        decriptionForumField.textProperty().addListener((observable, oldValue, newValue) -> {
            controlVoid();
        });
    }
    /**
     * Controls the appearance of input fields based on whether they are empty or not.
     */
    public void controlVoid() {
        String nameIsNull = nameForumField.getText();
        String descriptionIsNull = decriptionForumField.getText();

        if(nameIsNull.equals("")){
            nameForumField.setStyle("-fx-border-color: red");
        }else if(descriptionIsNull.equals("")){
            decriptionForumField.setStyle("-fx-border-color: red");
        }else{
            nameForumField.setStyle("-fx-border-color: green");
            decriptionForumField.setStyle("-fx-border-color: green");
            searchInfo.setVisible(true);
        }
    }

    public TextField getNameForumField() {
        return nameForumField;
    }

    public TextArea getDecriptionForumField() {
        return decriptionForumField;
    }

    public Button getSearchInfo() {
        return searchInfo;
    }

    public VBox getContainerCreate() {
        return containerCreate;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

}
