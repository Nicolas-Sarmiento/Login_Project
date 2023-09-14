package co.edu.uptc.view;

import co.edu.uptc.model.Account;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class LoginListUsers {
    LoginView parent;
    //LoginTemplate loginTemplate;
    VBox container;
    BorderPane borderPane;
    TableView table;
    TableColumn idColumn;
    TableColumn userNameColumn;
    TableColumn passwordColumn;
    TableColumn roleColumn;
    TableColumn emailColumn;

    public LoginListUsers(LoginView loginView) {
        this.parent = loginView;
        //loginTemplate = new LoginTemplate();
        borderPane = new BorderPane();
        table = new TableView<Account>();
        creationColumns();
        addingRegisters();
    }

    public Scene loginListUsers(){
        borderPane.setCenter(table);
//        HBox root = new HBox(borderPane);
        //root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(borderPane, 1000, 600);
        return scene;
    }

    private void creationColumns() {
        idColumn = new TableColumn<Account, String> ("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("id"));

        userNameColumn = new TableColumn<Account, String> ("User Name");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));

        passwordColumn = new TableColumn<Account, String> ("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));

        roleColumn = new TableColumn<Account, String> ("Rol");
        roleColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("role"));

        emailColumn = new TableColumn<Account, String> ("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));

        table.getColumns().add(idColumn);
        table.getColumns().add(userNameColumn);
        table.getColumns().add(passwordColumn);
        table.getColumns().add(roleColumn);
        table.getColumns().add(emailColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    private void addingRegisters(){
        for (Account account: parent.controller.getPersonController().getAccounts()) {
            table.getItems().add(new Account(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getEmail()));
        }
    }

}
