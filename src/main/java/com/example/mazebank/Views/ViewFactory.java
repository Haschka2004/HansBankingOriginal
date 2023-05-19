package com.example.mazebank.Views;

import com.example.mazebank.Controllers.Admin.AdminController;
import com.example.mazebank.Controllers.Admin.AdminMenuController;
import com.example.mazebank.Controllers.Client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    //Client Views
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;
    private StringProperty clientSelectedMenuItem;

    //Admin Views
    private AnchorPane createClientView;
    private final StringProperty adminSelectedMenuItem;


    public ViewFactory(){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem(){

        return clientSelectedMenuItem;
    }

    public void setClientSelectedMenuItem(String clientSelectedMenuItem) {
        this.clientSelectedMenuItem.set(clientSelectedMenuItem);
    }
    /* Client Views Scection
     */

    public AnchorPane getDashboardView(){
        // Man checkt null, weil man nicht möchte, dass wenn man von einem Fenster wieder zu Dashboard geht es wieder neu lädt.
        if(dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/fxml/Client/Dashboard.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionsView(){
        if(transactionsView == null){
            try{
                transactionsView = new FXMLLoader(getClass().getResource("/fxml/Client/Transactions.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {

        if(accountsView == null){
            try{
                accountsView = new FXMLLoader(getClass().getResource("/fxml/Client/Accounts.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = null;
        try{
            scene = new Scene(loader.load());

        }catch(Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hans Banking");
        stage.show();
    }


    //Admin View Section
public StringProperty getAdminSelectedMenuItem(){
        return clientSelectedMenuItem;
}
    public AnchorPane getCreateClientView(){
        if(createClientView == null){
            try {
                createClientView = new FXMLLoader(getClass().getResource("/fxml/Admin/CreateClient.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }
    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/CreateClient.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }
    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);

    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());

        }catch(Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hans Banking");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
