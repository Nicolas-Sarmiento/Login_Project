package co.edu.uptc.view;

import co.edu.uptc.model.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LoginListUsers extends Header{
    LoginView parent;
    BorderPane borderPane;
    TableView table;
    TableColumn idColumn;
    TableColumn userNameColumn;
    TableColumn passwordColumn;
    TableColumn roleColumn;
    TableColumn emailColumn;

    public LoginListUsers(LoginView loginView, Button home) {
        super(home);
        this.parent = loginView;
        borderPane = new BorderPane();
        table = new TableView<Account>();
        addingRegisters();
        creationColumns();
    }

    public Scene loginListUsers(){

        borderPane.setCenter(table);
        HBox header = this.getHeader();
        this.setOption("Ver cuentas");
        this.setName(parent.controller.getName());
        VBox root = new VBox(header, borderPane);
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    public void addAccount(Account account){
        table.getItems().add(account);
    }

    private void addingRegisters(){
        for (Account account: parent.controller.getPersonController().getAccounts()) {
            table.getItems().add(new Account(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getEmail()));
        }
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



}
