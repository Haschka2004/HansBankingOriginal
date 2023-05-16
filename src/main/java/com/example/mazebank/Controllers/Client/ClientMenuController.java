package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
             accounts_btn.setOnAction(actionEvent -> onAccounts());
         }

    private void onTransactions() {
         Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Transactions");
    }


    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
    }

    private void onAccounts(){
         Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Accounts");
    }
}
