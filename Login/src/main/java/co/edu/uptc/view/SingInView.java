package co.edu.uptc.view;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SingInView extends Header{

    public Scene singIn(){
        VBox root = new VBox(this.getHeader());
        Scene scene = new Scene(root);
        return scene;
    }
}
