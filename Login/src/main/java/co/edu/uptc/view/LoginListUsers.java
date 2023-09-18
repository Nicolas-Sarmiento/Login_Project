package co.edu.uptc.view;

import co.edu.uptc.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;


public class LoginListUsers extends Header{
    LoginView parent;
    BorderPane borderPane;
    TableView table;
    TableColumn idColumn;
    TableColumn userNameColumn;
    TableColumn passwordColumn;
    TableColumn roleColumn;
    TableColumn emailColumn;
    ObservableList<Account> accountList = FXCollections.observableArrayList();

    /**
     * Constructor of the LoginListUsers class.
     * @param loginView The parent LoginView instance.
     * @param home The Button instance to be set as home button in the header.
     */
    public LoginListUsers(LoginView loginView, Button home) {
        super(home);
        this.parent = loginView;
        borderPane = new BorderPane();
        table = new TableView<Account>();
        //addingRegisters();
        creationColumns();
    }

    /**
     * Method that returns the user list view scene.
     * @return Scene of the user list view.
     */
    public Scene loginListUsers(){
        updateTable();
        table.setItems(accountList);
        borderPane.setCenter(table);
        HBox header = this.getHeader();
        this.setOption("Ver cuentas");
        this.setName(parent.controller.getName());
        VBox root = new VBox(header, borderPane);
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    // Método para actualizar la tabla con las cuentas actuales
    private void updateTable() {
        List<Account> updatedAccounts = parent.controller.getPersonController().getAccounts();

        // Comparar las cuentas actuales con las cuentas en la tabla
        // Agregar cuentas nuevas si las hay
        for (Account updatedAccount : updatedAccounts) {
            if (!accountList.contains(updatedAccount)) {
                addAccount(updatedAccount);
            }
        }

        // Eliminar cuentas que ya no están presentes
        accountList.removeIf(account -> !updatedAccounts.contains(account));
    }
    public void removeAccount(Account account) {
        accountList.remove(account);
    }
    /**
     * Method to add an account to the table.
     * @param account User account to be added.
     */
    public void addAccount(Account account){
        accountList.add(account);
    }

//    /**
//     * Private method to add records to the
//     * table from the controller's account list.
//     */
//    private void addingRegisters(){
//        for (Account account: parent.controller.getPersonController().getAccounts()) {
//            table.getItems().add(new Account(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getEmail()));
//        }
//    }

    /**
     *Private method to create the columns of the table.
     */
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
