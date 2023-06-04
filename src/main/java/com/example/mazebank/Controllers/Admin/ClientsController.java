package com.example.mazebank.Controllers.Admin;

import com.example.mazebank.Models.Client;
import com.example.mazebank.Models.Model;
import com.example.mazebank.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView <Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(event -> new ClientCellFactory());
    }

    private void initClientsList(){
        if(Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }

    }
}
