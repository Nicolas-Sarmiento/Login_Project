package co.edu.uptc.view.forum;

import co.edu.uptc.view.LoginView;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.io.File;

public class ForumButton extends Button {

    private int index;
    private ContextMenu menu;
    private MenuItem deleteForum;
    public ForumButton(){
        super();
        this.getStylesheets().add( new File("./styles/ForumButton.css").toURI().toString());
    }
    public ForumButton(ForumView parent, String msg, int id){
        super(msg);
        this.index = id;
        this.menu = new ContextMenu();
        this.deleteForum = new MenuItem("Borrar Foro");
        this.menu.getItems().add(this.deleteForum);
        this.setContextMenu(this.menu);
        this.deleteForum.setOnAction(parent);
        this.deleteForum.setId(String.valueOf(id));
        this.getStylesheets().add( new File("./styles/forum/ForumButton.css").toURI().toString());
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
