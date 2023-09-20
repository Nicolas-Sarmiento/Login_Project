package co.edu.uptc.view.forum;

import co.edu.uptc.controller.ForumController;
import co.edu.uptc.view.Header;
import co.edu.uptc.view.LoginView;
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

public class ForumView extends Header {
    LoginView parent;
    ForumController forumController;
    public ForumView(LoginView parent, Button home){
        super(home);
        this.parent = parent;
        this.forumController = new ForumController();

    }
    public Scene Forum(){
        VBox root = new VBox();
        HBox forumContainer = new HBox();
        forumContainer.setId("ForumContainer");
        VBox.setVgrow(forumContainer, Priority.ALWAYS);
        VBox forums = new VBox();
        VBox forumContent = new VBox();

        for (int i = 0; i < this.forumController.getForumsLen(); i++){
            ForumButton btn = new ForumButton(this.forumController.getForumTitle(i), i);
            forums.getChildren().add(btn);
        }
        ScrollPane sp = new ScrollPane(forums);
        sp.setFitToWidth(true);


        HBox infoContainer = new HBox();
        infoContainer.setId("Info");
        Label infoForum = new Label("Tus Foros");
        VBox labelContainer = new VBox(infoForum);
        labelContainer.setAlignment(Pos.CENTER);
        labelContainer.setId("InfoLabel");
        VBox addContainer = new VBox();
        ImageView addIcon = new ImageView(new File("./imgs/add.png").toURI().toString());
        Button add = new Button("Nuevo", addIcon);
        addContainer.getChildren().add(add);
        addContainer.setId("AddContainer");
        infoContainer.getChildren().addAll(labelContainer, addContainer);
        VBox spContainer = new VBox(infoContainer,sp);
        VBox.setVgrow(sp, Priority.ALWAYS);
        spContainer.setId("ChatContainer");
        forumContainer.getChildren().addAll(spContainer, forumContent);

        HBox header = this.getHeader();
        this.setOption("Foros");
        this.setName(this.parent.getController().getName());
        root.getChildren().addAll(header, forumContainer);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(new File("./styles/forum/Forum.css").toURI().toString());
        return scene;
    }


}
