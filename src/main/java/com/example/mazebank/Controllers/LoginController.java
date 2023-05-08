package com.example.mazebank.Controllers;

import com.example.mazebank.Models.Model;
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
    public ChoiceBox acc_selector;
    public Label username;
    public TextField input_username;
    public Label password;
    public TextField input_password;
    public Button login_button;
    public Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle){
        login_button.setOnAction(actionEvent -> onLogin());
    }
    private void onLogin(){
        Stage stage = (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();

    }
}
