package com.example.mazebank.Controllers.Admin;

import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.AdminMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
        private void addListeners(){
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(actionEvent -> onClients());
        deposit_btn.setOnAction(actionEvent -> onDeposit());
        logout_btn.setOnAction(actionEvent -> onLogout());
        }
@FXML
        private void onCreateClient(){
            Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);

        }
        private void onClients(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
        }

        private  void onDeposit(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
        }
    private void onLogout(){
        Stage stage = (Stage) clients_btn.getScene().getWindow();

        // Schließen der Stage
        Model.getInstance().getViewFactory().closeStage(stage);

        //Zeigen des LoginWindows
        Model.getInstance().getViewFactory().showLoginWindow();

        // AdminLoginSuccessFlag auf false setzten sonst kann sich der User wieder einloggen ohne pw und payeeAddress einzugeben
        Model.getInstance().setAdminLodinSuccessFlag(false);
    }
    }

