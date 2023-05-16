package com.example.mazebank.Views;

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
    private final StringProperty clientSelectedMenuItem;

    public ViewFactory(){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem(){

        return clientSelectedMenuItem;
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
