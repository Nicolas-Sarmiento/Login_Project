package co.edu.uptc.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class Header {
    HBox header;
    VBox homeButtonContainer;
    Button home;
    HBox nameContainer;
    Label name;
    VBox optionInfoContainer;
    Label optionInfo;
    VBox Container;


    public HBox getHeader(){

        this.settingHomeContainer();
        this.settingNameContainer();
        this.settingInfoContainer();


        this.header = new HBox();
        this.header.getChildren().addAll(this.homeButtonContainer, this.optionInfoContainer, this.nameContainer);
        this.header.setId("header");
        this.header.getStylesheets().add(new File("./styles/header.css").toURI().toString());
        this.header.setAlignment(Pos.CENTER);

        return this.header;
    }

    private void settingHomeContainer(){
        this.homeButtonContainer = new VBox();
        ImageView homeIcon = new ImageView(new File("./imgs/home.png").toURI().toString());
        this.home = new Button("",homeIcon);
        this.home.setId("home");
        this.home.setAlignment(Pos.CENTER_LEFT);
        this.homeButtonContainer.getChildren().add(this.home);
        this.homeButtonContainer.setAlignment(Pos.CENTER);
        this.home.setCursor(Cursor.HAND);
        HBox.setHgrow(this.homeButtonContainer, Priority.ALWAYS);
    }

    private void settingNameContainer(){
        ImageView userIcon = new ImageView(new File("./imgs/user.png").toURI().toString());
        userIcon.setFitHeight(20);
        userIcon.setFitWidth(20);

        this.nameContainer = new HBox();
        this.name = new Label("Nino");
        this.name.setId("name");

        this.nameContainer.getChildren().addAll(userIcon, this.name);
        this.nameContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(this.nameContainer, Priority.ALWAYS);
    }
    private void settingInfoContainer(){
        this.optionInfoContainer = new VBox();
        this.optionInfo = new Label("cambiar contraseña");
        this.optionInfo.setId("info");
        this.optionInfo.setAlignment(Pos.CENTER);
        this.optionInfo.setMaxWidth(Double.MAX_VALUE);
        this.optionInfoContainer.getChildren().add(this.optionInfo);
        this.optionInfoContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(this.optionInfoContainer, Priority.ALWAYS);
    }

    public void setOption(String option){
        this.optionInfo.setText(option);
    }

    public void setName(String name){
        this.name.setText(name);
    }

    public Button getHome() {
        return home;
    }
}
