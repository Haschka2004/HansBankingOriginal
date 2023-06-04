package com.example.mazebank.Controllers.Client;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label cheking_bal;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextField message_fld;
    public Button send_money_btn;

    /**
     * Diese Methode wird aufgerufen, wenn man das FXML-File als View aufrufen möchte, deswegen muss man das  Initializable Interface
     * implementieren !
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransaction());
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());
        send_money_btn.setOnAction(event -> onSendMoney());
    }

    private void bindData(){
        user_name.textProperty().bind(Bindings.concat("Willkommen, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Heute, "+ LocalDate.now());
        cheking_bal.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        savings_bal.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }

    private void initLatestTransactionsList(){
        if(Model.getInstance().getLatestTransaction().isEmpty()){
            Model.getInstance().setLatestTransaction();
        }

    }
    private void onSendMoney(){
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().payeeAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDatabaseDriver().updateBalance(receiver,amount,"ADD");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //Abzeihen vom senders savingsAccount
        Model.getInstance().getDatabaseDriver().updateBalance(sender,amount,"SUB");
        //Update savings account kontostand im client object
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender));
        //Speichern der neue Transaction
        Model.getInstance().getDatabaseDriver().newTransaction(sender,receiver,amount,message);
        // textfelder leeren
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");

    }
}
