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
    private ChoiceBox choiceBox;
    public CreateForum(){

    }
    public CreateForum(ForumView forumView){
        this.forumView = forumView;
    }

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
    private void setChoiceBox(){
        List<Course> courses = forumView.getForumController().getCourses();
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

    public void setChoiceBox(ChoiceBox choiceBox) {
        this.choiceBox = choiceBox;
    }
}
