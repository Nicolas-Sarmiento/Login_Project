package co.edu.uptc.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
/**
 * The Header class represents a header layout for the application.
 * It includes a home button, user name, and an information option.
 */
public class Header {
    HBox header;
    VBox homeButtonContainer;
    Button home;
    HBox nameContainer;
    Label name;
    VBox optionInfoContainer;
    Label optionInfo;
    VBox Container;
    /**
     * Constructs a Header instance with a provided home button.
     *
     * @param button The home button for the header.
     */
    public Header(Button button) {
        this.home = button;
    }
    /**
     * Returns the header layout containing home button, user name, and information option.
     *
     * @return The header layout as an HBox.
     */
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
    /**
     * Sets up the home button and assigns an icon to it, then adds the button to a container.
     * The button is styled with a specific icon and properties, and it is placed within a container for the header.
     *
     * This method configures the appearance and behavior of the home button, including its graphic icon, ID, alignment,
     * and cursor style to indicate interactivity. The button is added to a VBox container, allowing it to grow horizontally.
     */
    private void settingHomeContainer(){
        this.homeButtonContainer = new VBox();
        ImageView homeIcon = new ImageView(new File("./imgs/home.png").toURI().toString());
        this.home.setGraphic(homeIcon);
        this.home.setId("home");
        this.home.setAlignment(Pos.CENTER_LEFT);
        this.homeButtonContainer.getChildren().add(this.home);
        this.homeButtonContainer.setAlignment(Pos.CENTER);
        this.home.setCursor(Cursor.HAND);
        HBox.setHgrow(this.homeButtonContainer, Priority.ALWAYS);
    }
    /**
     * Sets up the container for displaying the user's name and an icon representing the user.
     * The container includes an HBox with the user icon and a Label displaying the user's name.
     *
     * This method configures the appearance and layout of the container. It creates an ImageView to represent
     * the user icon, sets its dimensions, and creates a Label to display the user's name. The ImageView and Label
     * are added to an HBox container, which allows them to be horizontally aligned. The HBox is set to grow horizontally.
     */
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
    /**
     * Sets up the container for displaying additional information or options.
     * The container includes a VBox with a Label displaying the provided information or option.
     *
     * This method configures the appearance and layout of the container. It creates a Label to display
     * the provided information or option, adds it to a VBox container, and aligns the content to the center.
     * The VBox is set to grow vertically.
     */
    private void settingInfoContainer(){
        this.optionInfoContainer = new VBox();
        this.optionInfo = new Label("cambiar contraseña");
        this.optionInfo.setId("info");
        this.optionInfoContainer.getChildren().add(this.optionInfo);
        this.optionInfoContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(this.optionInfoContainer, Priority.ALWAYS);
    }
    /**
     * Sets the information option in the header.
     *
     * @param option The information option to set.
     */
    public void setOption(String option){

        this.optionInfo.setText(option);
    }
    /**
     * Sets the user name in the header.
     *
     * @param name The user name to set.
     */
    public void setName(String name){
        this.name.setText(name);
    }
    /**
     * Gets the home button from the header.
     *
     * @return The home button.
     */
    public Button getHome() {
        return home;
    }
}
