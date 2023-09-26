package co.edu.uptc.view.forum;

import co.edu.uptc.controller.ForumController;
import co.edu.uptc.model.Answer;
import co.edu.uptc.model.Forum;
import co.edu.uptc.view.Header;
import co.edu.uptc.view.LoginView;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ForumView extends Header  implements EventHandler<ActionEvent> {
    LoginView parent;
    ForumController forumController;
    Button add;
    VBox chatForum;
    VBox forumContent;
    Label forumName;
    VBox forums;
    VBox forumNameContainer;
    HBox infoContainer;
    TextArea textArea;
    Button buttonSend;
    ArrayList<Label> msg;
    Forum forumAux;
    HBox contetBoxText;
    VBox contentMsg;
    ScrollPane scrollPaneMsg;
    CreateForum createForum;
    StackPane stackPane;
    VBox sectionWrite;


    public ForumView(LoginView parent, Button home){
        super(home);
        this.parent = parent;
        this.forumController = new ForumController();

        this.msg = new ArrayList<>();
        forumAux = new Forum();

        this.createForum = new CreateForum(this);


    }

    public Scene Forum(){
        VBox root = new VBox();
        HBox forumContainer = new HBox();
        VBox.setVgrow(forumContainer, Priority.ALWAYS);
        this.settingForumContent();
        this.settingForumsContainer();
        forumContainer.getChildren().addAll(chatForum, forumContent);

        HBox header = this.getHeader();
        this.setOption("Foros");
        this.setName(this.parent.getController().getName());
        root.getChildren().addAll(header, forumContainer);
        root.setSpacing(10);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/forum/Forum.css").toURI().toString());
        return scene;
    }

    public void settingForumsContainer(){
        this.forums = new VBox();
        this.settingForumsButtons();

        ScrollPane sp = new ScrollPane(forums);
        sp.setFitToWidth(true);

        this.settingInfoContainer();

        this.chatForum = new VBox(this.infoContainer,sp);
        chatForum.setSpacing(10);
        VBox.setVgrow(sp, Priority.ALWAYS);
        chatForum.setId("ChatContainer");
    }

    private void settingForumsButtons(){
        for (int i = 0; i < this.forumController.getForumsLen(); i++){
            ForumButton btn = new ForumButton(this.forumController.getForumTitle(i), i);
            String color = "";
            if (i % 2 == 0){
                btn.getStyleClass().add("first");
            }else {
                btn.getStyleClass().add("second");
            }
            btn.setOnAction(this);
            VBox.setMargin(btn, new Insets(5, 0, 5, 0));
            this.forums.getChildren().add(btn);
        }
    }

    private void settingInfoContainer(){
        this.infoContainer = new HBox();
        infoContainer.setId("Info");
        infoContainer.setAlignment(Pos.CENTER);
        Label infoForum = new Label("Tus Foros");
        VBox labelContainer = new VBox(infoForum);
        labelContainer.setAlignment(Pos.CENTER);
        labelContainer.setId("InfoLabel");

        this.infoContainer.getChildren().add(labelContainer);

        if (!this.parent.getController().showRol().equals("ADMINISTRATOR")) return;

        VBox addContainer = new VBox();
        ImageView addIcon = new ImageView(new File("./imgs/add.png").toURI().toString());
        this.add = new Button("Nuevo", addIcon);
        this.add.setId("add");
        this.add.setOnAction(this);
        VBox.setMargin(this.add, new Insets(0, 5, 0, 0));
        addContainer.getChildren().add(add);
        addContainer.setId("AddContainer");
        infoContainer.getChildren().addAll(addContainer);
    }

    /**
     * Sets up the content layout for a forum interface, which includes a forum name,
     * a message display area with scrolling functionality, a text input box for adding comments,
     * and other associated UI components.
     *
     * This method initializes and configures a VBox ('forumContent') as the main container
     * for the forum content. It sets an identifier ("ForumContent") for styling purposes.
     * The 'scrollPaneMsg' is initially set to be invisible, and appropriate growth priorities
     * are set for various UI components to ensure proper layout behavior.
     */
    public void settingForumContent(){
        forumContent = new VBox();
        this.forumContent.setId("ForumContent");
        HBox.setHgrow(this.forumContent, Priority.ALWAYS);

        contentMsg = new VBox();
        VBox.setVgrow(contentMsg, Priority.ALWAYS);
        forumNameContainer = new VBox();
        this.forumName = new Label();

        forumNameContainer.setAlignment(Pos.CENTER);
        forumNameContainer.getChildren().add(forumName);

        scrollPaneMsg = new ScrollPane(contentMsg);
        scrollPaneMsg.setFitToWidth(true);
        scrollPaneMsg.setVisible(false);
        VBox.setVgrow(scrollPaneMsg, Priority.ALWAYS);

        settingBoxText();

        this.sectionWrite = new VBox();
        this.sectionWrite.getChildren().addAll(forumNameContainer, scrollPaneMsg,contetBoxText);

        this.stackPane = new StackPane();
        stackPane.setId("stackPane");
        this.stackPane.getChildren().addAll(this.sectionWrite, this.createForum.principalCreateForum());
        VBox.setVgrow(stackPane, Priority.ALWAYS);
        this.forumContent.getChildren().add(stackPane);
    }

    /**
     * Populates a UI container with messages from a logged forum, including user names
     * and their corresponding messages. This method checks if there are any answers
     * in the logged forum and displays them in a formatted manner.
     *
     * If there are answers in the forum, it iterates through each answer, retrieves the
     * user's name and message, and adds them as Label components to the 'contentMsg'
     * UI container. Each user's name is given the identifier "UserName," and each message
     * is given the identifier "Msg."
     *
     * If there are no answers in the forum, the 'contentMsg' UI container is cleared.
     */
    private void settingSpaceMsg() {
        if(this.forumController.getLoggedForum().getAnswerForum().size() > 0){

            for (int i = 0; i < this.forumController.getLoggedForum().getAnswerForum().size(); i++) {
                Label userMsg = new Label(this.forumController.getLoggedForum().getAnswerForum().get(i).getPerson().getName() + " " + this.forumController.getLoggedForum().getAnswerForum().get(i).getPerson().getLastname());
                userMsg.setId("UserName");
                Label textMsg = new Label(this.forumController.getLoggedForum().getAnswerForum().get(i).getAnwers());
                textMsg.setId("Msg");
                msg.add(userMsg);
                msg.add(textMsg);
            }

            for (int i = 0; i < msg.size(); i++) {
                contentMsg.getChildren().add(msg.get(i));
            }
        }else {
            contentMsg.getChildren().clear();
        }
    }

    /**
     * Initializes and configures the user interface components for entering comments
     * within a content box, including a TextArea for text input and a Button for sending comments.
     *
     * This method sets up a horizontal box (HBox) containing the TextArea and the Button.
     * The TextArea is configured with a prompt text and a preferred row count of 2.
     * The Button is configured with an image icon for sending, and its action is handled by
     * the current class.
     *
     * The HBox is given an identifier ("BoxText") and initially set to be invisible.
     */
    private void settingBoxText() {
        this.contetBoxText = new HBox();

        this.textArea = new TextArea();
        textArea.setPromptText("Escribe aquí tus comentarios");
        textArea.setPrefRowCount(2);

        ImageView addIcon = new ImageView(new File("./imgs/send.png").toURI().toString());
        this.buttonSend = new Button("", addIcon);;
        this.buttonSend.setId("send");
        this.buttonSend.setOnAction(this);

        HBox.setHgrow(textArea, Priority.ALWAYS);
        this.contetBoxText.getChildren().addAll(textArea, buttonSend);
        this.contetBoxText.setId("BoxText");
        this.contetBoxText.setVisible(false);
    }

    @Override
    public void handle(ActionEvent e) {

        if (e.getSource() instanceof ForumButton btn){
            this.createForum.getContainerCreate().setVisible(false);

            this.sectionWrite.setVisible(true);
            this.forumNameContainer.setVisible(true);
            this.scrollPaneMsg.setVisible(true);
            this.contetBoxText.setVisible(true);
            this.forumController.selectForum((btn.getIndex()));
            this.forumName.setText(this.forumController.getForumTitle(btn.getIndex()));
            contentMsg.getChildren().clear();

            if (forumAux == null){
                settingSpaceMsg();
                forumAux = this.forumController.getLoggedForum();
            }else {
                msg.clear();
                if (!this.forumAux.equals(this.forumController.getLoggedForum())) {
                    forumAux = this.forumController.getLoggedForum();
                    settingSpaceMsg();
                }else {
                    settingSpaceMsg();
                }
            }
        }

        if(e.getSource() == buttonSend){
            if(!textArea.getText().isEmpty()){
                this.forumController.addComment(textArea.getText(), parent.getController().getLoggedPerson());
                this.contentMsg.getChildren().clear();
                this.msg.clear();
                settingSpaceMsg();
                textArea.clear();
            }
        }

        if(e.getSource() == this.add){
            //forumNameContainer, scrollPaneMsg,contetBoxText
            createForum.getContainerCreate().setVisible(true);
            this.sectionWrite.setVisible(false);
            this.scrollPaneMsg.setVisible(false);
            this.contetBoxText.setVisible(false);
            this.forumNameContainer.setVisible(false);
        }
        if(e.getSource() == this.createForum.getSearchInfo()){
            String nameForum = createForum.getNameForumField().getText();
            String descriptionForum = createForum.getDecriptionForumField().getText();

            if(!this.forumController.checkIfItExist(nameForum)){
                if(forumController.createdForum(nameForum, descriptionForum, "")){
                    this.createForum.msgGeneralM("0");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                createForum.getContainerCreate().setVisible(false);
                            });
                        }
                    }, 3000);
                }
            }else{
                this.createForum.msgGeneralM("3");
            }
        }
    }
}
