package com.example.mazebank.Views;

import com.example.mazebank.Controllers.Admin.AdminController;
import com.example.mazebank.Controllers.Admin.AdminMenuController;
import com.example.mazebank.Controllers.Client.ClientController;
import com.example.mazebank.Controllers.Client.ClientMenuController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {
private AccountType loginAccountType;

    //Client Views
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;
    private ObjectProperty <ClientMenuOptions> clientSelectedMenuItem;

    //Admin Views
    private AnchorPane createClientView;
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane clientsView;
    private AnchorPane depositView;


    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem(){

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


    //Admin View Section
    public AnchorPane getDepositView(){
        if(depositView == null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/fxml/Admin/Deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }
    public AnchorPane getClientsView() {

        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/fxml/Admin/Clients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }
    public void showMessageWindow(String pAddress, String message){
        StackPane pane = new StackPane();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label msg = new Label(message);
        hBox.getChildren().addAll(sender,msg);
        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane,300,100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/HANSBanking.png"))));
        stage.setResizable(false);
        //Fender geht nicht sofort weg man muss es handeln
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();

    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());

        }catch(Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/HANSBanking.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Hans Banking");
        stage.show();
    }

public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/Admin.fxml"));
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


    public void closeStage(Stage stage){
        stage.close();
    }
}
