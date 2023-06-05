package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
    public Button msg_btn;


    private Transaction transaction;
    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sender_lbl.textProperty().bind(transaction.senderProperty());
        receiver_lbl.textProperty().bind(transaction.reiceiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_date_lbl.textProperty().bind(transaction.dateProperty().asString());
        //msg_btn.setOnAction(event -> Model.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(),transaction.messageProperty().get()));
        transactionIcon();
    }

        private void transactionIcon(){
            if(transaction.senderProperty().get().equals(Model.getInstance().getClient().payeeAddressProperty().get())){
                in_icon.setFill(Color.rgb(240,240,240));
                out_icon.setFill(Color.RED);
            }else {
                in_icon.setFill(Color.GREEN);
                out_icon.setFill(Color.rgb(240,240,240));
            }
        }

}
