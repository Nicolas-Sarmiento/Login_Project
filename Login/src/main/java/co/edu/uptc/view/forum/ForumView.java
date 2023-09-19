package co.edu.uptc.view.forum;

import co.edu.uptc.view.Header;
import co.edu.uptc.view.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ForumView extends Header {
    LoginView parent;
    public ForumView(LoginView parent, Button home){
        super(home);
        this.parent = parent;
    }
    public Scene Forum(){
        VBox root = new VBox();
        HBox forumContainer = new HBox();
        VBox forums = new VBox();
        VBox forumContent = new VBox();

        for (int i = 0; i < 50; i++){
            Button btn = new Button("foro" + i);
            btn.setPrefWidth(150);
            forums.getChildren().add(btn);
        }

        ScrollPane sp = new ScrollPane(forums);
        forums.setPrefWidth(150);
        sp.setFitToWidth(true);
        forumContainer.getChildren().addAll(sp, forumContent);

        HBox header = this.getHeader();
        this.setOption("Foros");
        this.setName(this.parent.getController().getName());
        root.getChildren().addAll(header, forumContainer);
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
