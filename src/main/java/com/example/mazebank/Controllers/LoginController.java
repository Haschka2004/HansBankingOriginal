package com.example.mazebank.Controllers;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label username;
    public TextField input_username;
    public Label password;
    public TextField input_password;
    public Button login_button;
    public Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle){
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_button.setOnAction(actionEvent -> onLogin());
    }
    private void onLogin(){
        Stage stage = (Stage) error_label.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            // Evaluate Client Login Credentials
            Model.getInstance().evaluteClientCred(input_username.getText(),input_password.getText());
            if(Model.getInstance().getClientLoginSuccessFlag()){
             Model.getInstance().getViewFactory().showClientWindow();
             //Close login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            }else{
                input_username.setText("");
                input_password.setText("");
                error_label.setText("No such login Credentials.");
            }

        }else{
           //Evaluate Admin Login Credentials
            Model.getInstance().evaluatedAdminCred(input_username.getText(),input_password.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAdminWindow();
                // Close login Stage
                Model.getInstance().getViewFactory().closeStage(stage);
            }else{
                input_username.setText("");
                input_password.setText("");
                error_label.setText("No such login Credentials.");
            }
        }


    }
    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        // Ändert das Label von Username zu PayeeAddress je nach Auswahl der Checkbox
        if(acc_selector.getValue() == AccountType.ADMIN){
            username.setText("Username:");
        }else {
            username.setText("Payee Address:");
        }
    }
}
