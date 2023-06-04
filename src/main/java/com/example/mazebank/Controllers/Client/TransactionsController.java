package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView transactions_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactionsList();
        transactions_listview.setItems(Model.getInstance().getAllTransaction());
        transactions_listview.setCellFactory(e -> new TransactionCellFactory());
    }

    private void initAllTransactionsList(){
        if(Model.getInstance().getAllTransaction().isEmpty()){
            Model.getInstance().setAllTransaction();
        }
    }
}
