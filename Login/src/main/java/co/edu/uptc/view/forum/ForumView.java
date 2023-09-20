package co.edu.uptc.view.forum;

import co.edu.uptc.controller.ForumController;
import co.edu.uptc.view.Header;
import co.edu.uptc.view.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

public class ForumView extends Header  implements EventHandler<ActionEvent> {
    LoginView parent;
    ForumController forumController;
    Button add;
    VBox chatForum;
    VBox forumContent;
    Label forumName;
    VBox forums;
    HBox infoContainer;

    public ForumView(LoginView parent, Button home){
        super(home);
        this.parent = parent;
        this.forumController = new ForumController();

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
        VBox.setMargin(this.add, new Insets(0, 5, 0, 0));
        addContainer.getChildren().add(add);
        addContainer.setId("AddContainer");
        infoContainer.getChildren().add(addContainer);
    }

    public void settingForumContent(){
        this.forumContent = new VBox();
        this.forumContent.setId("ForumContent");
        HBox.setHgrow(this.forumContent, Priority.ALWAYS);
        VBox forumNameContainer = new VBox();
        this.forumName = new Label();
        forumNameContainer.setAlignment(Pos.CENTER);
        forumNameContainer.getChildren().add(forumName);

        this.forumContent.getChildren().add(forumNameContainer);

    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() instanceof ForumButton btn){
            this.forumName.setText(this.forumController.getForumTitle(btn.getIndex()));
        }
    }
}
