package com.example.mazebank.Models;

import com.example.mazebank.Views.AccountType;
import com.example.mazebank.Views.ViewFactory;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    //Singelton Pattern
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;


    //Client Data Section
    private Client client;
    private boolean clientLoginSuccesFlag;

    //Admin Data Section

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // Client Data Section
        this.clientLoginSuccesFlag = false;
        this.client = new Client("","","",null,null,null);
        //Admin Data Section
    }

    public static synchronized Model getInstance(){
        // wenn null heißt es wurde noch kein model erzeugt
        if(model == null){
            model = new Model();
        }
        return model;
    }
    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){
        return databaseDriver;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /**
     * Client Mehthod Section
     */
    public boolean getClientLoginSuccessFlag(){
        return this.clientLoginSuccesFlag;
    }
    public void setClientLoginSuccesFlag(boolean flag){
        this.clientLoginSuccesFlag = flag;
    }

    public Client getClient() {
        return client;
    }
    public void evaluteClientCred(String pAddress, String password){
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress,password);

        try{
            if(resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                this.client.dateCreatedProperty().set(date);
                this.clientLoginSuccesFlag = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
