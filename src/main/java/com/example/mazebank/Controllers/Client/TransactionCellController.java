package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public Label trans_date_lbl;
    public Text in_icon;
    public Text out_icon;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;

    private Transaction transaction;
    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
