package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    public Button report_btn;
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;

     @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

         this.addListeners();
     }

         private void addListeners(){
             dashboard_btn.setOnAction(actionEvent -> onDashboard());
             transaction_btn.setOnAction(actionEvent -> onTransactions());
           //  accounts_btn.setOnAction(actionEvent -> onAccounts());
             logout_btn.setOnAction(actionEvent -> onLogout());
         }

    private void onTransactions() {
         Model.getInstance().getViewFactory().getClientSelectedMenuItem().setValue(ClientMenuOptions.TRANSACTIONS);
    }


    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    //private void onAccounts(){
      //   Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    //}
    private void onLogout(){
         Stage stage = (Stage) dashboard_btn.getScene().getWindow();

         // Schließen der Stage
        Model.getInstance().getViewFactory().closeStage(stage);

        //Zeigen des LoginWindows
        Model.getInstance().getViewFactory().showLoginWindow();

        // ClientLoginSuccessFlag auf false setzten sonst kann sich der User wieder einloggen ohne pw und payeeAddress einzugeben
        Model.getInstance().setClientLoginSuccesFlag(false);
    }
}

