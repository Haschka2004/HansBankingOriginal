package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;
    /**
     * Diese Methode wird aufgerufen, wenn man das FXML-File als View aufrufen möchte, deswegen muss man das  Initializable Interface
     * implementieren !
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
      Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
          switch (newVal){
              case TRANSACTIONS -> client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
              case ACCOUNTS -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
              case DASHBOARD -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
          }
      });

}
}
