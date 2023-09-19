package co.edu.uptc.view.forum;

import javafx.scene.control.Button;

import java.io.File;

public class ForumButton extends Button {

    private int index;
    public ForumButton(){
        super();
        this.getStylesheets().add( new File("./styles/ForumButton.css").toURI().toString());
    }
    public ForumButton(String msg, int id){
        super(msg);
        this.index = id;
        this.getStylesheets().add( new File("./styles/ForumButton.css").toURI().toString());
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
